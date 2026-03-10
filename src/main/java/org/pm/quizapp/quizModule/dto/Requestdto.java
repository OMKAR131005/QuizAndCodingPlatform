package org.pm.quizapp.quizModule.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.pm.quizapp.QuetionModule.entity.Question;
import org.pm.quizapp.quizModule.entity.EntryType;
import org.pm.quizapp.quizModule.entity.Mode;
import org.pm.quizapp.quizModule.entity.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class Requestdto {
    private String title;
    private String description;
    private int price;
    private EntryType entryType;
    private Mode mode;
    private Status status;
    private int entryFees;
    @NotEmpty
    private List<UUID> questionList;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
