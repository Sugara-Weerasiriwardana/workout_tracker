package me.sugara.workout_tracker.dto;

import lombok.Data;

@Data
public class WorkoutExerciseRequest {
    private Long exerciseId;
    private Integer sets;
    private Integer reps;
    private Double weight;
}