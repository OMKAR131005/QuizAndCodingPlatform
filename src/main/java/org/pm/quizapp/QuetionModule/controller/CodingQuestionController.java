package org.pm.quizapp.QuetionModule.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.pm.quizapp.QuetionModule.dto.RequestProblem;
import org.pm.quizapp.QuetionModule.service.CodingQuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/codingquestion")
public class CodingQuestionController {
private  final CodingQuestionService  codingQuestionService;
    @PostMapping("/add/problem")
    public ResponseEntity<String> createProblem(@RequestBody RequestProblem requestProblem, HttpServletRequest httpServletRequest){
       return ResponseEntity.ok(codingQuestionService.createProblem(requestProblem,httpServletRequest));
    }



}
