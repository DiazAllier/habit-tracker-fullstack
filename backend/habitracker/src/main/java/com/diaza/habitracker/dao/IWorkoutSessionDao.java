package com.diaza.habitracker.dao;

import com.diaza.habitracker.entities.WorkoutSession;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IWorkoutSessionDao extends CrudRepository<WorkoutSession, Long> {

    Optional<WorkoutSession> findByUserIdAndCompletedFalse(Long userId);

    List<WorkoutSession> findAllByUserId(Long userId);
}