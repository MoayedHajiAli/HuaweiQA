package com.mali.huaweiqa.domain.questions;

import java.util.ArrayList;
import java.util.Locale;

/**
 * A singleton library that hold all questions categories in the application.
 */
public class QuestionsLibrary {
    private static QuestionsLibrary _instance;
    private ArrayList<QuestionsCategory> categories;

    private QuestionsLibrary(){
        this.categories = new ArrayList<>();
    }

    public static QuestionsLibrary getInstance(){
        if(_instance == null){
            _instance = new QuestionsLibrary();
        }
        return _instance;
    }

    public void addCategory(QuestionsCategory category){
        this.categories.add(category);
    }

    public void addCategory(String categoryTitle){
        this.categories.add(new QuestionsCategory(categoryTitle));
    }

    public ArrayList<QuestionsCategory> getCategories() {
        return categories;
    }
}
