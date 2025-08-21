package me.sugara.workout_tracker.dto;

import lombok.Data;
import java.util.List;

@Data
public class WorkoutReportResponse {
    private List<WorkoutSummary> pastWorkouts;
    private List<ExerciseProgress> progress;
}