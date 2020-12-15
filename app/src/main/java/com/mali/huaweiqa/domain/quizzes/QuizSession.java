package com.mali.huaweiqa.domain.quizzes;

import com.mali.huaweiqa.domain.questions.Question;
import com.mali.huaweiqa.domain.users_profile.Student;
import com.mali.huaweiqa.domain.users_profile.UserRegistry;

import java.io.Serializable;

/**
 * This class serves as a quiz session. It handles the logic of the quiz and update the score of the user on DB once the quiz finishes
 */
public class QuizSession implements Serializable {

    private int score;
    private Quiz quiz;
    private double startTime;
    private Question currentQuestion;
    private Student student;

    public QuizSession(Quiz quiz, Student student){
        this.quiz = quiz;
        this.student = student;
    }

    public void start(){
        this.startTime = System.currentTimeMillis();
    }

    public int getRemainingTime(){
        return (int) Math.floor((System.currentTimeMillis() - this.startTime) / 1000);
    }

    public boolean isFinished(){
        return !quiz.hasNextQuestion() || getRemainingTime() < 0;
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

    public void finishQuiz(){
        student.setTotalScore(student.getTotalScore() + getScore());
        quiz.setTaken(true);
        student.getQuizzes().remove(quiz);

        // update student in database
        UserRegistry.getInstance().addNewStudent(student);
    }
}
