package com.quiz.quizservice.controller;

import com.quiz.quizservice.model.QuestionWrapper;
import com.quiz.quizservice.model.QuizDto;
import com.quiz.quizservice.model.Response;
import com.quiz.quizservice.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping(path = "/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        return quizService.createQuize(quizDto.getCategory(),quizDto.getNumQ(),quizDto.getTitle());
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuesion(@PathVariable Integer id){
        return quizService.getQuizQuestion(id);
    }

    @PostMapping(path = "/submit/{id}")
    public ResponseEntity<Integer> sumbitQuiz(@PathVariable Integer id, @RequestBody List<Response> response){
        return quizService.sumbitQuiz(id,response);
    }

}
