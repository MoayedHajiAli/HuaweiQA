package com.mali.huaweiqa.domain.questions;

import java.util.ArrayList;

/**
 * A multiple choice question with a single correct answer.
 */
public class Question {

    private String questionBody;
    private ArrayList<String> choices;
    private Integer correctAnswer;
    private Integer score;

    public Question(){

    }
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

    public String getCorrectAnswerBody() {
        return choices.get(this.correctAnswer);
    }

    public Integer getCorrectAnswer() {
        return this.correctAnswer; }

    public Integer getScore() {
        return score;
    }

    public void setQuestionBody(String questionBody) {
        this.questionBody = questionBody;
    }

    public void setChoices(ArrayList<String> choices) {
        this.choices = choices;
    }

    public void setCorrectAnswer(Integer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

}
