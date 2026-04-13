package com.diaza.habitracker.services;

import com.diaza.habitracker.dao.IExerciseDao;
import com.diaza.habitracker.dao.IWorkoutsDao;
import com.diaza.habitracker.entities.Exercise;
import com.diaza.habitracker.entities.Workout;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServices {


    @Autowired
    private IExerciseDao exerciseDao;

    @Transactional
    public List<Exercise> findAll() {
        return (List<Exercise>) exerciseDao.findAll();
    }

    @Transactional
    public Exercise findById(Long id) {
        return exerciseDao.findById(id).orElse(null);
    }

    @Transactional
    public Exercise save(Exercise exercise) {
        return exerciseDao.save(exercise);
    }

    public void delete(Exercise exercise) {
        exerciseDao.delete(exercise);
    }

}
