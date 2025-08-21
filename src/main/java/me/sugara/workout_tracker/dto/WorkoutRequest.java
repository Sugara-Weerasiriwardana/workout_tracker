package me.sugara.workout_tracker.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class WorkoutRequest {
    private LocalDateTime scheduledAt;
    private String notes;
    private List<WorkoutExerciseRequest> exercises;
}
