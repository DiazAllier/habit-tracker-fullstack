package com.diaza.habitracker.controllers;

import com.diaza.habitracker.dao.IExerciseDao;
import com.diaza.habitracker.entities.Exercise;
import com.diaza.habitracker.services.ExerciseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/controller/exercises")
@CrossOrigin(origins = "http://localhost:5173")
public class ExerciseController {

    @Autowired
    private final ExerciseServices exerciseRepository;

    public ExerciseController(ExerciseServices exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @GetMapping
    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    @PostMapping
    public Exercise createExercise(@RequestBody Exercise exercise) {
        return exerciseRepository.save(exercise);
    }
}