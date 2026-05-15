package com.diaza.habitracker.entities;

import jakarta.persistence.*;

@Entity
public class SessionExerciseLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private WorkoutSession workoutSession;
    @ManyToOne
    private Exercise exercise;
    private Integer actualSets;
    private String actualReps;
    private Integer actualDuration;

    public SessionExerciseLog(Long id, WorkoutSession workoutSession, Exercise exercise, Integer actualSets, String actualReps, Integer actualDuration) {
        this.id = id;
        this.workoutSession = workoutSession;
        this.exercise = exercise;
        this.actualSets = actualSets;
        this.actualReps = actualReps;
        this.actualDuration = actualDuration;
    }

    public SessionExerciseLog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WorkoutSession getWorkoutSession() {
        return workoutSession;
    }

    public void setWorkoutSession(WorkoutSession workoutSession) {
        this.workoutSession = workoutSession;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Integer getActualSets() {
        return actualSets;
    }

    public void setActualSets(Integer actualSets) {
        this.actualSets = actualSets;
    }

    public String getActualReps() {
        return actualReps;
    }

    public void setActualReps(String actualReps) {
        this.actualReps = actualReps;
    }

    public Integer getActualDuration() {
        return actualDuration;
    }

    public void setActualDuration(Integer actualDuration) {
        this.actualDuration = actualDuration;
    }
}
