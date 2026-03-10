package org.pm.quizapp.quizModule.service;

import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.pm.quizapp.QuetionModule.entity.Question;
import org.pm.quizapp.QuetionModule.repository.QuetionRepository;
import org.pm.quizapp.quizModule.ExceptionQuiz.QuizNotFoundException;
import org.pm.quizapp.quizModule.Mapper.MapperClass;
import org.pm.quizapp.quizModule.dto.QuizResponseDto;
import org.pm.quizapp.quizModule.dto.Requestdto;
import org.pm.quizapp.quizModule.entity.Quiz;
import org.pm.quizapp.quizModule.repository.QuizRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuizService {
private final QuizRepository quizRepository;
private final QuetionRepository quetionRepository;
    public String createQuestion(Requestdto quizDto){
        try{
            Quiz quiz= MapperClass.quiz(quizDto);
            System.out.println("Question IDs: " + quizDto.getQuestionList());
            if(quizDto.getQuestionList() == null || quizDto.getQuestionList().isEmpty()){
                throw new RuntimeException("Question list cannot be empty");
            }
            System.out.println(quizDto.getQuestionList());

           // List<Question> questions = questionRepository.findAllById(requestdto.getQuestionList());
            List<Question> questions = quetionRepository.findAllById(quizDto.getQuestionList());
            quiz.setQuestions(questions);
            quizRepository.save(quiz);
            quizRepository.save(quiz);
         }catch (RuntimeException e) {
            throw new RuntimeException(e);

        }
        return "quiz created";
    }
    @Transactional
    public QuizResponseDto getQuiz(Long id){
       Optional<Quiz> quiz= quizRepository.findById(id);
       quiz.orElseThrow(()-> new QuizNotFoundException("quiz with id "+id +"not found"));
       return MapperClass.toResponseDto(quiz.get());
    }

}
