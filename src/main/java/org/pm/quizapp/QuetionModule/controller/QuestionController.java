package org.pm.quizapp.QuetionModule.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pm.quizapp.QuetionModule.dto.QuestionPost;
import org.pm.quizapp.QuetionModule.dto.ResponseQuestion;
import org.pm.quizapp.QuetionModule.entity.Category;
import org.pm.quizapp.QuetionModule.entity.Difficulty;
import org.pm.quizapp.QuetionModule.entity.Question;
import org.pm.quizapp.QuetionModule.service.QuestionService;
import org.pm.quizapp.authentication.service.JwtUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
@Slf4j
public class QuestionController {
    private final QuestionService questionService;
    @PostMapping("/add")
    public ResponseEntity<Boolean> addQuestion(@Valid @RequestBody QuestionPost questionPost, HttpServletRequest httpServletRequest){
        String header = httpServletRequest.getHeader("Authorization");
        String token = header.substring(7);
        UUID userId = JwtUtil.extractUserId(token);
        log.warn("enter add questions");
        Question question = new Question();
        question.setQuetion(questionPost.getQuestion());
        question.setCategory(questionPost.getCategory());
        question.setCreatedAt(LocalDateTime.now());
        question.setCorrectAnswer(questionPost.getCorrectAnswer());
        question.setOptionA(questionPost.getOptionA());
        question.setOptionB(questionPost.getOptionB());
        question.setOptionC(questionPost.getOptionC());
        question.setOptionD(questionPost.getOptionD());
        question.setCreatedBy(userId);
        question.setDifficulty(Difficulty.Basic);
        question.setPublic(questionPost.isPublic());
        return ResponseEntity.ok(
                questionService.addQuestion(question));
    }
    @GetMapping("/hello")
    public String hello(){
        return "working";
    }

    @GetMapping
    public Page<Question> getUsers(
            @RequestParam int page,
            @RequestParam int size) {

        return questionService.getQuestions(page, size);
    }

    @GetMapping("/{id}")
    public ResponseQuestion getQuestionById(
            @PathVariable UUID id,HttpServletRequest httpServletRequest){
        return questionService.getQuestionById(id,httpServletRequest);
    }
    @PostMapping("/add/all")
    public ResponseEntity<Boolean> addQuestions(
            @Valid @RequestBody List<QuestionPost> questionPost, HttpServletRequest httpServletRequest){
        String header = httpServletRequest.getHeader("Authorization");
        String token = header.substring(7);
        UUID userId = JwtUtil.extractUserId(token);

        for(QuestionPost question:questionPost){
            if(!question.isPublic()){
                System.out.println("this ok working");

            }
            if(question.isPublic()){
                System.out.println("this not ok working");

            }
            Question quiz = new Question();
            quiz.setQuetion(question.getQuestion());
            quiz.setCategory(question.getCategory());
            quiz.setCreatedAt(LocalDateTime.now());
            quiz.setCorrectAnswer(question.getCorrectAnswer());
            quiz.setOptionA(question.getOptionA());
            quiz.setOptionB(question.getOptionB());
            quiz.setOptionC(question.getOptionC());
            quiz.setOptionD(question.getOptionD());
            quiz.setCreatedBy(userId);
            quiz.setDifficulty(Difficulty.Basic);
            quiz.setPublic(question.isPublic());
            questionService.addQuestion(quiz);

        }

         return ResponseEntity.ok(true);
    }
    @GetMapping("/myquestion")
    public ResponseEntity<Page<Question>> myQuestion(HttpServletRequest httpServletRequest,Pageable pageable){
        String header=httpServletRequest.getHeader("Authorization");
        String token=header.substring(7);
        UUID id=JwtUtil.extractUserId(token);
        return ResponseEntity.ok(questionService.getMyQuestion(pageable,id));

    }
    @GetMapping("/difficulty")
    public ResponseEntity<Page<Question>> getByDifficulty(@RequestParam Difficulty difficulty,Pageable pageable){
        //Pageable pageable= PageRequest.of(page,size)
       return ResponseEntity.ok(questionService.getByDifficulty(difficulty,pageable));

    }
    @GetMapping("/category")
    public ResponseEntity<Page<Question>> getByCategory(@RequestParam Category category,Pageable pageable){
       // Pageable pageable= PageRequest.of(page,size);
        return ResponseEntity.ok(questionService.getByCategory(category,pageable));

    }
    @GetMapping("/myquestion/difficulty")
    public ResponseEntity<Page<Question>>getMyQuestionBydifficulty(@RequestParam Difficulty difficulty,HttpServletRequest httpServletRequest, Pageable pageable ){
        String header=httpServletRequest.getHeader("Authorization");
        String  token =header.substring(7);
        UUID id=JwtUtil.extractUserId(token);
        //Pageable pageable=PageRequest.of(page,size);
       return ResponseEntity.ok( questionService.getMyQuestionByDifficulty(difficulty,id,pageable));

    }
    @GetMapping("/myquestion/category")
    public ResponseEntity<Page<Question>>getMyQuestionByCategory(@RequestParam Category category,HttpServletRequest httpServletRequest ,Pageable pageable){
        String header=httpServletRequest.getHeader("Authorization");
        String  token =header.substring(7);
        UUID id=JwtUtil.extractUserId(token);
       // Pageable pageable=PageRequest.of(page,size);
        return ResponseEntity.ok( questionService.getMyQuestionByCategory(category,id,pageable));
    }
    @GetMapping("/search")
    public ResponseEntity<Page<Question>>search(@RequestParam String keyword1,HttpServletRequest httpServletRequest,Pageable pageable){
        String header=httpServletRequest.getHeader("Authorization");
        String  token =header.substring(7);
        UUID id=JwtUtil.extractUserId(token);
        return ResponseEntity.ok(questionService.search(keyword1,id,pageable));
    }



}