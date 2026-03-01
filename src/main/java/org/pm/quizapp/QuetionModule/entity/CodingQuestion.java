package org.pm.quizapp.QuetionModule.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "coding_questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CodingQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String difficulty; // "EASY", "MEDIUM", "HARD"

    @Lob
    private String inputFormat;

    @Lob
    private String outputFormat;

    @Lob
    private String examples; // You can store JSON string or plain text

    @Lob
    private String constraints;

    @ElementCollection
    @CollectionTable(name = "coding_question_tags", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "tag")
    private Set<String> tags;

    @Lob
    private String solution; // optional reference solution

    private Integer maxScore;

    private Boolean isActive = true;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "codingQuestion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestCase> testCases;
}