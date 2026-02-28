package org.pm.quizapp.QuetionModule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.pm.quizapp.QuetionModule.entity.Category;

@Data
public class QuestionPost {
    @NotBlank
    @NotNull
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;
    private Category category;
    private boolean isPublic;


}
