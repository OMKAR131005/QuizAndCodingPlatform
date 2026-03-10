package org.pm.quizapp.quizModule.controller;

import lombok.RequiredArgsConstructor;
import org.pm.quizapp.quizModule.dto.QuizResponseDto;
import org.pm.quizapp.quizModule.dto.Requestdto;
import org.pm.quizapp.quizModule.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/quiz")
@RestController
public class QuizController{
    private final QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody Requestdto requestdto){
       return  ResponseEntity.ok( quizService.createQuestion(requestdto));
    }
    @GetMapping("/getquiz/{id}")
    public ResponseEntity <QuizResponseDto> getQuiz(@PathVariable Long id ){
        return ResponseEntity.ok( quizService.getQuiz(id));
    }

}
