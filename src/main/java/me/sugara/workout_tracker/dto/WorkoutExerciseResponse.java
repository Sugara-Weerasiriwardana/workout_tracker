package me.sugara.workout_tracker.dto;
import lombok.Data;

@Data
public class WorkoutExerciseResponse {
    private String exerciseName;
    private Integer sets;
    private Integer reps;
    private Double weight;
}
