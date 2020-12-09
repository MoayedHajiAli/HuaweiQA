package com.mali.huaweiqa.ui.questions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mali.huaweiqa.R;
import com.mali.huaweiqa.domain.questions.Question;
import com.mali.huaweiqa.domain.questions.QuestionsCategory;
import com.mali.huaweiqa.domain.users_profile.Student;

import java.util.ArrayList;

public class QuestionsListAdapter extends ArrayAdapter<Student> {

    private ArrayList<Question> questions;
    public QuestionsListAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.fragment_question_view, parent, false);
        TextView nameView = rowView.findViewById(R.id.question_lst_body);
        nameView.setText(questions.get(position).getQuestionBody());
        return rowView;
    }

    @Override
    public int getCount() {
        return questions.size();
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
}
