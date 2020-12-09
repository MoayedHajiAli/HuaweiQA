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
        this.students = new ArrayList<>();
        this.pendingRequests = new ArrayList<>();
    }

    public void addStudentRequest(Student student){
        this.pendingRequests.add(student);
    }

    public ArrayList<Student> getPendingRequests() {
        return pendingRequests;
    }

    public boolean approveStudent(Student student){
        if(this.pendingRequests.contains(student)) {
            this.pendingRequests.remove(student);
            this.students.add(student);
            return true;
        }
        return false;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
}
