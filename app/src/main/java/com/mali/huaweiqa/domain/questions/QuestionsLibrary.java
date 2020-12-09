package com.mali.huaweiqa.domain.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
        QuestionsCategory category = new QuestionsCategory(categoryTitle);
        this.categories.add(category);
        // TODO: remove adding fixture questions
        Question question = new Question(
                categoryTitle + "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque ",
                new ArrayList<String>(Arrays.asList("Choice1", "Choice2", "Choice3", "Choice4")),0, 40);
        category.addQuestion(question);
        question = new Question(
                categoryTitle + "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque ",
                new ArrayList<String>(Arrays.asList("dog", "cat", "elephant", "crocadile")),0, 40);
        category.addQuestion(question);
        question = new Question(
                categoryTitle + "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque ",
                new ArrayList<String>(Arrays.asList("A", "B", "C", "D")),0, 40);
        category.addQuestion(question);
        question = new Question(
                categoryTitle + "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque ",
                new ArrayList<String>(Arrays.asList("1", "2", "3", "4")),0, 40);
        category.addQuestion(question);
    }

    public ArrayList<QuestionsCategory> getCategories() {
        return categories;
    }

    public QuestionsCategory getCategory(String title){
        for (QuestionsCategory category : categories){
            if(category.getCategoryTitle().equals(title))
                return category;
        }
        return null;
    }

    public QuestionsCategory getCategory(int ind){
        return this.categories.get(ind);
    }

    public void addQuestion(String categoryTitle, Question question){
        this.getCategory(categoryTitle).addQuestion(question);
    }
}
