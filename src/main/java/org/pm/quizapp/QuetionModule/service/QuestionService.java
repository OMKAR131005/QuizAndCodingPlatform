package org.pm.quizapp.QuetionModule.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.pm.quizapp.QuetionModule.dto.ResponseQuestion;
import org.pm.quizapp.QuetionModule.entity.Category;
import org.pm.quizapp.QuetionModule.entity.Difficulty;
import org.pm.quizapp.QuetionModule.entity.Question;
import org.pm.quizapp.QuetionModule.excepetion.customException.NotAPublicQuestion;
import org.pm.quizapp.QuetionModule.mapper.MapperAndUtil;
import org.pm.quizapp.QuetionModule.repository.QuetionRepository;
import org.pm.quizapp.authentication.service.JwtUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
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
        UUID userId= MapperAndUtil.userId(httpServletRequest);
        if(!question.get().isPublic()&&!question.get().getCreatedBy().equals(userId)) throw new NotAPublicQuestion("this question is not public question");
        return MapperAndUtil.QuestionToResponse(question.get());
    }

    public Page<ResponseQuestion> getQuestions(Pageable pageable) {
        return questionRepository
                .findByIsPublicTrue(pageable)
                .map(MapperAndUtil::QuestionToResponse);
    }
    public Page <ResponseQuestion>getMyQuestion(Pageable pageable,UUID id){
        return  questionRepository.findByCreatedBy(id,pageable).map(MapperAndUtil::QuestionToResponse);
    }
    public Page<ResponseQuestion>getByDifficulty(Difficulty difficulty, Pageable pageable){
        return questionRepository.findByDifficulty(difficulty,pageable).map(MapperAndUtil::QuestionToResponse);
    }
    public Page<ResponseQuestion> getByCategory(Category category,Pageable pageable){
        return questionRepository.findByCategory(category,pageable).map(MapperAndUtil::QuestionToResponse);
    }
    public Page<ResponseQuestion> getMyQuestionByDifficulty(Difficulty difficulty,UUID id,Pageable pageable){
        return  questionRepository.findByCreatedByAndDifficulty(id,difficulty,pageable).map(MapperAndUtil::QuestionToResponse);
    }
    public Page<ResponseQuestion> getMyQuestionByCategory(Category category,UUID id,Pageable pageable){
        return  questionRepository.findByCreatedByAndCategory(id,category,pageable).map(MapperAndUtil::QuestionToResponse);
    }

    public Page<ResponseQuestion> search(String keyword1,UUID id,Pageable pageable){
        return questionRepository.findByQuetionContainingIgnoreCaseAndIsPublicTrueOrQuetionContainingIgnoreCaseAndCreatedBy(keyword1,keyword1,id,pageable).map(MapperAndUtil::QuestionToResponse);
    }
    public Page<ResponseQuestion> search(
            String keyword,
            Difficulty difficulty,
            Category category,
            UUID userId,
            Pageable pageable){
        if(keyword != null && difficulty == null && category == null){
            return questionRepository
                    .findByQuetionContainingIgnoreCaseAndIsPublicTrueOrQuetionContainingIgnoreCaseAndCreatedBy(
                            keyword,
                            keyword,
                            userId,
                            pageable).map(MapperAndUtil::QuestionToResponse);
        }


        if(keyword == null && difficulty != null){
            return questionRepository
                    .findByDifficultyAndIsPublicTrueOrDifficultyAndCreatedBy(
                            difficulty,
                            difficulty,
                            userId,
                            pageable).map(MapperAndUtil::QuestionToResponse);
        }


        if(keyword == null && category != null){
            return questionRepository
                    .findByCategoryAndIsPublicTrueOrCategoryAndCreatedBy(
                            category,
                            category,
                            userId,
                            pageable).map(MapperAndUtil::QuestionToResponse);
        }
        if(keyword == null && difficulty == null && category == null){
            return questionRepository
                    .findByIsPublicTrueOrCreatedBy(
                            userId,
                            pageable).map(MapperAndUtil::QuestionToResponse);
        }
        return questionRepository
                .findByIsPublicTrueOrCreatedBy(
                        userId,
                        pageable).map(MapperAndUtil::QuestionToResponse);
    }
    public String deleteQuestion(UUID id){
       Optional<Question> question=questionRepository.findById(id);
       question.orElseThrow(()->new NoSuchElementException("question with this id not found"));
       questionRepository.deleteById(id);
       return "Deleted successfully";
    }
    public Question getQuestionById(UUID id){

        Optional<Question> question = questionRepository.findById(id);
        question.orElseThrow(()->new NoSuchElementException("no question with this id is found"));
        return question.get();
    }

}
