package com.mali.huaweiqa.domain.quizzes;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mali.huaweiqa.domain.questions.QuestionsLibrary;

import java.util.ArrayList;
import java.util.HashMap;

public class QuizLibrary {

    private static HashMap<String, QuizLibrary> _instances = new HashMap<>();
    private QuizListHelper quizzes;
    private String QUIZZES_LIST = "QUIZZES_LIST";
    private final FirebaseDatabase database;
    private final DatabaseReference quizRef;

    private QuizLibrary(String ID){
        // retrieve quizzes from the database
        this.database = FirebaseDatabase.getInstance();
        this.quizRef = database.getReference().child(QUIZZES_LIST).child(ID);
        this.quizzes = new QuizListHelper();

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


    public static QuizLibrary getInstance(String id){
        if(!_instances.containsKey(id)){
            _instances.put(id, new QuizLibrary(id));
            // add dummy quizzes
            _instances.get(id).quizzes.addQuiz(new Quiz(100, QuestionsLibrary.getInstance().getCategory(0).getCategoryQuestions()));
        }
        return _instances.get(id);
    }

    public void addQuiz(Quiz quiz){
        this.quizzes.addQuiz(quiz);
        quizRef.setValue(quiz);
        Log.i("Database:update", "quizzes update in DB");
    }

    public ArrayList<Quiz> getQuizzes(){
        return this.quizzes.getQuizzes();
    }

}
