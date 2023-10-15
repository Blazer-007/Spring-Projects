package com.blazer.quizapp.quiz.service;

import com.blazer.quizapp.question.dao.QuestionDao;
import com.blazer.quizapp.question.model.Question;
import com.blazer.quizapp.question.model.QuestionWrapper;
import com.blazer.quizapp.quiz.dao.QuizDao;
import com.blazer.quizapp.quiz.model.Quiz;
import com.blazer.quizapp.quiz.model.QuizResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
    @author Vivek Rai
    created on 15/10/23
*/
@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        try {
            List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);

            quizDao.save(quiz);

            return new ResponseEntity<>("Quiz Creation SUCCESS", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Quiz Creation Failed", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        try {
            Optional<Quiz> quiz = quizDao.findById(id);
            List<Question> questionsFromDB = quiz.get().getQuestions();
            List<QuestionWrapper> questionsForUser = new ArrayList<>();
            for (Question q : questionsFromDB) {
                QuestionWrapper questionWrapper = new QuestionWrapper(
                        q.getId(),
                        q.getQuestionTitle(),
                        q.getOption1(),
                        q.getOption2(),
                        q.getOption3(),
                        q.getOption4()
                );
                questionsForUser.add(questionWrapper);
            }
            return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Integer> calculateResult(Integer quizId, List<QuizResponse> quizResponses) {
        try {
            Quiz quiz = quizDao.findById(quizId).get();
            List<Question> quizQuestions = quiz.getQuestions();
            int score = 0;
            int questionIndex = 0;
            for (QuizResponse quizResponse : quizResponses) {
                if (quizResponse.getResponse().equals(quizQuestions.get(questionIndex).getRightAnswer()))
                    score++;
                questionIndex++;
            }
            return new ResponseEntity<>(score, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(-1, HttpStatus.BAD_REQUEST);
    }
}
