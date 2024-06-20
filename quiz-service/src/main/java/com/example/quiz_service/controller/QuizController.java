package com.example.quiz_service.controller;

import com.example.quiz_service.record.QuestionResponseRecord;
import com.example.quiz_service.record.request.SubmitQuizRequest;
import com.example.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    private QuizService service;
    @PostMapping
    public ResponseEntity<String> createQuiz(@RequestParam String topic, @RequestParam int num, @RequestParam String title) {
        return new ResponseEntity<>(service.createQuiz(topic, num, title), HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<List<QuestionResponseRecord>> getQuizQuestions(@PathVariable Long id) {
        return new ResponseEntity<>(service.getQuizQuestions(id), HttpStatus.OK);
    }
    @PostMapping("submit/{id}")
    public ResponseEntity<Long> submitQUiz(@PathVariable Long id ,@RequestBody List<SubmitQuizRequest> submitQuizRequest) {
        return new ResponseEntity<>(service.submitQUiz(id, submitQuizRequest), HttpStatus.OK);
    }
}
