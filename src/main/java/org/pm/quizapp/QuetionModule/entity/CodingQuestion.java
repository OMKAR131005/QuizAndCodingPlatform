package org.pm.quizapp.QuetionModule.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "coding_questions")
@Data
public class CodingQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Difficulty difficulty;

    @Lob
    private String inputFormat;

    @Lob
    private String outputFormat;

    @Lob
    private String examples;

    @Lob
    private String constraints;

    @ElementCollection
    @CollectionTable(
            name = "coding_question_tags",
            joinColumns = @JoinColumn(name = "question_id")
    )
    @Column(name = "tag")
    private Set<String> tags;

    @Lob
    private String solution;

    private Integer maxScore;

    @Column(name = "is_active")
    private Boolean active = true;

    private Integer timeLimit;

    private Integer memoryLimit;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(
            mappedBy = "codingQuestion",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<TestCase> testCases;

    private UUID createdBy;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}