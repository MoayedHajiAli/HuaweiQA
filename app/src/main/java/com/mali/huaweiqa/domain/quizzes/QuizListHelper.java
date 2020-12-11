package com.mali.huaweiqa.domain.quizzes;

import java.util.ArrayList;

public class QuizListHelper {

    ArrayList<Quiz> quizzes;

    QuizListHelper(){}

    public QuizListHelper(ArrayList<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public ArrayList<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(ArrayList<Quiz> quizzes) {
        this.quizzes = quizzes;
    }
}
