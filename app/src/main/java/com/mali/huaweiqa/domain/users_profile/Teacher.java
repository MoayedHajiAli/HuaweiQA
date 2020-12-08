package com.mali.huaweiqa.domain.users_profile;

import java.io.File;
import java.util.ArrayList;

public class Teacher extends User{

    // paired students with the teacher
    private ArrayList<Student> students;

    // pending students requests to the teacher
    private ArrayList<Student> pendingRequests;

    public  Teacher(String name, File imgFile){
        super(name, imgFile);
    }

    public void addStudentRequest(Student student){
        this.pendingRequests.add(student);
    }

    public ArrayList<Student> getPendingRequests() {
        return pendingRequests;
    }

    public boolean approveStudent(Student student){
        return  this.students.add(student);
    }

}
