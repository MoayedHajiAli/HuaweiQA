package com.mali.huaweiqa.ui.Students;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.mali.huaweiqa.R;
import com.mali.huaweiqa.domain.users_profile.Student;
import java.util.ArrayList;

/**
 * List all students in the system for the teacher
 */
public class StudentFragment extends Fragment {

    private StudentViewModel teacherViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        navigationView.setVisibility(View.VISIBLE);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setVisibility(View.VISIBLE);
        teacherViewModel =
                new ViewModelProvider(this).get(StudentViewModel.class);
        View root = inflater.inflate(R.layout.student_main, container, false);
        final RecyclerView studentsList = root.findViewById(R.id.students_list);
        final StudentListAdapter adapter = new StudentListAdapter(false);

        teacherViewModel.getStudents().observe(getViewLifecycleOwner(), new Observer<ArrayList<Student>>() {
            @Override
            public void onChanged(ArrayList<Student> students) {
                adapter.setStudents(students);
                studentsList.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Log.i("BACK PRESSED", "BACK PRESSED - No action taken");
            }
        });
        return root;
    }

}
