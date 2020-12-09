package com.mali.huaweiqa.ui.questions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.mali.huaweiqa.R;
import com.mali.huaweiqa.domain.questions.Question;
import com.mali.huaweiqa.domain.users_profile.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class QuestionsListAdapter extends RecyclerView.Adapter<QuestionsListAdapter.QuestionListViewHolder> {

    private ArrayList<Question> questions;
    private HashSet<Question> selectedQuestions;
    private QuestionListener listener;

    public  QuestionsListAdapter(QuestionListener listener){
        this.listener = listener;
        this.questions = new ArrayList<>();
        this.selectedQuestions = new HashSet<>();
    }

    @NonNull
    @Override
    public QuestionListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuestionListViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.fragment_question_view,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionListViewHolder holder, int position) {
        holder.bindQuestion(questions.get(position));
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }


    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }


    class QuestionListViewHolder extends RecyclerView.ViewHolder{

        ImageView isSelectedImg;
        TextView questionBody;
        ConstraintLayout layoutView;

        public QuestionListViewHolder(@NonNull View itemView) {
            super(itemView);
            isSelectedImg = itemView.findViewById(R.id.isSelectedQuestion);
            questionBody = itemView.findViewById(R.id.questionListBody);
            layoutView = itemView.findViewById(R.id.layoutQuestionView);
        }

        void bindQuestion(final Question question){
            questionBody.setText(question.getQuestionBody());
            if(selectedQuestions.contains(question)){
                isSelectedImg.setVisibility(View.VISIBLE);
            }
            else{
                isSelectedImg.setVisibility(View.GONE);
            }

            layoutView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(selectedQuestions.contains(question)){
                        selectedQuestions.remove(question);
                        isSelectedImg.setVisibility(View.GONE);
                        if(selectedQuestions.isEmpty()){
                            //notify the UI the make quiz button non-visible
                            listener.onMakeQuizAction(false);
                        }
                    }
                    else
                    {
                        selectedQuestions.add(question);
                        isSelectedImg.setVisibility(View.VISIBLE);

                        //notify the UI the make quiz button non-visible
                        listener.onMakeQuizAction(true);
                    }
                }
            });
        }
    }

    interface QuestionListener{
        void onMakeQuizAction(Boolean isSelected);
    }
}
