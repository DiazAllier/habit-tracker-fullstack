package com.diaza.habitracker.controllers;


import com.diaza.habitracker.entities.User;
import com.diaza.habitracker.entities.Workout;
import com.diaza.habitracker.payload.response.MessageResponse;
import com.diaza.habitracker.services.UserServices;
import com.diaza.habitracker.services.WorkoutServices;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/controller")
@CrossOrigin(origins = "http://localhost:5173")
public class WorkoutController {


    @Autowired
    private WorkoutServices services;

    @GetMapping("workout")
    public ResponseEntity<?> findAll() {
        return  ResponseEntity.ok(services.findAll());
    }

    @GetMapping("workout/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
       Workout workout = services.findById(id).orElse(null);
        if (workout != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Workout found, ID: " + workout.toString()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Workout not found"));
    }

    @PostMapping("workout")
    public ResponseEntity<?> save(@RequestBody Workout workouts) {
        return ResponseEntity.ok(services.save(workouts));
    }

    @DeleteMapping("workout/{id}")
    public ResponseEntity<?> save(@PathVariable Long id) {
        Workout workout = services.findById(id).orElse(null);
        if (workout != null) {
        services.delete(workout);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Workout deleted"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Workout not found"));
    }
}
