package com.mali.huaweiqa.ui.Students;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mali.huaweiqa.domain.users_profile.Student;
import com.mali.huaweiqa.domain.users_profile.Teacher;

import java.util.ArrayList;

public class StudentViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Student>> students;

    public LiveData<ArrayList<Student>> getStudents(Teacher teacher) {
        if (students == null) {
            students = new MutableLiveData<ArrayList<Student>>();
            loadUsers(teacher);
        }
        return students;
    }

    private void loadUsers(Teacher teacher) {
        students.setValue(teacher.getStudents());
    }
}
