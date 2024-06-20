package com.example.question_service.controller;

import com.example.question_service.dto.AddQuestionDto;
import com.example.question_service.record.QuestionResponseRecord;
import com.example.question_service.record.request.SubmitQuizRequest;
import com.example.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ques")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("getAll")
    public ResponseEntity<List<QuestionResponseRecord>> getAllQuiz() {
        return new ResponseEntity<>(questionService.getAllQuiz(), HttpStatus.OK);
    }
    @GetMapping("topic/{topic}")
    public ResponseEntity<List<QuestionResponseRecord>> getQuizByTopic(@PathVariable String topic){
        return new ResponseEntity<>(questionService.getQuizByTopic(topic), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> createQuiz(@RequestBody AddQuestionDto addQuestionDto){
        return new ResponseEntity<>(questionService.createQuestion(addQuestionDto), HttpStatus.CREATED);
    }
    @GetMapping("generate")
    public ResponseEntity<List<Long>> getQuestionsForQuiz(@RequestParam String topic, @RequestParam Integer num) {
        return new ResponseEntity<>(questionService.getQuestionsForQuiz(topic, num), HttpStatus.OK);
    }
    @PostMapping("getquestions")
    public ResponseEntity<List<QuestionResponseRecord>> getQuestionsFromId(@RequestBody List<Long> questionIds) {
        return new ResponseEntity<>(questionService.getQuestionsFromId(questionIds), HttpStatus.OK);
    }
    @PostMapping("getScore")
    public ResponseEntity<Long> getScore(@RequestBody List<SubmitQuizRequest> submitQuizRequestList) {
        return new ResponseEntity<>(questionService.getScore(submitQuizRequestList), HttpStatus.OK);
    }
}
