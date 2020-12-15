package com.mali.huaweiqa.ui.Students;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mali.huaweiqa.domain.users_profile.Student;
import com.mali.huaweiqa.domain.users_profile.UserRegistry;

import java.util.ArrayList;

public class StudentViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Student>> students;

    public LiveData<ArrayList<Student>> getStudents() {
        if (students == null) {
            students = new MutableLiveData<ArrayList<Student>>();
            loadUsers();
        }
        return students;
    }

    private void loadUsers() {
        students.setValue(UserRegistry.getInstance().getStudents());
    }
}
