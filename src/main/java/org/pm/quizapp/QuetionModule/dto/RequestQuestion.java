package org.pm.quizapp.QuetionModule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.pm.quizapp.QuetionModule.entity.Category;
import org.pm.quizapp.QuetionModule.entity.Difficulty;

@Data
public class RequestQuestion {
    @NotBlank
    private String question;
    @NotBlank
    private String optionA;
    @NotBlank
    private String optionB;
    @NotBlank
    private String optionC;
    @NotBlank
    private String optionD;
    @NotBlank
    private String correctAnswer;
    @NotNull
    private Category category;
    private boolean isPublic;
    @NotNull
    private Difficulty difficulty;
}
