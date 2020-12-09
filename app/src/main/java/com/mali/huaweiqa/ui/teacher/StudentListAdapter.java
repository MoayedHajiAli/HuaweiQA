package com.mali.huaweiqa.ui.teacher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mali.huaweiqa.R;
import com.mali.huaweiqa.domain.users_profile.Student;

import java.util.ArrayList;

public class StudentListAdapter extends ArrayAdapter<Student> {
    private ArrayList<Student> students;


    public StudentListAdapter(Context context) {
        super(context, 0);
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.fragment_student, parent, false);
        TextView nameView = rowView.findViewById(R.id.student_name);
        TextView scoreView = rowView.findViewById(R.id.student_score);
        nameView.setText(this.students.get(position).getName());
        scoreView.setText(this.students.get(position).getTotalScore().toString());
        return rowView;
    }

    @Override
    public int getCount() {
        System.out.println(this.students.size());
        return this.students.size();
    }
}
