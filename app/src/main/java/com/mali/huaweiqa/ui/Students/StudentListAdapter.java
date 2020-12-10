package com.mali.huaweiqa.ui.Students;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.mali.huaweiqa.R;
import com.mali.huaweiqa.domain.users_profile.Student;

import java.util.ArrayList;
import java.util.HashSet;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentListViewHolder> {
    private ArrayList<Student> students;
    private HashSet<Student> selectedStudents;
    private boolean selectable;

    public  StudentListAdapter(boolean selectable){
        this.selectable = selectable;
        this.selectedStudents = new HashSet<>();
        this.students = new ArrayList<>();
    }
    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    @NonNull
    @Override
    public StudentListAdapter.StudentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudentListAdapter.StudentListViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.fragment_student,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull StudentListAdapter.StudentListViewHolder holder, int position) {
        holder.bindStudent(students.get(position));
    }

    @Override
    public int getItemCount() {
        return this.students.size();
    }

    class StudentListViewHolder extends RecyclerView.ViewHolder{

        TextView nameView;
        TextView scoreView;
        ImageView imageView;
        ImageView isSelectedImg;
        ConstraintLayout layoutView;
        View viewBackground;

        public StudentListViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.student_name);
            scoreView = itemView.findViewById(R.id.student_score);
            layoutView = itemView.findViewById(R.id.layoutStudentView);
            isSelectedImg = itemView.findViewById(R.id.isSelectedStudent);
            viewBackground = itemView.findViewById(R.id.studentViewBackground);
        }

        void bindStudent(final Student student){
            nameView.setText(student.getName());
            scoreView.setText("Score: " + student.getTotalScore().toString());
            
            if(selectedStudents.contains(student)){
                isSelectedImg.setVisibility(View.VISIBLE);
                layoutView.setBackgroundResource(R.drawable.student_selected_background);
            }
            else{
                isSelectedImg.setVisibility(View.GONE);
                layoutView.setBackgroundResource(R.drawable.student_background);
            }

            layoutView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(selectedStudents.contains(student)){
                        selectedStudents.remove(student);
                        isSelectedImg.setVisibility(View.GONE);
                        layoutView.setBackgroundResource(R.drawable.student_background);
                    }
                    else
                    {
                        selectedStudents.add(student);
                        if(selectable) {
                            isSelectedImg.setVisibility(View.VISIBLE);
                            layoutView.setBackgroundResource(R.drawable.student_selected_background);
                        }
                    }
                }
            });
        }
    }

    interface QuestionListener{
        void onMakeQuizAction(Boolean isSelected);
    }
}
