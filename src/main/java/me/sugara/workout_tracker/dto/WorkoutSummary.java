package me.sugara.workout_tracker.dto;
import lombok.Data;
import java.util.List;

@Data
public class WorkoutSummary {
    private Long workoutId;
    private String notes;
    private String date;
    private List<WorkoutExerciseResponse> exercises;
}