package com.mali.huaweiqa.ui.categories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mali.huaweiqa.domain.questions.QuestionsCategory;
import com.mali.huaweiqa.domain.questions.QuestionsLibrary;

import java.util.ArrayList;

public class CategoryViewModel extends ViewModel {
    private MutableLiveData<ArrayList<QuestionsCategory>> categories;

    public LiveData<ArrayList<QuestionsCategory>> getCategories() {
        if (categories == null) {
            categories = new MutableLiveData<ArrayList<QuestionsCategory>>();
            loadCategories();
        }
        return categories;
    }

    private void loadCategories() {
        categories.setValue(QuestionsLibrary.getInstance().getCategories());
    }
}
