package com.blazer.quizapp.quiz.model;

import com.blazer.quizapp.question.model.Question;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/*
    @author Vivek Rai
    created on 15/10/23
*/
@Entity
@Data
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @ManyToMany
    private List<Question> questions;
}
