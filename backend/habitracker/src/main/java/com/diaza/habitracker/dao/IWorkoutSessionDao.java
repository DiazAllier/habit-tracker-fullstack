package com.diaza.habitracker.dao;

import com.diaza.habitracker.entities.Exercise;
import com.diaza.habitracker.entities.WorkoutSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IWorkoutSessionDao extends JpaRepository<WorkoutSession, Long> {
    Optional<WorkoutSession> findByUserIdAndCompletedFalse(Long userId);

}
