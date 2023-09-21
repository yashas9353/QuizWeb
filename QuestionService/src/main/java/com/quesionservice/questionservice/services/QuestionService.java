package com.quesionservice.questionservice.services;

import com.quesionservice.questionservice.model.Question;
import com.quesionservice.questionservice.model.QuestionWrapper;
import com.quesionservice.questionservice.model.Response;
import com.quesionservice.questionservice.repo.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>("Success",HttpStatus.CREATED);
    }

    public ResponseEntity<Optional<Question>> deleteQuestion(Integer id) {
        Optional<Question> q = questionDao.findById(id);
        questionDao.deleteById(id);
        return new ResponseEntity<>(q,HttpStatus.FOUND);
    }

    public ResponseEntity<List<Integer>> generateQuestions(String category, Integer numQ) {
        List<Integer> questionsid = questionDao.findRandomQuestionByCategory(category,numQ);
        return new ResponseEntity<>(questionsid,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuesionFromId(List<Integer> questionIds) {
        List<Question> questions = new ArrayList<>();
        List<QuestionWrapper> wrappers = new ArrayList<>();
        for(Integer q : questionIds){
            questions.add(questionDao.findById(q).get());
        }

        for(Question q : questions){
            QuestionWrapper wrapper = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            wrappers.add(wrapper);
        }
        return new ResponseEntity<>(wrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int right = 0;

        for(Response r : responses){
            Question question = questionDao.findById(r.getId()).get();
            if(r.getResponse().equals(question.getRightAnswer())){
                right++;
            }
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
