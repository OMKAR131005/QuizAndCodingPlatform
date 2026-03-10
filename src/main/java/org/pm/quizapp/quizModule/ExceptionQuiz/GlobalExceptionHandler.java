package org.pm.quizapp.quizModule.ExceptionQuiz;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(QuizNotFoundException.class)
    public ResponseEntity<ErrorResponse> quizNotFound(QuizNotFoundException ex){

      ErrorResponse error =new ErrorResponse(ex.getMessage(),404);
      return ResponseEntity.status(404).body(error);
    }
}
