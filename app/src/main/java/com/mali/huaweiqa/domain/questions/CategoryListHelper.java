package com.mali.huaweiqa.domain.questions;

import java.util.ArrayList;

public class CategoryListHelper {

    private ArrayList<QuestionsCategory> categories;

    public CategoryListHelper(){
        this.categories = new ArrayList<>();
    }

    public CategoryListHelper(ArrayList<QuestionsCategory> categories) {
        this.categories = categories;
    }

    public ArrayList<QuestionsCategory> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<QuestionsCategory> categories) {
        this.categories = categories;
    }
}
