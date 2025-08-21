package me.sugara.workout_tracker.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
public OpenAPI workoutTrackerAPI() {
    return new OpenAPI()
            .info(new Info()
                    .title("Workout Tracker API")
                    .description("API documentation for the Workout Tracker application")
                    .version("1.0"))
            .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement().addList("BearerAuth"))
            .components(new io.swagger.v3.oas.models.Components()
                    .addSecuritySchemes("BearerAuth",
                            new io.swagger.v3.oas.models.security.SecurityScheme()
                                    .name("Authorization")
                                    .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
                                    .scheme("bearer")
                                    .bearerFormat("JWT")));
}

}