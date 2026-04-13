package com.diaza.habitracker.dao;

import com.diaza.habitracker.entities.User;
import com.diaza.habitracker.entities.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWorkoutsDao extends JpaRepository<Workout, Long> {
}
