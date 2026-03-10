package org.pm.quizapp.quizModule.ExceptionQuiz;


public class QuizNotFoundException extends RuntimeException {
   public QuizNotFoundException(String msg){
        super(msg);
    }
}
