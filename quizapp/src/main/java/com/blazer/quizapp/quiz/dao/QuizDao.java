package com.blazer.quizapp.quiz.dao;

import com.blazer.quizapp.quiz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
    @author Vivek Rai
    created on 15/10/23
*/
@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {
}
