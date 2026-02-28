package org.pm.quizapp.QuetionModule.excepetion.customException;

public class NotAPublicQuestion extends RuntimeException{
   public NotAPublicQuestion(String msg){
        super(msg);
    }
}
