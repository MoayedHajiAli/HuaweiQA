package com.mali.huaweiqa.domain.users_profile;

import com.mali.huaweiqa.domain.questions.QuestionsLibrary;
import com.mali.huaweiqa.domain.quizzes.Quiz;

import java.io.Serializable;
import java.util.ArrayList;

public class Student extends User implements Serializable {

    private Integer totalScore;

    private ArrayList<Quiz> quizzes;

    public Student(){
        quizzes = new ArrayList<>();

        // by default add two welcome quizzes
        quizzes.add(new Quiz(200, QuestionsLibrary.getInstance().getCategories().get(0).getCategoryQuestions()));
        quizzes.add(new Quiz(200, QuestionsLibrary.getInstance().getCategories().get(1).getCategoryQuestions()));
    }

    public Student(String name, String userID) {
        super(name, userID);
        setUserType(UserType.STUDENT);
    }



    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public ArrayList<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(ArrayList<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

}
