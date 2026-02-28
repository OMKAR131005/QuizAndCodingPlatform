package org.pm.quizapp.QuetionModule.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.pm.quizapp.QuetionModule.dto.QuestionPost;
import org.pm.quizapp.QuetionModule.dto.ResponseQuestion;
import org.pm.quizapp.QuetionModule.entity.Category;
import org.pm.quizapp.QuetionModule.entity.Difficulty;
import org.pm.quizapp.QuetionModule.entity.Question;
import org.pm.quizapp.QuetionModule.excepetion.customException.NotAPublicQuestion;
import org.pm.quizapp.QuetionModule.repository.QuetionRepository;
import org.pm.quizapp.authentication.service.JwtUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuetionRepository questionRepository;

    public boolean addQuestion(@Valid Question question){
        Set<String>set=new HashSet<>();
        set.add(question.getOptionA());
        set.add(question.getOptionB());
        set.add(question.getOptionC());
        set.add(question.getOptionD());
        if(set.size()<4)return false;
        if(!set.contains(question.getCorrectAnswer()))return false;
        questionRepository.save(question);
        return true;
    }
    public ResponseQuestion getQuestionById(@PathVariable UUID id, HttpServletRequest httpServletRequest){
        Optional<Question>question=questionRepository.findById(id);
        question.orElseThrow(()->new NoSuchElementException("question with id"+id+"does not found"));
        String header = httpServletRequest.getHeader("Authorization");
        String token = header.substring(7);
        UUID userId = JwtUtil.extractUserId(token);
        if(!question.get().isPublic()&&!question.get().getCreatedBy().equals(userId)) throw new NotAPublicQuestion("this question is not public question");
        ResponseQuestion questionResponse=new ResponseQuestion();
        questionResponse.setQuestion(question.get().getQuetion());
        questionResponse.setOptionA(question.get().getOptionA());
        questionResponse.setOptionB(question.get().getOptionB());
        questionResponse.setOptionC(question.get().getOptionC());
        questionResponse.setOptionD(question.get().getOptionD());
        return questionResponse;
    }

    public Page<Question> getQuestions(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return questionRepository.findByIsPublicTrue(pageable);
    }
    public Page <Question>getMyQuestion(Pageable pageable,UUID id){
        //Pageable pageable =PageRequest.of(page,size);
        return  questionRepository.findByCreatedBy(id,pageable);
    }
    public Page<Question>getByDifficulty(Difficulty difficulty, Pageable pageable){
        return questionRepository.findByDifficulty(difficulty,pageable);
    }
    public Page<Question> getByCategory(Category category,Pageable pageable){
        return questionRepository.findByCategory(category,pageable);
    }
    public Page<Question> getMyQuestionByDifficulty(Difficulty difficulty,UUID id,Pageable pageable){
        return  questionRepository.findByCreatedByAndDifficulty(id,difficulty,pageable);
    }
    public Page<Question> getMyQuestionByCategory(Category category,UUID id,Pageable pageable){
        return  questionRepository.findByCreatedByAndCategory(id,category,pageable);
    }

    public Page<Question> search(String keyword1,UUID id,Pageable pageable){
        return questionRepository.findByQuetionContainingIgnoreCaseAndIsPublicTrueOrQuetionContainingIgnoreCaseAndCreatedBy(keyword1,keyword1,id,pageable);
    }



}
