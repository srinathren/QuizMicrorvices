package com.example.question_service.service.impl;

import com.example.question_service.dto.AddQuestionDto;
import com.example.question_service.entity.Question;
import com.example.question_service.record.QuestionResponseRecord;
import com.example.question_service.record.request.SubmitQuizRequest;
import com.example.question_service.repo.QuestionDao;
import com.example.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionDao questionDao;
    @Override
    public List<QuestionResponseRecord> getAllQuiz() {
        List<Question> questionList = questionDao.findAll();
        List<QuestionResponseRecord> questionResponseRecordList = new ArrayList<>();
        for (Question question : questionList) {
            QuestionResponseRecord questionResponseRecord = new QuestionResponseRecord(question.getId(), question.getQuestion(), question.getOptionOne(), question.getOptionTwo(), question.getOptionThree(), question.getOptionFour());
            questionResponseRecordList.add(questionResponseRecord);
        }
        return questionResponseRecordList;
    }

    @Override
    public List<QuestionResponseRecord> getQuizByTopic(String topic) {
        List<Question> questionList = questionDao.findByTopic(topic);
        List<QuestionResponseRecord> questionResponseRecordList = new ArrayList<>();
        for (Question question : questionList) {
            QuestionResponseRecord questionResponseRecord = new QuestionResponseRecord(question.getId(), question.getQuestion(), question.getOptionOne(), question.getOptionTwo(), question.getOptionThree(), question.getOptionFour());
            questionResponseRecordList.add(questionResponseRecord);
        }
        return questionResponseRecordList;
    }

    @Override
    public String createQuestion(AddQuestionDto addQuestionDto) {
        final Question question = Question.builder().question(addQuestionDto.getQuestion()).answer(addQuestionDto.getAnswer()).optionOne(addQuestionDto.getOptionOne()).optionTwo(addQuestionDto.getOptionTwo()).optionThree(addQuestionDto.getOptionThree()).optionFour(addQuestionDto.getOptionFour()).topic(addQuestionDto.getTopic()).build();
        questionDao.saveAndFlush(question);
        return "Created a Question";
    }

    @Override
    public List<Long> getQuestionsForQuiz(String topic, Integer num) {
        List<Long> questions = questionDao.createRandomQuizByCategory(topic, num);
        return questions;
    }

    @Override
    public List<QuestionResponseRecord> getQuestionsFromId(List<Long> questionIds) {
        List<QuestionResponseRecord> questionResponseRecordList = new ArrayList<>();
        for (Long id : questionIds) {
            Question question = questionDao.findById(id).get();
            QuestionResponseRecord questionResponseRecord = new QuestionResponseRecord(question.getId(), question.getQuestion(), question.getOptionOne(), question.getOptionTwo(), question.getOptionThree(), question.getOptionFour());
            questionResponseRecordList.add(questionResponseRecord);
        }
        return questionResponseRecordList;
    }

    @Override
    public Long getScore(List<SubmitQuizRequest> request) {
        Long rightAnswer = 0L;
        for (SubmitQuizRequest submitQuizRequest : request) {
            Question question = questionDao.findById(submitQuizRequest.id()).get();
            if (question.getAnswer().equalsIgnoreCase(submitQuizRequest.choice())) {
                rightAnswer++;
            }
        }
        return rightAnswer;
    }
}
