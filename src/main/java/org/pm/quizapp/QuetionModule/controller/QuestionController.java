package org.pm.quizapp.QuetionModule.controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pm.quizapp.QuetionModule.dto.RequestQuestion;
import org.pm.quizapp.QuetionModule.dto.ResponseQuestion;
import org.pm.quizapp.QuetionModule.entity.Category;
import org.pm.quizapp.QuetionModule.entity.Difficulty;
import org.pm.quizapp.QuetionModule.entity.Question;
import org.pm.quizapp.QuetionModule.mapper.MapperAndUtil;
import org.pm.quizapp.QuetionModule.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
@Slf4j
public class QuestionController {
    private final QuestionService questionService;
    @PostMapping("/add")
    public ResponseEntity<Boolean> addQuestion(@Valid @RequestBody RequestQuestion questionPost, HttpServletRequest httpServletRequest){
      Question question=MapperAndUtil.RequestQuestionToQuestion(questionPost,httpServletRequest);
        return ResponseEntity.ok(
                questionService.addQuestion(question));
    }
    @GetMapping("/hello")
    public String hello(){
        return "working";
    }

    @GetMapping
    public Page<ResponseQuestion> getUsers(Pageable pageable) {

        return questionService.getQuestions(pageable);
    }

    @GetMapping("/{id}")
    public ResponseQuestion getQuestionById(
            @PathVariable UUID id,HttpServletRequest httpServletRequest){
        return questionService.getQuestionById(id,httpServletRequest);
    }
    @PostMapping("/add/all")
    public ResponseEntity<Boolean> addQuestions(
            @Valid @RequestBody List<RequestQuestion> questionPost, HttpServletRequest httpServletRequest){
        for(RequestQuestion question:questionPost){
            questionService.addQuestion(MapperAndUtil.RequestQuestionToQuestion(question,httpServletRequest));
        }
         return ResponseEntity.ok(true);
    }

    @GetMapping("/myquestion")
    public ResponseEntity<Page<ResponseQuestion>> myQuestion(HttpServletRequest httpServletRequest,Pageable pageable){
        UUID id= MapperAndUtil.userId(httpServletRequest);
        return ResponseEntity.ok(questionService.getMyQuestion(pageable,id));
    }
    @GetMapping("/difficulty")
    public ResponseEntity<Page<ResponseQuestion>> getByDifficulty(@RequestParam Difficulty difficulty,Pageable pageable){
       return ResponseEntity.ok(questionService.getByDifficulty(difficulty,pageable));
    }

    @GetMapping("/category")
    public ResponseEntity<Page<ResponseQuestion>> getByCategory(@RequestParam Category category,Pageable pageable){
        return ResponseEntity.ok(questionService.getByCategory(category,pageable));
    }

    @GetMapping("/myquestion/difficulty")
    public ResponseEntity<Page<ResponseQuestion>>getMyQuestionBydifficulty(@RequestParam Difficulty difficulty,HttpServletRequest httpServletRequest, Pageable pageable ){
        UUID id=MapperAndUtil.userId(httpServletRequest);
        return ResponseEntity.ok( questionService.getMyQuestionByDifficulty(difficulty,id,pageable));

    }
    @GetMapping("/myquestion/category")
    public ResponseEntity<Page<ResponseQuestion>>getMyQuestionByCategory(@RequestParam Category category,HttpServletRequest httpServletRequest ,Pageable pageable){
        UUID id= MapperAndUtil.userId(httpServletRequest);
        return ResponseEntity.ok( questionService.getMyQuestionByCategory(category,id,pageable));
    }
    @GetMapping("/search")
    public ResponseEntity<Page<ResponseQuestion>>search(@RequestParam(required = false) String keyword1,@RequestParam(required = false)Difficulty difficulty,@RequestParam(required = false)Category category,HttpServletRequest httpServletRequest,Pageable pageable){
        UUID id= MapperAndUtil.userId(httpServletRequest);
        return ResponseEntity.ok(questionService.search(keyword1,difficulty,category,id,pageable));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String>deleteQuestion(@RequestParam UUID id){
      return ResponseEntity.ok(questionService.deleteQuestion(id));
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateQuestion(
            @RequestParam UUID id,
            @RequestBody RequestQuestion questionPost) {
        Question responseQuestion = questionService.getQuestionById(id);
        Question question =MapperAndUtil.update(responseQuestion,questionPost);
        questionService.addQuestion(question);
        return ResponseEntity.ok("Question updated successfully");
    }
}