package com.mali.huaweiqa.domain.quizzes;


import com.mali.huaweiqa.domain.questions.Question;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * A quiz that will contain list of questions from the QuestionLibrary.
 * It will produce the questions in a random order.
 */

public class Quiz implements Serializable{
    // duration of the quiz
    private Integer duration;
    private ArrayList<Question> questions;
    private boolean isTaken;

    public Quiz(){
        this.questions = new ArrayList<>();
        this.duration = 0;
    }
    public Quiz(Integer duration, ArrayList<Question> questions) {
        this.duration = duration;
        this.questions = questions;
        this.isTaken = false;
    }

    /**
     * get a random question from the quiz.
     * @return a random question for the quiz
     */
    public Question nextQuestion(){
        Random rnd = new Random();
        return questions.remove(rnd.nextInt(questions.size()));
    }

    /**
     *
     * @return Ture if there are still any more questions in the quiz.
     */
    public boolean hasNextQuestion(){
        return questions.size() > 0;
    }

    public Integer questionTotal(){
        return this.questions.size();
    }

    public Integer getDuration() {
        return duration;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }


}
