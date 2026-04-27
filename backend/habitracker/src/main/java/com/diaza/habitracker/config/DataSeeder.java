package com.diaza.habitracker.config;

import com.diaza.habitracker.dao.IExerciseDao;
import com.diaza.habitracker.dao.IWorkoutSessionDao;
import com.diaza.habitracker.dao.IWorkoutsDao;
import com.diaza.habitracker.entities.*;
import com.diaza.habitracker.entities.enums.ExerciseCategory;
import com.diaza.habitracker.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.Month;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedData(
            ExerciseServices exerciseRepo,
            WorkoutServices workoutRepo,
            WorkoutExerciseServices workoutExerciseRepo,
            WorkoutSessionServices workoutSessionServices,
            UserServices userServices
    ) {
        return args -> {

            if ((long) exerciseRepo.findAll().size() > 0) return;


            User user = new User(null, "allierdiaza@gmail.com", "test123");

            userServices.save(user);

            Exercise pushUp = exerciseRepo.save(
                    new Exercise(null, "Push Up", ExerciseCategory.PUSH));

            Exercise pullUp = exerciseRepo.save(
                    new Exercise(null, "Pull Up", ExerciseCategory.PULL));

            Exercise plank = exerciseRepo.save(
                    new Exercise(null, "Plank", ExerciseCategory.CORE));

            Exercise longRun = exerciseRepo.save(
                    new Exercise(null, "Long Run", ExerciseCategory.CARDIO));

            Workout workout = new Workout();
            workout.setName("Upper Body Strength");

            workout = workoutRepo.save(workout);


            workoutExerciseRepo.save(
                    new WorkoutExercise(null, workout, pushUp, 3, "10-12", 74));

            workoutExerciseRepo.save(
                    new WorkoutExercise(null, workout, pullUp, 3, "8-10", 25));

            workoutExerciseRepo.save(
                    new WorkoutExercise(null, workout, plank, 3, "2", 30));

            workoutExerciseRepo.save(
                    new WorkoutExercise(null, workout, longRun, 1, "1", 90));

            WorkoutSession session = new WorkoutSession();
            session.setUser(user);
            session.setWorkout(workout);
            session.setProgress(20);
            session.setCaloriesBurned(302);
            session.setCompleted(false);
            session.setStartedAt(LocalDateTime.now());

            workoutSessionServices.save(session);
        };
    }
}
