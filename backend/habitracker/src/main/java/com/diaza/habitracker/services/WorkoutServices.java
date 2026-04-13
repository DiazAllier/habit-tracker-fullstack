package com.diaza.habitracker.services;

import com.diaza.habitracker.dao.IWorkoutsDao;
import com.diaza.habitracker.entities.User;
import com.diaza.habitracker.entities.Workout;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutServices {


    @Autowired
    private IWorkoutsDao workoutDao;

    @Transactional
    public List<Workout> findAll() {
        return (List<Workout>) workoutDao.findAll();
    }

    @Transactional
    public Optional<Workout> findById(Long id) {
        return workoutDao.findById(id);
    }

    @Transactional
    public Workout save(Workout workouts) {
        return workoutDao.save(workouts);
    }

    public void delete(Workout workouts) {
        workoutDao.delete(workouts);
    }

}
