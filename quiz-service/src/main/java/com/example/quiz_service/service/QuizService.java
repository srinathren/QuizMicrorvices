package com.example.quiz_service.service;

import com.example.quiz_service.record.QuestionResponseRecord;
import com.example.quiz_service.record.request.SubmitQuizRequest;

import java.util.List;

public interface QuizService {
    String createQuiz(String topic, int num, String title);
    List<QuestionResponseRecord> getQuizQuestions(Long id);
    Long submitQUiz(Long id, List<SubmitQuizRequest> submitQuizRequestList);
}
