package org.pm.quizapp.quizModule.Mapper;

import org.pm.quizapp.QuetionModule.dto.ResponseQuestion;
import org.pm.quizapp.QuetionModule.mapper.MapperAndUtil;
import org.pm.quizapp.quizModule.dto.QuizResponseDto;
import org.pm.quizapp.quizModule.dto.Requestdto;
import org.pm.quizapp.quizModule.entity.Quiz;

import java.util.List;

public class MapperClass {
    public static Quiz quiz(Requestdto requestdto) {
        Quiz quiz = new Quiz();

        quiz.setTitle(requestdto.getTitle());
        quiz.setDescription(requestdto.getDescription());
        quiz.setStartTime(requestdto.getStartTime());
        quiz.setEndTIme(requestdto.getEndTime());

        quiz.setPrice(requestdto.getPrice());
        quiz.setEntryFees(requestdto.getEntryFees());

        quiz.setMode(requestdto.getMode());
        quiz.setStatus(requestdto.getStatus());
        quiz.setEntryType(requestdto.getEntryType());

        return quiz;
    }
    public static QuizResponseDto toResponseDto(Quiz quiz) {

        QuizResponseDto dto = new QuizResponseDto();

        dto.setId(quiz.getId());
        dto.setTitle(quiz.getTitle());
        dto.setDescription(quiz.getDescription());

        dto.setStartTime(quiz.getStartTime());
        dto.setEndTime(quiz.getEndTIme());

        dto.setPrice(quiz.getPrice());
        dto.setEntryFees(quiz.getEntryFees());

        dto.setStatus(quiz.getStatus());
        dto.setMode(quiz.getMode());
        dto.setEntryType(quiz.getEntryType());

        List<ResponseQuestion> questionDtos = quiz.getQuestions()
                .stream()
                .map(MapperAndUtil::QuestionToResponse)
                .toList();
        dto.setQuestions(questionDtos);
        return dto;
    }
}
