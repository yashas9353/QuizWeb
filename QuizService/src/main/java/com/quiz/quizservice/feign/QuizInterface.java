package com.quiz.quizservice.feign;

import com.quiz.quizservice.model.QuestionWrapper;
import com.quiz.quizservice.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTIONSERVICE")
public interface QuizInterface {

    @GetMapping(path = "question/generate")
    public ResponseEntity<List<Integer>> generateQuestions(@RequestParam String category, @RequestParam Integer numQ);

    @PostMapping(path = "question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestBody List<Integer> questionIds);

    @PostMapping(path = "question/getscore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
