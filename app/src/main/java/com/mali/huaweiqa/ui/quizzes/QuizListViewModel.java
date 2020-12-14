package com.mali.huaweiqa.ui.quizzes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mali.huaweiqa.domain.quizzes.Quiz;
import com.mali.huaweiqa.domain.quizzes.QuizLibrary;
import com.mali.huaweiqa.domain.Students_profile.Student;

import java.util.ArrayList;

public class QuizListViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Quiz>> quizzes;

    public LiveData<ArrayList<Quiz>> getQuizzes(Student student) {
        if (quizzes == null) {
            quizzes = new MutableLiveData<ArrayList<Quiz>>();
            loadQuizzes(student);
        }
        return quizzes;
    }

    private void loadQuizzes(Student student) {
        quizzes.setValue(student.getQuizzes());
    }
}
