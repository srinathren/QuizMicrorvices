package com.example.question_service.service;


import com.example.question_service.dto.AddQuestionDto;
import com.example.question_service.record.QuestionResponseRecord;
import com.example.question_service.record.request.SubmitQuizRequest;

import java.util.List;
public interface QuestionService {
    List<QuestionResponseRecord> getAllQuiz();
    List<QuestionResponseRecord> getQuizByTopic(String topic);
    String createQuestion(AddQuestionDto addQuestionDto);
    List<Long> getQuestionsForQuiz(String topic, Integer num);
    List<QuestionResponseRecord> getQuestionsFromId(List<Long> questionIds);

    Long getScore(List<SubmitQuizRequest> submitQuizRequestList);
}
