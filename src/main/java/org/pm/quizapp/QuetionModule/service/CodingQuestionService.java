package org.pm.quizapp.QuetionModule.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.hibernate.engine.spi.Resolution;
import org.pm.quizapp.QuetionModule.dto.RequestProblem;
import org.pm.quizapp.QuetionModule.entity.CodingQuestion;
import org.pm.quizapp.QuetionModule.mapper.MapperAndUtil;
import org.pm.quizapp.QuetionModule.repository.CodingQuestionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CodingQuestionService {
   private final CodingQuestionRepository codingQuestionRepository;
   public String createProblem(RequestProblem requestProblem, HttpServletRequest httpServletRequest){
      CodingQuestion codingQuestion = MapperAndUtil.requestToCodingQuestion(requestProblem,httpServletRequest);
      codingQuestionRepository.save(codingQuestion);
      return "Created";
   }
}
