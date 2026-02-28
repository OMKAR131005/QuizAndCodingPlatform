package org.pm.quizapp.authentication.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank
    @NotNull
    private String username;

    @NotNull
    @NotBlank
    private String password;
}
