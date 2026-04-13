package com.diaza.habitracker.services;

import com.diaza.habitracker.dao.IWorkoutSessionDao;
import com.diaza.habitracker.entities.WorkoutSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutSessionServices {

    @Autowired
    private IWorkoutSessionDao workoutSesDao;

    @Transactional
    public List<WorkoutSession> findAll() {
        return (List<WorkoutSession>) workoutSesDao.findAll();
    }

    @Transactional
    public Optional<WorkoutSession> findById(Long id) {
        return workoutSesDao.findById(id);
    }

    @Transactional
    public WorkoutSession save(WorkoutSession workouts) {
        return workoutSesDao.save(workouts);
    }

    public void delete(WorkoutSession workouts) {
        workoutSesDao.delete(workouts);
    }


    @Transactional
    public Optional<WorkoutSession> findByUserIdAndCompletedFalse(Long id) {
        return (Optional<WorkoutSession>) workoutSesDao.findByUserIdAndCompletedFalse(id);
    }
}
