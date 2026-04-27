package com.diaza.habitracker.controllers;

import com.diaza.habitracker.entities.Exercise;
import com.diaza.habitracker.payload.response.MessageResponse;
import com.diaza.habitracker.services.ExerciseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/controller/exercises")
@CrossOrigin(origins = "http://localhost:5173")
public class ExerciseController {

    @Autowired
    private ExerciseServices exerciseServices;

    @GetMapping
    public ResponseEntity<List<Exercise>> getAllExercises() {
        return ResponseEntity.ok(exerciseServices.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Exercise exercise = exerciseServices.findById(id);
        if (exercise != null) {
            return ResponseEntity.ok(exercise);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MessageResponse("Exercise not found"));
    }

    @PostMapping
    public ResponseEntity<Exercise> createExercise(@RequestBody Exercise exercise) {
        return ResponseEntity.ok(exerciseServices.save(exercise));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateExercise(@PathVariable Long id, @RequestBody Exercise updated) {
        Exercise existing = exerciseServices.findById(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("Exercise not found"));
        }
        existing.setName(updated.getName());
        existing.setCategory(updated.getCategory());
        return ResponseEntity.ok(exerciseServices.save(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExercise(@PathVariable Long id) {
        Exercise existing = exerciseServices.findById(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("Exercise not found"));
        }
        exerciseServices.delete(existing);
        return ResponseEntity.ok(new MessageResponse("Exercise deleted"));
    }
}