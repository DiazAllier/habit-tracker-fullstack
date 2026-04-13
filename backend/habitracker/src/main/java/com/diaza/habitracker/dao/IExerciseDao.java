package com.diaza.habitracker.dao;

import com.diaza.habitracker.entities.Exercise;
import com.diaza.habitracker.entities.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExerciseDao extends JpaRepository<Exercise, Long> {
}
