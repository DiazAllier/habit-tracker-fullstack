package com.diaza.habitracker.controllers;


import com.diaza.habitracker.dao.IUserDao;
import com.diaza.habitracker.entities.User;
import com.diaza.habitracker.entities.Workout;
import com.diaza.habitracker.entities.WorkoutSession;
import com.diaza.habitracker.payload.response.MessageResponse;
import com.diaza.habitracker.services.ExerciseServices;
import com.diaza.habitracker.services.WorkoutServices;
import com.diaza.habitracker.services.WorkoutSessionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/controller/sessions")
@CrossOrigin(origins = "http://localhost:5173")
public class WorkoutSessionController {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private WorkoutServices workoutServices;

    @Autowired
    private ExerciseServices exerciseServices;

    @Autowired
    private WorkoutSessionServices workoutServServices;

    @GetMapping("/active/{userId}")
    public WorkoutSession getActiveSession(@PathVariable Long userId) {
        return workoutServServices.findByUserIdAndCompletedFalse(userId)
                .orElse(null);
    }

    @PostMapping("/start/{userId}/{workoutId}")
    public WorkoutSession startWorkout(@PathVariable Long userId, @PathVariable Long workoutId) {

        User user = userDao.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Workout workout = workoutServices.findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout not found"));

        WorkoutSession session = new WorkoutSession();
        session.setUser(user);
        session.setWorkout(workout);
        session.setProgress(0);
        session.setCompleted(false);
        session.setStartedAt(LocalDateTime.now());

        return workoutServServices.save(session);
    }

    @PutMapping("/{sessionId}/progress")
    public WorkoutSession updateProgress(
            @PathVariable Long sessionId,
            @RequestParam Integer progress
    ) {
        WorkoutSession session = workoutServServices.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        session.setProgress(progress);

        if (progress >= 100) {
            session.setCompleted(true);
            session.setCompletedAt(LocalDateTime.now());
        }

        return workoutServServices.save(session);
    }

    @PutMapping("/{sessionId}/complete")
    public WorkoutSession completeWorkout(@PathVariable Long sessionId) {

        WorkoutSession session = workoutServServices.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        session.setProgress(100);
        session.setCompleted(true);
        session.setCompletedAt(LocalDateTime.now());

        return workoutServServices.save(session);
    }
}