package com.diaza.habitracker.services;

import com.diaza.habitracker.dao.ISessionExerciseLogDao;
import com.diaza.habitracker.dao.IWorkoutSessionDao;
import com.diaza.habitracker.entities.SessionExerciseLog;
import com.diaza.habitracker.entities.WorkoutSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionExerciseLogServices {

    @Autowired
    private ISessionExerciseLogDao sessionDao;

    @Transactional
    public List<SessionExerciseLog> findAll() {
        return (List<SessionExerciseLog>) sessionDao.findAll();
    }

    @Transactional
    public Optional<SessionExerciseLog> findById(Long id) {
        return sessionDao.findById(id);
    }

    @Transactional
    public SessionExerciseLog save(SessionExerciseLog session) {
        return sessionDao.save(session);
    }

    @Transactional
    public void delete(SessionExerciseLog session) {
        sessionDao.delete(session);
    }

    @Transactional
    public List<SessionExerciseLog> findAllByWorkoutSessionId(Long sessionId) {
        return sessionDao.findAllByWorkoutSessionId(sessionId);
    }

}