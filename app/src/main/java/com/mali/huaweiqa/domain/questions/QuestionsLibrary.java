package com.mali.huaweiqa.domain.questions;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * A singleton library that hold all questions categories in the application.
 */
public class QuestionsLibrary {
    private static QuestionsLibrary _instance;
    private String CATEGORIES_LIST = "CATEGORIES_LIST";
    private String QUESTIONS_LIST = "QUESTIONS_LIST";
    private final FirebaseDatabase database;
    private final DatabaseReference categoryRef;
    private CategoryListHelper categories;

    private QuestionsLibrary() {
        this.categories = new CategoryListHelper();
        // retrieve the categories list from DB.
        database = FirebaseDatabase.getInstance();
        categoryRef = database.getReference().child(CATEGORIES_LIST);

        categoryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                categories = dataSnapshot.getValue(CategoryListHelper.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Database:onCancelled", databaseError.toException());
            }
        });
        categoryRef.push();
    }

    public static QuestionsLibrary getInstance(){
        if(_instance == null){
            _instance = new QuestionsLibrary();
        }
        return _instance;
    }

    public void addCategory(QuestionsCategory category){
        this.categories.getCategories().add(category);
    }

    public void addCategory(String categoryTitle){
        QuestionsCategory category = new QuestionsCategory(categoryTitle);
        this.categories.getCategories().add(category);
        // update the categories
        categoryRef.setValue(categories);
        Log.i("Database:update", "Categories update in DB");
    }

    public ArrayList<QuestionsCategory> getCategories() {
        return categories.getCategories();
    }

    public QuestionsCategory getCategory(String title){
        for (QuestionsCategory category : categories.getCategories()){
            if(category.getCategoryTitle().equals(title))
                return category;
        }
        return null;
    }

    public QuestionsCategory getCategory(int ind){
        return this.categories.getCategories().get(ind);
    }

    public void addQuestion(String categoryTitle, Question question){
        this.getCategory(categoryTitle).addQuestion(question);
    }
}
