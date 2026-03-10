package org.pm.quizapp.QuetionModule.dto;

import jakarta.persistence.*;
import lombok.Data;
import org.pm.quizapp.QuetionModule.entity.Difficulty;
import org.pm.quizapp.QuetionModule.entity.TestCase;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class RequestProblem{
   private String title;

   private String description;

   private Difficulty difficulty;

   private String inputFormat;

   private String outputFormat;

   private String examples;

   private String constraints;

   private Set<String> tags;

   private String solution;

   private Integer maxScore;

   private Boolean active;

   private Integer timeLimit;

   private Integer memoryLimit;

   private List<TestCaseRequest> testCases;

}

