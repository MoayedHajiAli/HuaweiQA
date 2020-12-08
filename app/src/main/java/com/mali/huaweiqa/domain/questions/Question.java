package com.mali.huaweiqa.domain.questions;

import java.util.ArrayList;

/**
 * A multiple choice question with a single correct answer.
 */
public class Question {

    private String questionBody;
    private ArrayList<String> choices;
    private String correctAnswers;
    Integer score;


    public Question(String questionBody, ArrayList<String> choices, String correctAnswers, Integer score) {
        this.questionBody = questionBody;
        this.choices = choices;
        this.correctAnswers = correctAnswers;
        this.score = score;
    }

    public String getQuestionBody() {
        return questionBody;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    public String getCorrectAnswers() {
        return correctAnswers;
    }

    public Integer getScore() {
        return score;
    }
}
