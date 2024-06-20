package com.example.quiz_service.service.impl;

import com.example.quiz_service.entity.Quiz;
import com.example.quiz_service.record.QuestionResponseRecord;
import com.example.quiz_service.record.request.SubmitQuizRequest;
import com.example.quiz_service.repo.QuizDao;
import com.example.quiz_service.service.QuizInterface;
import com.example.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
    private QuizDao quizdao;
    @Autowired
    QuizInterface quizInterface;
    @Override
    public String createQuiz(String topic, int num, String title) {
        List<Long> questions = quizInterface.getQuestionsForQuiz(topic, num).getBody();
        Quiz quiz = Quiz.builder().title(title).questionIds(questions).build();
        quizdao.saveAndFlush(quiz);
        return "Created a Quiz";
    }
    @Override
    public List<QuestionResponseRecord> getQuizQuestions(Long id) {
        Optional<Quiz> quiz = quizdao.findById(id);
        List<Long> questionIds = quiz.get().getQuestionIds();
        return quizInterface.getQuestionsFromId(questionIds).getBody();
    }
    @Override
    public Long submitQUiz(Long id, List< SubmitQuizRequest> submitQuizRequestList) {
        return quizInterface.getScore(submitQuizRequestList).getBody();
    }
}
