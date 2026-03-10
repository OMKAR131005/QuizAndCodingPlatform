package org.pm.quizapp.quizModule.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pm.quizapp.QuetionModule.entity.Question;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String description;

    private String title;
    private LocalDateTime startTime;
    private  LocalDateTime endTIme;
    private int price;
    private int entryFees;
    @Enumerated(EnumType.STRING)
    private Status status;//("ended , started, upcoming")
    @Enumerated(EnumType.STRING)
    private Mode mode;//("live or static")
    @Enumerated(EnumType.STRING)
    private EntryType entryType;//("paid or free")
    @ManyToMany
    @JoinTable(
            name = "quiz_question",
            joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private List<Question> questions;


}
