package com.mali.huaweiqa.ui.Students;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.mali.huaweiqa.R;
import com.mali.huaweiqa.domain.users_profile.Student;
import com.mali.huaweiqa.domain.quizzes.Quiz;
import com.mali.huaweiqa.domain.users_profile.UserRegistry;

import java.util.ArrayList;

public class StudentsDialogFragment extends DialogFragment {

    private static final String TAG = "QuizConfiguration";
    private StudentViewModel teacherViewModel;
    private Quiz quiz;
    private Button bConfirm;

    public StudentsDialogFragment(Quiz quiz){
        this.quiz = quiz;
    }
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        teacherViewModel =
                new ViewModelProvider(this).get(StudentViewModel.class);
        View root = inflater.inflate(R.layout.student_main, container, false);
        final RecyclerView studentsList = root.findViewById(R.id.students_list);
        final StudentListAdapter adapter = new StudentListAdapter(true);
        bConfirm = root.findViewById(R.id.quizConfirmation);

        teacherViewModel.getStudents().observe(getViewLifecycleOwner(), new Observer<ArrayList<Student>>() {
            @Override
            public void onChanged(ArrayList<Student> students) {
                adapter.setStudents(students);
                studentsList.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

        // make the confirmation button visible
        bConfirm.setVisibility(View.VISIBLE);
        bConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // make a new quiz and add it to all the selected studetns
                ArrayList<Student> students =  adapter.getSelectedStudents();
                for (Student student : students){
                    student.getQuizzes().add(quiz);
                }

                //update all studetns in database
                for (Student student : students){
                    UserRegistry.getInstance().addNewStudent(student);
                }
                Toast.makeText(getContext(), "Quiz added to selected students", Toast.LENGTH_SHORT).show();
                getDialog().dismiss();
            }
        });

        return root;

    }


}
