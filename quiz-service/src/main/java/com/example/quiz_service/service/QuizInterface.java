package com.example.quiz_service.service;

import com.example.quiz_service.record.QuestionResponseRecord;
import com.example.quiz_service.record.request.SubmitQuizRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping("ques/generate")
    public ResponseEntity<List<Long>> getQuestionsForQuiz(@RequestParam String topic, @RequestParam Integer num);
    @PostMapping("ques/getquestions")
    public ResponseEntity<List<QuestionResponseRecord>> getQuestionsFromId(@RequestBody List<Long> questionIds);
    @PostMapping("ques/getScore")
    public ResponseEntity<Long> getScore(@RequestBody List<SubmitQuizRequest> submitQuizRequestList);
}
