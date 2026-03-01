package org.pm.quizapp.QuetionModule.repository;

import org.pm.quizapp.QuetionModule.entity.CodingQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodingQuestionRepository extends JpaRepository<CodingQuestion,Long> {
}
