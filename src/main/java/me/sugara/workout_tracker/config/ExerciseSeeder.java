package me.sugara.workout_tracker.config;

import me.sugara.workout_tracker.entity.Exercise;
import me.sugara.workout_tracker.repository.ExerciseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExerciseSeeder {

    @Bean
    CommandLineRunner seedExercises(ExerciseRepository exerciseRepository) {
        return args -> {
            if (exerciseRepository.count() == 0) {
                exerciseRepository.save(new Exercise(null, "Push-up", "Bodyweight chest exercise", "Strength", "Chest"));
                exerciseRepository.save(new Exercise(null, "Squat", "Leg strength exercise", "Strength", "Legs"));
                exerciseRepository.save(new Exercise(null, "Running", "Cardio endurance exercise", "Cardio", null));
                exerciseRepository.save(new Exercise(null, "Plank", "Core stability exercise", "Strength", "Abs"));
            }
        };
    }
}