package com.mali.huaweiqa.domain.questions;

import java.util.ArrayList;

/**
 * A multiple choice question with a single correct answer.
 */
public class Question {

    private String questionBody;
    private ArrayList<String> choices;
    private Integer correctAnswer;
    Integer score;


    public Question(String questionBody, ArrayList<String> choices, Integer correctAnswer, Integer score) {
        this.questionBody = questionBody;
        this.choices = choices;
        this.correctAnswer = correctAnswer;
        this.score = score;
    }

    public String getQuestionBody() {
        return questionBody;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    public String getCorrectAnswers() {
        return choices.get(this.correctAnswer);
    }

    public Integer getScore() {
        return score;
    }
}
