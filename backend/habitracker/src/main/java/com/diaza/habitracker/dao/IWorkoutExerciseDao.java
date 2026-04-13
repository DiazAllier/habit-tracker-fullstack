package com.diaza.habitracker.dao;

import com.diaza.habitracker.entities.WorkoutExercise;
import com.diaza.habitracker.entities.WorkoutSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IWorkoutExerciseDao extends JpaRepository<WorkoutExercise, Long> {

}
