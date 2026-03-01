package org.pm.quizapp.QuetionModule.mapper;

import jakarta.servlet.http.HttpServletRequest;
import org.pm.quizapp.QuetionModule.dto.RequestQuestion;
import org.pm.quizapp.QuetionModule.dto.ResponseQuestion;
import org.pm.quizapp.QuetionModule.entity.Question;
import org.pm.quizapp.authentication.service.JwtUtil;

import java.time.LocalDateTime;
import java.util.UUID;

public class MapperAndUtil {
    public static ResponseQuestion QuestionToResponse(Question question) {
        ResponseQuestion responseQuestion = new ResponseQuestion();
        responseQuestion.setQuestion(question.getQuetion());
        responseQuestion.setOptionA(question.getOptionA());
        responseQuestion.setOptionB(question.getOptionB());
        responseQuestion.setOptionC(question.getOptionC());
        responseQuestion.setOptionD(question.getOptionD());
        responseQuestion.setId(question.getId());
        responseQuestion.setCategory(question.getCategory());
        responseQuestion.setDifficulty(question.getDifficulty());
        return responseQuestion;
    }
    public static Question RequestQuestionToQuestion(RequestQuestion requestQuestion,HttpServletRequest httpServletRequest){
        Question question=new Question();
        question.setQuetion(requestQuestion.getQuestion());
        question.setOptionA(requestQuestion.getOptionA());
        question.setOptionB(requestQuestion.getOptionB());
        question.setOptionC(requestQuestion.getOptionC());
        question.setQuetion(requestQuestion.getQuestion());
        question.setOptionD(requestQuestion.getOptionD());
        question.setPublic(requestQuestion.isPublic());
        question.setDifficulty(requestQuestion.getDifficulty());
        question.setCategory(requestQuestion.getCategory());
        question.setCreatedAt(LocalDateTime.now());
        question.setCorrectAnswer(requestQuestion.getCorrectAnswer());
        question.setCreatedBy(userId(httpServletRequest));
        return question;
    }
    public static Question update(Question question,RequestQuestion requestQuestion){
        question.setQuetion(requestQuestion.getQuestion());
        question.setOptionA(requestQuestion.getOptionA());
        question.setOptionB(requestQuestion.getOptionB());
        question.setQuetion(requestQuestion.getQuestion());
        question.setOptionD(requestQuestion.getOptionD());
        question.setPublic(requestQuestion.isPublic());
        question.setDifficulty(requestQuestion.getDifficulty());
        question.setCategory(requestQuestion.getCategory());
        question.setCreatedAt(LocalDateTime.now());
        question.setCorrectAnswer(requestQuestion.getCorrectAnswer());
        return question;
    }
    public static UUID userId(HttpServletRequest httpServletRequest){
        String header = httpServletRequest.getHeader("Authorization");
        String token = header.substring(7);
        UUID userId = JwtUtil.extractUserId(token);
        return userId;
    }
}
