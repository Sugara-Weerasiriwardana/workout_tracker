package me.sugara.workout_tracker.controller;

import me.sugara.workout_tracker.dto.WorkoutRequest;
import me.sugara.workout_tracker.dto.WorkoutResponse;
import me.sugara.workout_tracker.service.WorkoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
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

    @PostMapping
    public ResponseEntity<WorkoutResponse> createWorkout(@RequestBody WorkoutRequest request) {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(workoutService.createWorkout(userId, request));
    }

    @GetMapping
    public ResponseEntity<List<WorkoutResponse>> getUserWorkouts() {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(workoutService.getUserWorkouts(userId));
    }

   
    @PutMapping("/{id}")
    public ResponseEntity<WorkoutResponse> updateWorkout(
            @PathVariable Long id,
            @RequestBody WorkoutRequest request) {

        Long userId = getCurrentUserId();
        return ResponseEntity.ok(workoutService.updateWorkout(userId, id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWorkout(@PathVariable Long id) {
        Long userId = getCurrentUserId();
        workoutService.deleteWorkout(userId, id);
        return ResponseEntity.ok("Workout deleted successfully");
    }

}