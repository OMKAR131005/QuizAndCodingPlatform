package org.pm.quizapp.QuetionModule.mapper;

import jakarta.servlet.http.HttpServletRequest;
import org.pm.quizapp.QuetionModule.dto.RequestProblem;
import org.pm.quizapp.QuetionModule.dto.RequestQuestion;
import org.pm.quizapp.QuetionModule.dto.ResponseProblem;
import org.pm.quizapp.QuetionModule.dto.ResponseQuestion;
import org.pm.quizapp.QuetionModule.entity.CodingQuestion;
import org.pm.quizapp.QuetionModule.entity.Question;
import org.pm.quizapp.QuetionModule.entity.TestCase;
import org.pm.quizapp.authentication.service.JwtUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class MapperAndUtil {
    public static ResponseQuestion QuestionToResponse(Question question) {
        ResponseQuestion responseQuestion = new ResponseQuestion();
        responseQuestion.setQuestion(question.getQuetion());
        responseQuestion.setOptionA(question.getOptionA());
        responseQuestion.setOptionB(question.getOptionB());
        responseQuestion.setOptionC(question.getOptionC());
        responseQuestion.setOptionD(question.getOptionD());
        responseQuestion.setId(question.getId());
        responseQuestion.setCategory(question.getCategory());
        responseQuestion.setDifficulty(question.getDifficulty());
        return responseQuestion;
    }
    public static Question RequestQuestionToQuestion(RequestQuestion requestQuestion,HttpServletRequest httpServletRequest){
        Question question=new Question();
        question.setQuetion(requestQuestion.getQuestion());
        question.setOptionA(requestQuestion.getOptionA());
        question.setOptionB(requestQuestion.getOptionB());
        question.setOptionC(requestQuestion.getOptionC());
        question.setQuetion(requestQuestion.getQuestion());
        question.setOptionD(requestQuestion.getOptionD());
        question.setPublic(requestQuestion.isPublic());
        question.setDifficulty(requestQuestion.getDifficulty());
        question.setCategory(requestQuestion.getCategory());
        question.setCreatedAt(LocalDateTime.now());
        question.setCorrectAnswer(requestQuestion.getCorrectAnswer());
        question.setCreatedBy(userId(httpServletRequest));
        return question;
    }
    public static Question update(Question question,RequestQuestion requestQuestion){
        question.setQuetion(requestQuestion.getQuestion());
        question.setOptionA(requestQuestion.getOptionA());
        question.setOptionB(requestQuestion.getOptionB());
        question.setQuetion(requestQuestion.getQuestion());
        question.setOptionD(requestQuestion.getOptionD());
        question.setPublic(requestQuestion.isPublic());
        question.setDifficulty(requestQuestion.getDifficulty());
        question.setCategory(requestQuestion.getCategory());
        question.setCreatedAt(LocalDateTime.now());
        question.setCorrectAnswer(requestQuestion.getCorrectAnswer());
        return question;
    }
    public static CodingQuestion requestToCodingQuestion(RequestProblem requestProblem,HttpServletRequest httpServletRequest) {
        UUID id=MapperAndUtil.userId(httpServletRequest);
        CodingQuestion codingQuestion = new CodingQuestion();

        codingQuestion.setTitle(requestProblem.getTitle());

        codingQuestion.setDescription(requestProblem.getDescription());

        codingQuestion.setDifficulty(requestProblem.getDifficulty());

        codingQuestion.setInputFormat(requestProblem.getInputFormat());

        codingQuestion.setOutputFormat(requestProblem.getOutputFormat());

        codingQuestion.setExamples(requestProblem.getExamples());

        codingQuestion.setConstraints(requestProblem.getConstraints());

        codingQuestion.setTags(requestProblem.getTags());

        codingQuestion.setActive(requestProblem.getActive());

        codingQuestion.setTimeLimit(requestProblem.getTimeLimit());

        codingQuestion.setMemoryLimit(requestProblem.getMemoryLimit());

        codingQuestion.setCreatedAt(LocalDateTime.now());
        codingQuestion.setCreatedBy(id);

        // TestCase Mapping
        List<TestCase> testCases = requestProblem.getTestCases()
                .stream()
                .map(tc -> {

                    TestCase testCase = new TestCase();

                    testCase.setInput(tc.getInput());

                    testCase.setExpectedOutput(tc.getOutput());

                    testCase.setHidden(tc.isHidden());

                    testCase.setCodingQuestion(codingQuestion);

                    return testCase;
                })
                .toList();

        codingQuestion.setTestCases(testCases);

        return codingQuestion;
    }
    public static UUID userId(HttpServletRequest httpServletRequest){
        String header = httpServletRequest.getHeader("Authorization");
        String token = header.substring(7);
        UUID userId = JwtUtil.extractUserId(token);
        return userId;
    }
}
