package com.mali.huaweiqa.domain.users_profile;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Teacher implements Serializable {

    // paired students with the teacher
    private ArrayList<Student> students;

    // pending students requests to the teacher
    private ArrayList<Student> pendingRequests;


    public Teacher (){}


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

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void setPendingRequests(ArrayList<Student> pendingRequests) {
        this.pendingRequests = pendingRequests;
    }
}
