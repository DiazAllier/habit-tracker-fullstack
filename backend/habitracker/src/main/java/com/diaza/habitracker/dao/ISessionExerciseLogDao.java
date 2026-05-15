package com.diaza.habitracker.dao;

import com.diaza.habitracker.entities.SessionExerciseLog;
import com.diaza.habitracker.entities.WorkoutSession;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ISessionExerciseLogDao extends CrudRepository<SessionExerciseLog, Long> {
    List<SessionExerciseLog> findAllByWorkoutSessionId(Long sessionId);


}