package com.mali.huaweiqa.ui.questions;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mali.huaweiqa.domain.questions.Question;
import com.mali.huaweiqa.domain.questions.QuestionsCategory;
import com.mali.huaweiqa.domain.questions.QuestionsLibrary;

import java.util.ArrayList;

public class QuestionsListViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Question>> questions;
    private int CategoryPosition = -1;

    public LiveData<ArrayList<Question>> getQuestions(int position) {
        if (questions == null) {
            questions = new MutableLiveData<ArrayList<Question>>();
            loadQuestions(position);
        }
        if(this.CategoryPosition != position){
            this.CategoryPosition = position;
            loadQuestions(position);
        }
        return questions;
    }

    private void loadQuestions(int position) {
        questions.setValue(QuestionsLibrary.getInstance().getCategory(position).getCategoryQuestions());
    }
}
