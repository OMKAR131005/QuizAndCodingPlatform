package org.pm.quizapp.QuetionModule.dto;

import lombok.Data;
import org.pm.quizapp.QuetionModule.entity.Category;
import org.pm.quizapp.QuetionModule.entity.Difficulty;

import java.util.UUID;

@Data
public class ResponseQuestion {
    private String question;
    private String OptionA;
    private String OptionB;
    private String OptionC;
    private String OptionD;
    private UUID id;
    private Category category;
    private Difficulty difficulty;
}
