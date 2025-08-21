package me.sugara.workout_tracker.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;


@Data
public class WorkoutResponse {
    private Long id;
    private LocalDateTime scheduledAt;
    private String notes;
    private List<WorkoutExerciseResponse> exercises;
}