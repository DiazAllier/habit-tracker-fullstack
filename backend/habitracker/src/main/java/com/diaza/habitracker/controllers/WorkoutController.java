package com.diaza.habitracker.controllers;

import com.diaza.habitracker.entities.Workout;
import com.diaza.habitracker.entities.WorkoutExercise;
import com.diaza.habitracker.payload.response.MessageResponse;
import com.diaza.habitracker.services.WorkoutExerciseServices;
import com.diaza.habitracker.services.WorkoutServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/controller")
@CrossOrigin(origins = "http://localhost:5173")
public class WorkoutController {

    @Autowired
    private WorkoutServices services;

    @Autowired
    private WorkoutExerciseServices workoutExerciseServices;

    @GetMapping("/workout")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(services.findAll());
    }

    @GetMapping("/workout/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Workout workout = services.findById(id).orElse(null);
        if (workout != null) {
            return ResponseEntity.ok(workout);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MessageResponse("Workout not found"));
    }

    @PostMapping("/workout")
    public ResponseEntity<?> save(@RequestBody Workout workout) {
        return ResponseEntity.ok(services.save(workout));
    }

    @DeleteMapping("/workout/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Workout workout = services.findById(id).orElse(null);
        if (workout != null) {
            services.delete(workout);
            return ResponseEntity.ok(new MessageResponse("Workout deleted"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MessageResponse("Workout not found"));
    }

    @PostMapping("/workout/{workoutId}/exercises")
    public ResponseEntity<?> addExerciseToWorkout(
            @PathVariable Long workoutId,
            @RequestBody WorkoutExercise workoutExercise
    ) {
        Workout workout = services.findById(workoutId).orElse(null);
        if (workout == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("Workout not found"));
        }
        workoutExercise.setWorkout(workout);
        WorkoutExercise saved = workoutExerciseServices.save(workoutExercise);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/workout/{workoutId}/exercises")
    public ResponseEntity<?> getExercisesInWorkout(@PathVariable Long workoutId) {
        Workout workout = services.findById(workoutId).orElse(null);
        if (workout == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("Workout not found"));
        }
        return ResponseEntity.ok(workout.getExercises());
    }
}