package org.pm.quizapp.authentication.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class RegistrationRequest {
    @NotBlank
    private String name;

    @NotBlank
    @NotNull
    private String username;

    @NotNull
    @NotBlank
    private String password;

    @Email
    private String email;

}
