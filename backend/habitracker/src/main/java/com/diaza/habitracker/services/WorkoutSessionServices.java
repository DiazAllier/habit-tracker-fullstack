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
    public WorkoutSession save(WorkoutSession session) {
        return workoutSesDao.save(session);
    }

    @Transactional
    public void delete(WorkoutSession session) {
        workoutSesDao.delete(session);
    }

    @Transactional
    public Optional<WorkoutSession> findByUserIdAndCompletedFalse(Long userId) {
        return workoutSesDao.findByUserIdAndCompletedFalse(userId);
    }

    @Transactional
    public List<WorkoutSession> findAllByUserId(Long userId) {
        return workoutSesDao.findAllByUserId(userId);
    }
}