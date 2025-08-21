package me.sugara.workout_tracker.dto;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String username;

    public AuthResponse(String token, String username) {
        this.token = token;
        this.username = username;
    }
}
