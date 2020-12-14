package com.mali.huaweiqa.domain.Students_profile;

import com.mali.huaweiqa.domain.questions.QuestionsLibrary;
import com.mali.huaweiqa.domain.quizzes.Quiz;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {

    private Integer totalScore;
    private ArrayList<Quiz> quizzes;
    private String name, email, password;
    private File imgFile;
    private String StudentID;

    public Student(){
        quizzes = new ArrayList<>();
        totalScore = 0;
        this.password = "";
        this.email = "";
        // by default add two welcome quizzes
        quizzes.add(new Quiz(200, QuestionsLibrary.getInstance().getCategories().get(0).getCategoryQuestions()));
        quizzes.add(new Quiz(200, QuestionsLibrary.getInstance().getCategories().get(1).getCategoryQuestions()));
    }

    public Student(String name, String StudentID) {
        setName(name);
        setEmail(email);
        setStudentID(StudentID);
        this.email = "";
        this.password = "";
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

    @Override
    public String toString() {
        return "Student{" +
                "totalScore=" + totalScore +
                ", quizzes=" + quizzes +
                '}';
    }

    public Student withEmail(String email){
        setEmail(email);
        return this;
    }

    public Student withPassword(String password){
        setPassword(password);
        return this;
    }

    public Student withImage(File imgFile){
        setImgFile(imgFile);
        return this;
    }




    // setter, getters for serializations

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public File getImgFile() {
        return imgFile;
    }

    public String getStudentID() {
        return StudentID;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImgFile(File imgFile) {
        this.imgFile = imgFile;
    }

    public void setStudentID(String StudentID) {
        this.StudentID = StudentID;
    }

}
