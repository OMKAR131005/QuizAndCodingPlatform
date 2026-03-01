package org.pm.quizapp.QuetionModule.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotBlank
    private String quetion;
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

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Category category;


    @Column(nullable = true)
    private Difficulty difficulty;
    private UUID createdBy;
    private LocalDateTime createdAt;
    private boolean isPublic;

}
