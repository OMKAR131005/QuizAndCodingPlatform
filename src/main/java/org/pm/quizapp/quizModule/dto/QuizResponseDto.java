package org.pm.quizapp.quizModule.dto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.pm.quizapp.QuetionModule.dto.ResponseQuestion;
import org.pm.quizapp.quizModule.entity.EntryType;
import org.pm.quizapp.quizModule.entity.Mode;
import org.pm.quizapp.quizModule.entity.Status;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuizResponseDto {

    private Long id;

    private String title;
    private String description;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private int price;
    private int entryFees;

    private Status status;
    private Mode mode;
    private EntryType entryType;
    @NotNull
    private List<ResponseQuestion> questions;

}
