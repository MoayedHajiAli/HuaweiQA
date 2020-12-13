package com.mali.huaweiqa.domain.quizzes;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QuizLibrary {

    private QuizLibrary _instance;
    private QuizListHelper quizzes;
    private String ID;
    private String QUIZZES_LIST = "QUIZZES_LIST";
    private final FirebaseDatabase database;
    private final DatabaseReference quizRef;

    private QuizLibrary(String ID){
        this.ID = ID;
        // retrieve quizzes from the database
        this.database = FirebaseDatabase.getInstance();
        this.quizRef = database.getReference().child(QUIZZES_LIST);

        this.quizRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                quizzes = snapshot.getValue(QuizListHelper.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Database:onCancelled", error.toException());
            }
        });
        this.quizRef.push();
    }

    public QuizLibrary getInstance(String ID){
        if(_instance == null){
            _instance = new QuizLibrary(ID);

        }
        else if(ID != this.ID)
            return null;

        return _instance;
    }

    public ArrayList<Quiz> getQuizzes(){
        return this.quizzes.getQuizzes();
    }

}
