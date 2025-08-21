package me.sugara.workout_tracker.controller;

import me.sugara.workout_tracker.dto.WorkoutReportResponse;
import me.sugara.workout_tracker.dto.WorkoutRequest;
import me.sugara.workout_tracker.dto.WorkoutResponse;
import me.sugara.workout_tracker.service.WorkoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
@Tag(name = "Workouts", description = "Manage user workout plans")
public class WorkoutController {

    private final WorkoutService workoutService;
    private final me.sugara.workout_tracker.repository.UserRepository userRepo;

    public WorkoutController(WorkoutService workoutService,
            me.sugara.workout_tracker.repository.UserRepository userRepo) {
        this.workoutService = workoutService;
        this.userRepo = userRepo;
    }

    // Get current logged-in user's ID from SecurityContext
    private Long getCurrentUserId() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepo.findByUsername(username).orElseThrow().getId();
    }

    @Operation(summary = "Create a new workout", description = "Adds a new workout plan for the logged-in user")
    @PostMapping
    public ResponseEntity<WorkoutResponse> createWorkout(@RequestBody WorkoutRequest request) {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(workoutService.createWorkout(userId, request));
    }

    @Operation(summary = "Get all workouts", description = "Retrieve all workout plans for the logged-in user")
    @GetMapping
    public ResponseEntity<List<WorkoutResponse>> getUserWorkouts() {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(workoutService.getUserWorkouts(userId));
    }

    @Operation(summary = "Update workout", description = "Update an existing workout plan")
    @PutMapping("/{id}")
    public ResponseEntity<WorkoutResponse> updateWorkout(
            @PathVariable Long id,
            @RequestBody WorkoutRequest request) {

        Long userId = getCurrentUserId();
        return ResponseEntity.ok(workoutService.updateWorkout(userId, id, request));
    }

    @Operation(summary = "Delete workout", description = "Delete a workout plan by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWorkout(@PathVariable Long id) {
        Long userId = getCurrentUserId();
        workoutService.deleteWorkout(userId, id);
        return ResponseEntity.ok("Workout deleted successfully");
    }

    @Operation(summary = "Generate report", description = "Get past workouts and progress summary")
    @GetMapping("/report")
    public ResponseEntity<WorkoutReportResponse> getWorkoutReport() {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(workoutService.generateReport(userId));
    }

}