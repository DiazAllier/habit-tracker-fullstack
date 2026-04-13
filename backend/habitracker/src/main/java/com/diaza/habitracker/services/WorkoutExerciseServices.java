package com.diaza.habitracker.services;

import com.diaza.habitracker.dao.IWorkoutExerciseDao;
import com.diaza.habitracker.dao.IWorkoutsDao;
import com.diaza.habitracker.entities.Workout;
import com.diaza.habitracker.entities.WorkoutExercise;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutExerciseServices {


    @Autowired
    private IWorkoutExerciseDao workoutExerciseDao;

    @Transactional
    public List<WorkoutExercise> findAll() {
        return (List<WorkoutExercise>) workoutExerciseDao.findAll();
    }

    @Transactional
    public Optional<WorkoutExercise> findById(Long id) {
        return workoutExerciseDao.findById(id);
    }

    @Transactional
    public WorkoutExercise save(WorkoutExercise workouts) {
        return workoutExerciseDao.save(workouts);
    }

    public void delete(WorkoutExercise workouts) {
        workoutExerciseDao.delete(workouts);
    }

}
