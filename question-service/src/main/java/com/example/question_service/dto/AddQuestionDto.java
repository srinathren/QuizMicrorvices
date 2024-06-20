package com.example.question_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddQuestionDto {
    private String question;
    private String optionOne;
    private String optionTwo;
    private String optionThree;
    private String optionFour;
    private String answer;
    private String topic;
}
