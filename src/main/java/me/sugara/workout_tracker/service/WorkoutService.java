package me.sugara.workout_tracker.service;

import me.sugara.workout_tracker.dto.*;
import me.sugara.workout_tracker.entity.*;
import me.sugara.workout_tracker.repository.*;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepo;
    private final WorkoutExerciseRepository workoutExerciseRepo;
    private final ExerciseRepository exerciseRepo;
    private final UserRepository userRepo;

    public WorkoutService(WorkoutRepository workoutRepo, WorkoutExerciseRepository workoutExerciseRepo,
            ExerciseRepository exerciseRepo, UserRepository userRepo) {
        this.workoutRepo = workoutRepo;
        this.workoutExerciseRepo = workoutExerciseRepo;
        this.exerciseRepo = exerciseRepo;
        this.userRepo = userRepo;
    }

    public WorkoutResponse createWorkout(Long userId, WorkoutRequest request) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Workout workout = Workout.builder()
                .user(user)
                .scheduledAt(request.getScheduledAt())
                .notes(request.getNotes())
                .build();

        workoutRepo.save(workout);

        List<WorkoutExercise> workoutExercises = request.getExercises().stream().map(ex -> {
            Exercise exercise = exerciseRepo.findById(ex.getExerciseId())
                    .orElseThrow(() -> new RuntimeException("Exercise not found"));
            return WorkoutExercise.builder()
                    .workout(workout)
                    .exercise(exercise)
                    .sets(ex.getSets())
                    .reps(ex.getReps())
                    .weight(ex.getWeight())
                    .build();
        }).collect(Collectors.toList());

        workoutExerciseRepo.saveAll(workoutExercises);
        workout.setWorkoutExercises(workoutExercises);

        return mapToResponse(workout);
    }

    public List<WorkoutResponse> getUserWorkouts(Long userId) {
        return workoutRepo.findByUserId(userId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private WorkoutResponse mapToResponse(Workout workout) {
        WorkoutResponse response = new WorkoutResponse();
        response.setId(workout.getId());
        response.setScheduledAt(workout.getScheduledAt());
        response.setNotes(workout.getNotes());
        response.setExercises(workout.getWorkoutExercises().stream().map(we -> {
            WorkoutExerciseResponse wr = new WorkoutExerciseResponse();
            wr.setExerciseName(we.getExercise().getName());
            wr.setSets(we.getSets());
            wr.setReps(we.getReps());
            wr.setWeight(we.getWeight());
            return wr;
        }).collect(Collectors.toList()));
        return response;
    }

  

@Transactional
public WorkoutResponse updateWorkout(Long userId, Long workoutId, WorkoutRequest request) {
    Workout workout = workoutRepo.findById(workoutId)
            .orElseThrow(() -> new RuntimeException("Workout not found"));

    // Ensure workout belongs to the logged-in user
    if (!workout.getUser().getId().equals(userId)) {
        throw new RuntimeException("Unauthorized to update this workout");
    }

    // Update workout details
    workout.setScheduledAt(request.getScheduledAt());
    workout.setNotes(request.getNotes());

    // Clear existing exercises to trigger orphan removal
    workout.getWorkoutExercises().clear();

    // Add new exercises
    List<WorkoutExercise> newExercises = request.getExercises().stream().map(ex -> {
        Exercise exercise = exerciseRepo.findById(ex.getExerciseId())
                .orElseThrow(() -> new RuntimeException("Exercise not found"));
        return WorkoutExercise.builder()
                .workout(workout)
                .exercise(exercise)
                .sets(ex.getSets())
                .reps(ex.getReps())
                .weight(ex.getWeight())
                .build();
    }).collect(Collectors.toList());

    workout.getWorkoutExercises().addAll(newExercises);

    // Save workout (Hibernate will handle cascade and orphan removal)
    workoutRepo.save(workout);

    return mapToResponse(workout);
}

    public void deleteWorkout(Long userId, Long workoutId) {
        Workout workout = workoutRepo.findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout not found"));

        if (!workout.getUser().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized to delete this workout");
        }

        workoutRepo.delete(workout);
    }
}