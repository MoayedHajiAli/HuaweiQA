package com.mali.huaweiqa.ui.quizzes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mali.huaweiqa.domain.quizzes.Quiz;
import com.mali.huaweiqa.domain.quizzes.QuizLibrary;

import java.util.ArrayList;

public class QuizListViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Quiz>> quizzes;

    public LiveData<ArrayList<Quiz>> getQuizzes(String ID) {
        if (quizzes == null) {
            quizzes = new MutableLiveData<ArrayList<Quiz>>();
            loadQuizzes(ID);
        }
        return quizzes;
    }

    private void loadQuizzes(String ID) {
        quizzes.setValue(QuizLibrary.getInstance(ID).getQuizzes());
    }
}
