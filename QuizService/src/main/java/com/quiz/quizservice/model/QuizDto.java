package com.quiz.quizservice.model;

import lombok.Data;

@Data
public class QuizDto {
    private String category;
    private Integer numQ;
    private String title;
}
