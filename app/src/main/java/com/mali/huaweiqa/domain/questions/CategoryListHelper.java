package com.mali.huaweiqa.domain.questions;

import java.util.ArrayList;

/**
 * This class serves as a list helper for storing categories on Firebase.
 */

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
