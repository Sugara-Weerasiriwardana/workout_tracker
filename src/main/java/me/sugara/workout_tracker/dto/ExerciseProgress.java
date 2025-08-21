package me.sugara.workout_tracker.dto;
import lombok.Data;

@Data
public class ExerciseProgress {
    private String exerciseName;
    private Integer totalReps;
    private Double maxWeight;
    private Integer workoutCount;
}
