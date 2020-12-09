package com.mali.huaweiqa.ui.teacher;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.mali.huaweiqa.R;
import com.mali.huaweiqa.domain.users_profile.Student;
import com.mali.huaweiqa.domain.users_profile.Teacher;

import java.util.ArrayList;

public class TeacherFragment extends Fragment {

    private TeacherViewModel teacherViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // create dummy teacher for now
        Teacher teacher = new Teacher("Moayed",  null);
        Student student = new Student("Walid", null);
        student.setTotalScore(80);
        teacher.addStudentRequest(student);
        teacher.approveStudent(student);

        student = new Student("abd", null);
        student.setTotalScore(10);
        teacher.addStudentRequest(student);
        teacher.approveStudent(student);

        student = new Student("omar", null);
        student.setTotalScore(-5);
        teacher.addStudentRequest(student);
        teacher.approveStudent(student);

        student = new Student("wajdy", null);
        student.setTotalScore(15);
        teacher.addStudentRequest(student);
        teacher.approveStudent(student);

        student = new Student("mufuck", null);
        student.setTotalScore(-100);
        teacher.addStudentRequest(student);
        teacher.approveStudent(student);


        student = new Student("noor", null);
        student.setTotalScore(65);
        teacher.addStudentRequest(student);
        teacher.approveStudent(student);


        teacherViewModel =
                new ViewModelProvider(this).get(TeacherViewModel.class);
        View root = inflater.inflate(R.layout.teacher_main, container, false);
        final ListView studentsList = root.findViewById(R.id.students_list);
        final StudentListAdapter adapter = new StudentListAdapter(getActivity().getBaseContext());

        teacherViewModel.getStudents(teacher).observe(getViewLifecycleOwner(), new Observer<ArrayList<Student>>() {
            @Override
            public void onChanged(ArrayList<Student> students) {
                adapter.setStudents(students);
                studentsList.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
        return root;
    }
}
