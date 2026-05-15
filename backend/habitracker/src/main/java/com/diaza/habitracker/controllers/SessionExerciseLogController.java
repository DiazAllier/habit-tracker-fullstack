package com.diaza.habitracker.controllers;

import com.diaza.habitracker.entities.*;
import com.diaza.habitracker.payload.response.MessageResponse;
import com.diaza.habitracker.services.SessionExerciseLogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/controller/sessions/exercise")
@CrossOrigin(origins = "http://localhost:5173")
public class SessionExerciseLogController {

    @Autowired
    private SessionExerciseLogServices services;

    @GetMapping
    public ResponseEntity<List<SessionExerciseLog>> getAllSessions() {
        return ResponseEntity.ok(services.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        SessionExerciseLog exercise = services.findById(id).orElse(null);
        if (exercise != null) {
            return ResponseEntity.ok(exercise);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MessageResponse("Session Exercise not found"));
    }

    @GetMapping("log/{id}")
    public ResponseEntity<?> getAllByWorkoutSessionId(@PathVariable Long id) {
        List<SessionExerciseLog> logs = services.findAllByWorkoutSessionId(id);
        if (logs != null) {
            return ResponseEntity.ok(logs);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MessageResponse("Session logs not found"));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody SessionExerciseLog session) {
        if (session.getId() != null && session.getId() > 0) {
            SessionExerciseLog existing = services.findById(session.getId()).orElse(null);
            if (existing == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Session not found"));
            }
            existing.setActualSets(session.getActualSets());
            existing.setActualDuration(session.getActualDuration());
            existing.setExercise(session.getExercise());
            existing.setWorkoutSession(session.getWorkoutSession());
            existing.setActualReps(session.getActualReps());
            return ResponseEntity.ok(services.save(existing));
        }
        return ResponseEntity.ok(services.save(session));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExercise(@PathVariable Long id) {
        SessionExerciseLog existing = services.findById(id).orElse(null);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("Session exercise not found"));
        }
        services.delete(existing);
        return ResponseEntity.ok(new MessageResponse("Session exercise deleted"));
    }
}
