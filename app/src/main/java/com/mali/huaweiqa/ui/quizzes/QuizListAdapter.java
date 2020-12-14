package com.mali.huaweiqa.ui.quizzes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.mali.huaweiqa.R;
import com.mali.huaweiqa.domain.quizzes.Quiz;
import com.mali.huaweiqa.domain.quizzes.QuizSession;

import java.util.ArrayList;

public class QuizListAdapter extends RecyclerView.Adapter<QuizListAdapter.QuizListViewHolder>{
    private ArrayList<Quiz> quizzes;

    public  QuizListAdapter(){
        this.quizzes = new ArrayList<>();
    }

    @NonNull
    @Override
    public QuizListAdapter.QuizListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuizListAdapter.QuizListViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.single_quiz,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull QuizListAdapter.QuizListViewHolder holder, int position) {
        holder.bindQuiz(quizzes.get(position));
    }

    @Override
    public int getItemCount() {
        return quizzes.size();
    }


    public void setQuizzes(ArrayList<Quiz> quizzes) {
        this.quizzes = quizzes;
    }


    class QuizListViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout layoutView;
        TextView quizQuestionNum, quizDuration;


        public QuizListViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutView = itemView.findViewById(R.id.layoutQuizView);
            quizDuration = itemView.findViewById(R.id.quizDuration);
            quizQuestionNum =  itemView.findViewById(R.id.quizQuestionNum);
        }

        void bindQuiz(final Quiz quiz){
            quizDuration.setText(quiz.getDuration().toString());
            quizQuestionNum.setText(quiz.questionTotal().toString());
            layoutView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // take that quiz
                    System.out.println("taking the quiz..");
                    Bundle bundle = new Bundle();
                    System.out.println(quiz.getDuration());
                    bundle.putSerializable("Quiz", new QuizSession(quiz));
                    Navigation.findNavController(v).navigate(R.id.nav_quiz_questions, bundle);
                    quiz.setTaken(true);
                }
            });
        }
    }
}
