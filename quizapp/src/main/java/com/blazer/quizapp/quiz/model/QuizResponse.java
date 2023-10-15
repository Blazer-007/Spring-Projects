package com.blazer.quizapp.quiz.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/*
    @author Vivek Rai
    created on 15/10/23
*/
@Data
@RequiredArgsConstructor
public class QuizResponse {
    private Integer id;
    private String response;
}
