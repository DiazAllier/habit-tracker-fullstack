package com.diaza.habitracker.config;

import com.diaza.habitracker.dao.IExerciseDao;
import com.diaza.habitracker.entities.*;
import com.diaza.habitracker.entities.enums.ExerciseCategory;
import com.diaza.habitracker.entities.enums.SessionType;
import com.diaza.habitracker.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedData(
            ExerciseServices exerciseRepo,
            WorkoutServices workoutRepo,
            WorkoutExerciseServices workoutExerciseRepo,
            WorkoutSessionServices workoutSessionServices,
            UserServices userServices,
            BCryptPasswordEncoder passwordEncoder
            ) {
        return args -> {

            if (exerciseRepo.findAll().size() > 0) return;

            User user = userServices.save(new User(null, "test@gmail.com", passwordEncoder.encode("test123")));

            Exercise pushUp       = exerciseRepo.save(new Exercise(null, "Push Up",         ExerciseCategory.PUSH));
            Exercise benchPress   = exerciseRepo.save(new Exercise(null, "Bench Press",      ExerciseCategory.PUSH));
            Exercise shoulderPress= exerciseRepo.save(new Exercise(null, "Shoulder Press",   ExerciseCategory.PUSH));
            Exercise tricepDips   = exerciseRepo.save(new Exercise(null, "Tricep Dips",      ExerciseCategory.PUSH));
            Exercise chestFly     = exerciseRepo.save(new Exercise(null, "Chest Fly",        ExerciseCategory.PUSH));
            Exercise inclinePushUp= exerciseRepo.save(new Exercise(null, "Incline Push Up",  ExerciseCategory.PUSH));

            Exercise pullUp       = exerciseRepo.save(new Exercise(null, "Pull Up",          ExerciseCategory.PULL));
            Exercise bentOverRow  = exerciseRepo.save(new Exercise(null, "Bent Over Row",    ExerciseCategory.PULL));
            Exercise bicepCurl    = exerciseRepo.save(new Exercise(null, "Bicep Curl",       ExerciseCategory.PULL));
            Exercise latPulldown  = exerciseRepo.save(new Exercise(null, "Lat Pulldown",     ExerciseCategory.PULL));
            Exercise facePull     = exerciseRepo.save(new Exercise(null, "Face Pull",        ExerciseCategory.PULL));
            Exercise hammerCurl   = exerciseRepo.save(new Exercise(null, "Hammer Curl",      ExerciseCategory.PULL));

            Exercise squat        = exerciseRepo.save(new Exercise(null, "Squat",            ExerciseCategory.LEGS));
            Exercise lunges       = exerciseRepo.save(new Exercise(null, "Lunges",           ExerciseCategory.LEGS));
            Exercise legPress     = exerciseRepo.save(new Exercise(null, "Leg Press",        ExerciseCategory.LEGS));
            Exercise rdl          = exerciseRepo.save(new Exercise(null, "Romanian Deadlift",ExerciseCategory.LEGS));
            Exercise calfRaises   = exerciseRepo.save(new Exercise(null, "Calf Raises",      ExerciseCategory.LEGS));
            Exercise gluteBridge  = exerciseRepo.save(new Exercise(null, "Glute Bridge",     ExerciseCategory.LEGS));

            Exercise plank        = exerciseRepo.save(new Exercise(null, "Plank",            ExerciseCategory.CORE));
            Exercise crunches     = exerciseRepo.save(new Exercise(null, "Crunches",         ExerciseCategory.CORE));
            Exercise legRaises    = exerciseRepo.save(new Exercise(null, "Leg Raises",       ExerciseCategory.CORE));
            Exercise russianTwist = exerciseRepo.save(new Exercise(null, "Russian Twist",    ExerciseCategory.CORE));
            Exercise mountainClimbers = exerciseRepo.save(new Exercise(null, "Mountain Climbers", ExerciseCategory.CORE));
            Exercise deadBug      = exerciseRepo.save(new Exercise(null, "Dead Bug",         ExerciseCategory.CORE));

            Exercise jumpRope     = exerciseRepo.save(new Exercise(null, "Jump Rope",        ExerciseCategory.CARDIO));
            Exercise burpees      = exerciseRepo.save(new Exercise(null, "Burpees",          ExerciseCategory.CARDIO));
            Exercise boxJumps     = exerciseRepo.save(new Exercise(null, "Box Jumps",        ExerciseCategory.CARDIO));
            Exercise highKnees    = exerciseRepo.save(new Exercise(null, "High Knees",       ExerciseCategory.CARDIO));

            Exercise handstandHold= exerciseRepo.save(new Exercise(null, "Handstand Hold",   ExerciseCategory.SKILL));
            Exercise lSit         = exerciseRepo.save(new Exercise(null, "L-Sit",            ExerciseCategory.SKILL));
            Exercise muscleUp     = exerciseRepo.save(new Exercise(null, "Muscle Up",        ExerciseCategory.SKILL));

            Workout upperBody = workoutRepo.save(new Workout(null, "Upper Body Strength"));
            workoutExerciseRepo.save(new WorkoutExercise(null, upperBody, pushUp,        3, "10-12", 60));
            workoutExerciseRepo.save(new WorkoutExercise(null, upperBody, benchPress,    3, "8-10",  60));
            workoutExerciseRepo.save(new WorkoutExercise(null, upperBody, pullUp,        3, "8-10",  60));
            workoutExerciseRepo.save(new WorkoutExercise(null, upperBody, shoulderPress, 3, "10-12", 60));
            workoutExerciseRepo.save(new WorkoutExercise(null, upperBody, tricepDips,    3, "12-15", 45));
            workoutExerciseRepo.save(new WorkoutExercise(null, upperBody, bicepCurl,     3, "10-12", 45));

            Workout lowerBody = workoutRepo.save(new Workout(null, "Lower Body Strength"));
            workoutExerciseRepo.save(new WorkoutExercise(null, lowerBody, squat,       4, "10-12", 90));
            workoutExerciseRepo.save(new WorkoutExercise(null, lowerBody, lunges,      3, "12",    60));
            workoutExerciseRepo.save(new WorkoutExercise(null, lowerBody, legPress,    3, "10",    60));
            workoutExerciseRepo.save(new WorkoutExercise(null, lowerBody, rdl,         3, "10-12", 60));
            workoutExerciseRepo.save(new WorkoutExercise(null, lowerBody, calfRaises,  4, "15-20", 30));
            workoutExerciseRepo.save(new WorkoutExercise(null, lowerBody, gluteBridge, 3, "15",    45));

            Workout pushDay = workoutRepo.save(new Workout(null, "Push Day"));
            workoutExerciseRepo.save(new WorkoutExercise(null, pushDay, benchPress,    4, "8-10",  60));
            workoutExerciseRepo.save(new WorkoutExercise(null, pushDay, shoulderPress, 3, "10-12", 60));
            workoutExerciseRepo.save(new WorkoutExercise(null, pushDay, inclinePushUp, 3, "12-15", 45));
            workoutExerciseRepo.save(new WorkoutExercise(null, pushDay, chestFly,      3, "12",    45));
            workoutExerciseRepo.save(new WorkoutExercise(null, pushDay, tricepDips,    3, "12-15", 45));

            Workout pullDay = workoutRepo.save(new Workout(null, "Pull Day"));
            workoutExerciseRepo.save(new WorkoutExercise(null, pullDay, pullUp,      4, "6-8",   60));
            workoutExerciseRepo.save(new WorkoutExercise(null, pullDay, bentOverRow, 3, "8-10",  60));
            workoutExerciseRepo.save(new WorkoutExercise(null, pullDay, latPulldown, 3, "10-12", 60));
            workoutExerciseRepo.save(new WorkoutExercise(null, pullDay, facePull,    3, "15",    45));
            workoutExerciseRepo.save(new WorkoutExercise(null, pullDay, hammerCurl,  3, "10-12", 45));

            Workout coreAbs = workoutRepo.save(new Workout(null, "Core & Abs"));
            workoutExerciseRepo.save(new WorkoutExercise(null, coreAbs, plank,            3, "60s",   60));
            workoutExerciseRepo.save(new WorkoutExercise(null, coreAbs, crunches,         3, "20",    45));
            workoutExerciseRepo.save(new WorkoutExercise(null, coreAbs, legRaises,        3, "15",    45));
            workoutExerciseRepo.save(new WorkoutExercise(null, coreAbs, russianTwist,     3, "20",    45));
            workoutExerciseRepo.save(new WorkoutExercise(null, coreAbs, mountainClimbers, 3, "30s",   30));
            workoutExerciseRepo.save(new WorkoutExercise(null, coreAbs, deadBug,          3, "10",    45));

            Workout hiit = workoutRepo.save(new Workout(null, "Full Body HIIT"));
            workoutExerciseRepo.save(new WorkoutExercise(null, hiit, burpees,          4, "10",  30));
            workoutExerciseRepo.save(new WorkoutExercise(null, hiit, highKnees,        4, "30s", 30));
            workoutExerciseRepo.save(new WorkoutExercise(null, hiit, jumpRope,         4, "60s", 30));
            workoutExerciseRepo.save(new WorkoutExercise(null, hiit, boxJumps,         3, "10",  45));
            workoutExerciseRepo.save(new WorkoutExercise(null, hiit, mountainClimbers, 3, "30s", 30));

            Workout skillTraining = workoutRepo.save(new Workout(null, "Skill Training"));
            workoutExerciseRepo.save(new WorkoutExercise(null, skillTraining, handstandHold, 3, "30s", 60));
            workoutExerciseRepo.save(new WorkoutExercise(null, skillTraining, lSit,          3, "20s", 60));
            workoutExerciseRepo.save(new WorkoutExercise(null, skillTraining, muscleUp,      3, "5",   90));
            workoutExerciseRepo.save(new WorkoutExercise(null, skillTraining, pullUp,        3, "8",   60));

            Workout cooldown = workoutRepo.save(new Workout(null, "Cooldown & Stretch"));
            workoutExerciseRepo.save(new WorkoutExercise(null, cooldown, plank,       2, "30s", 30));
            workoutExerciseRepo.save(new WorkoutExercise(null, cooldown, deadBug,     2, "10",  30));
            workoutExerciseRepo.save(new WorkoutExercise(null, cooldown, gluteBridge, 2, "15",  30));

            WorkoutSession session = new WorkoutSession();
            session.setUser(user);
            session.setWorkout(upperBody);
            session.setProgress(0);
            session.setCaloriesBurned(0);
            session.setCompleted(false);
            session.setSessionType(SessionType.STRENGTH);
            session.setStartedAt(LocalDateTime.now());
            workoutSessionServices.save(session);
        };
    }
}