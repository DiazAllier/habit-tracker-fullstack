package com.diaza.habitracker.controllers;

import com.diaza.habitracker.dao.IUserDao;
import com.diaza.habitracker.entities.User;
import com.diaza.habitracker.entities.Workout;
import com.diaza.habitracker.entities.WorkoutSession;
import com.diaza.habitracker.entities.enums.SessionType;
import com.diaza.habitracker.payload.response.MessageResponse;
import com.diaza.habitracker.services.WorkoutServices;
import com.diaza.habitracker.services.WorkoutSessionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/controller/sessions")
@CrossOrigin(origins = "http://localhost:5173")
public class WorkoutSessionController {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private WorkoutServices workoutServices;

    @Autowired
    private WorkoutSessionServices workoutServServices;

    // GET active session for a user
    @GetMapping("/active/{userId}")
    public ResponseEntity<?> getActiveSession(@PathVariable Long userId) {
        WorkoutSession session = workoutServServices
                .findByUserIdAndCompletedFalse(userId)
                .orElse(null);
        if (session == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("No active session"));
        }
        return ResponseEntity.ok(session);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WorkoutSession>> getUserSessions(@PathVariable Long userId) {
        return ResponseEntity.ok(workoutServServices.findAllByUserId(userId));
    }

    @PostMapping("/start/{userId}/{workoutId}")
    public ResponseEntity<?> startWorkout(
            @PathVariable Long userId,
            @PathVariable Long workoutId,
            @RequestParam(defaultValue = "STRENGTH") SessionType sessionType
    ) {
        if (workoutServServices.findByUserIdAndCompletedFalse(userId).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new MessageResponse("User already has an active session"));
        }

        User user = userDao.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Workout workout = workoutServices.findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout not found"));

        WorkoutSession session = new WorkoutSession();
        session.setUser(user);
        session.setWorkout(workout);
        session.setProgress(0);
        session.setCompleted(false);
        session.setCaloriesBurned(0);
        session.setSessionType(sessionType);
        session.setStartedAt(LocalDateTime.now());

        return ResponseEntity.ok(workoutServServices.save(session));
    }

    @PostMapping("/start/run/{userId}")
    public ResponseEntity<?> startRun(@PathVariable Long userId) {
        if (workoutServServices.findByUserIdAndCompletedFalse(userId).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new MessageResponse("User already has an active session"));
        }

        User user = userDao.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        WorkoutSession session = new WorkoutSession();
        session.setUser(user);
        session.setWorkout(null);
        session.setProgress(0);
        session.setCompleted(false);
        session.setCaloriesBurned(0);
        session.setDistanceKm(0.0);
        session.setDurationSeconds(0);
        session.setSessionType(SessionType.CARDIO);
        session.setStartedAt(LocalDateTime.now());

        return ResponseEntity.ok(workoutServServices.save(session));
    }

    @PutMapping("/{sessionId}/finish-run")
    public ResponseEntity<?> finishRun(
            @PathVariable Long sessionId,
            @RequestParam Double distanceKm,
            @RequestParam Integer durationSeconds
    ) {
        WorkoutSession session = workoutServServices.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        session.setDistanceKm(distanceKm);
        session.setDurationSeconds(durationSeconds);
        session.setProgress(100);
        session.setCompleted(true);
        session.setCompletedAt(LocalDateTime.now());

        // ~60 calories per km
        int calories = (int) Math.round(distanceKm * 60);
        session.setCaloriesBurned(calories);

        return ResponseEntity.ok(workoutServServices.save(session));
    }

    @PutMapping("/{sessionId}/progress")
    public ResponseEntity<?> updateProgress(
            @PathVariable Long sessionId,
            @RequestParam Integer progress
    ) {
        WorkoutSession session = workoutServServices.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        session.setProgress(progress);

        if (progress >= 100) {
            session.setCompleted(true);
            session.setCompletedAt(LocalDateTime.now());
            session.setCaloriesBurned(calculateStrengthCalories(session));
        }

        return ResponseEntity.ok(workoutServServices.save(session));
    }

    @PutMapping("/{sessionId}/complete")
    public ResponseEntity<?> completeWorkout(@PathVariable Long sessionId) {
        WorkoutSession session = workoutServServices.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        session.setProgress(100);
        session.setCompleted(true);
        session.setCompletedAt(LocalDateTime.now());
        session.setCaloriesBurned(calculateStrengthCalories(session));

        return ResponseEntity.ok(workoutServServices.save(session));
    }

    @GetMapping("/calories/week/{userId}")
    public ResponseEntity<?> getWeeklyCalories(@PathVariable Long userId) {
        List<WorkoutSession> sessions = workoutServServices.findAllByUserId(userId);
        int totalCalories = sessions.stream()
                .filter(WorkoutSession::isCompleted)
                .mapToInt(s -> s.getCaloriesBurned() != null ? s.getCaloriesBurned() : 0)
                .sum();
        return ResponseEntity.ok(totalCalories);
    }

    private int calculateStrengthCalories(WorkoutSession session) {
        if (session.getWorkout() == null || session.getWorkout().getExercises() == null) return 0;
        return session.getWorkout().getExercises().stream()
                .mapToInt(we -> (we.getSets() != null ? we.getSets() : 1) * 50)
                .sum();
    }
}