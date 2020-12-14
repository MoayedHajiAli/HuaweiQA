package com.mali.huaweiqa.domain.quizzes;

import com.mali.huaweiqa.domain.questions.Question;

import java.io.Serializable;

public class QuizSession implements Serializable {

    private int score;
    private Quiz quiz;
    private double startTime;
    private Question currentQuestion;

    public QuizSession(Quiz quiz){
        this.quiz = quiz;
    }

    public void start(){
        this.startTime = System.currentTimeMillis();
    }

    public int getRemainingTime(){
        return (int) Math.floor((System.currentTimeMillis() - this.startTime) / 1000);
    }

    public boolean isFinished(){
        return quiz.hasNextQuestion() && getRemainingTime() > 0;
    }

    public Question nextQuestion(){
        this.currentQuestion = this.quiz.nextQuestion();
        System.out.println(this.currentQuestion);
        return this.currentQuestion;
    }

    public void evaluateAnswer(int chosenAnswer){
        if(chosenAnswer == this.currentQuestion.getCorrectAnswer()){
            this.score += this.currentQuestion.getScore();
        }
    }

    public int getScore() {
        return score;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public double getStartTime() {
        return startTime;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }


}
