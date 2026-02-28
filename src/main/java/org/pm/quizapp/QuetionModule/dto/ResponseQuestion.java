package org.pm.quizapp.QuetionModule.dto;

import lombok.Data;

@Data
public class ResponseQuestion {
    private String question;
    private String OptionA;
    private String OptionB;
    private String OptionC;
    private String OptionD;
}
