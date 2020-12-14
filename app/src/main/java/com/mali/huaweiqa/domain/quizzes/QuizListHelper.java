package com.mali.huaweiqa.domain.quizzes;

import java.util.ArrayList;

public class QuizListHelper {

    private ArrayList<Quiz> quizzes;

    public QuizListHelper(){
        this.quizzes = new ArrayList<>();
    }

    public QuizListHelper(ArrayList<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public ArrayList<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(ArrayList<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public void addQuiz(Quiz quiz){
        this.quizzes.add(quiz);
    }
}
