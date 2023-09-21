package com.quesionservice.questionservice.controller;

import com.quesionservice.questionservice.model.Question;
import com.quesionservice.questionservice.model.QuestionWrapper;
import com.quesionservice.questionservice.model.Response;
import com.quesionservice.questionservice.services.QuestionService;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping(path = "/allquestion")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping(path = "/category/{category}")
    public ResponseEntity<List<Question>> getQuestionCategory(@PathVariable("category") String category){
        return questionService.getQuestionCategory(category);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Optional<Question>> deleteQuestion(@PathVariable Integer id){
        return questionService.deleteQuestion(id);
    }

    @GetMapping(path = "/generate")
    public ResponseEntity<List<Integer>> generateQuestions(@RequestParam String category,@RequestParam Integer numQ){
        return questionService.generateQuestions(category,numQ);
    }

    @PostMapping(path = "/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestBody List<Integer> questionIds){
        return questionService.getQuesionFromId(questionIds);
    }

    @PostMapping(path = "/getscore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }
}
