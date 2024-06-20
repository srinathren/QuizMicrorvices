package com.example.quiz_service.record;

public record QuestionResponseRecord(Long id, String question, String optionOne, String optionTwo, String optionThree, String optionFour) {
}
