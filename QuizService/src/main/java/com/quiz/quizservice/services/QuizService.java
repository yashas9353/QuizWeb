package com.quiz.quizservice.services;

import com.quiz.quizservice.feign.QuizInterface;
import com.quiz.quizservice.model.QuestionWrapper;
import com.quiz.quizservice.model.Quiz;
import com.quiz.quizservice.model.Response;
import com.quiz.quizservice.repo.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuize(String category, int numQ, String title) {
        List<Integer> questionIds = quizInterface.generateQuestions(category,numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questionIds);
        quizDao.save(quiz);
        return new ResponseEntity<>("Created Succesfully quizid = "+quiz.getId(), HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
        Quiz quiz = quizDao.findById(id).get();
        List<Integer> questionids = quiz.getQuestionIds();
        return quizInterface.getQuestionFromId(questionids);
    }

    public ResponseEntity<Integer> sumbitQuiz(Integer id, List<Response> response) {
        return quizInterface.getScore(response);
    }
}
