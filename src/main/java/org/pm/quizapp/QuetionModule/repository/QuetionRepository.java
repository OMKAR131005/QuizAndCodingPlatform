package org.pm.quizapp.QuetionModule.repository;

import org.pm.quizapp.QuetionModule.entity.Category;
import org.pm.quizapp.QuetionModule.entity.Difficulty;
import org.pm.quizapp.QuetionModule.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface QuetionRepository extends JpaRepository<Question, UUID> {
    Page<Question> findByIsPublicTrue(Pageable pageable);
    Page<Question> findByCreatedBy(UUID userId, Pageable pageable);
    Page<Question> findByDifficulty(Difficulty difficulty,Pageable pageable);
    Page<Question> findByCategory(Category category,Pageable pageable);
    Page<Question> findByCreatedByAndDifficulty(UUID id,Difficulty difficulty,Pageable pageable);
    Page<Question> findByCreatedByAndCategory(UUID id,Category category,Pageable pageable);
    Page<Question>
    findByQuetionContainingIgnoreCaseAndIsPublicTrueOrQuetionContainingIgnoreCaseAndCreatedBy(
            String keyword1,
            String keyword2,
            UUID userId,
            Pageable pageable
    );

    Page<Question> findByCategoryAndIsPublicTrueOrCategoryAndCreatedBy(Category category, Category category1, UUID userId, Pageable pageable);

    Page<Question> findByIsPublicTrueOrCreatedBy(UUID userId, Pageable pageable);

    Page<Question> findByDifficultyAndIsPublicTrueOrDifficultyAndCreatedBy(Difficulty difficulty, Difficulty difficulty1, UUID userId, Pageable pageable);
}
