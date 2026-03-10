package org.pm.quizapp.QuetionModule.dto;



import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestCaseRequest {

    private String input;

    private String output;

    private boolean hidden;
}
