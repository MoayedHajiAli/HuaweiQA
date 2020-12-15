package com.mali.huaweiqa.ui.quizzes;

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
import com.mali.huaweiqa.domain.questions.QuestionsLibrary;
import com.mali.huaweiqa.domain.quizzes.Quiz;
import com.mali.huaweiqa.domain.users_profile.Student;

import java.util.ArrayList;

/**
 * A fragment that lists all available quizzes for student
 */
public class QuizListFragment extends Fragment {
    private QuizListViewModel quizListViewModel;
    private RecyclerView quizList;
    private QuizListAdapter adapter;
    private Student student;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        student = (Student) getArguments().getSerializable("User");
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setVisibility(View.VISIBLE);
        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        navigationView.setVisibility(View.VISIBLE);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_students).setVisible(false);
        nav_Menu.findItem(R.id.nav_categories).setVisible(false);

        quizListViewModel =
                new ViewModelProvider(this).get(QuizListViewModel.class);
        View root = inflater.inflate(R.layout.quiz_list_main, container, false);
        quizList = root.findViewById(R.id.quizList);
        adapter = new QuizListAdapter(student);


        // retrieve data on changes
        quizListViewModel.getQuizzes(student).observe(getViewLifecycleOwner(), new Observer<ArrayList<Quiz>>() {
            @Override
            public void onChanged(ArrayList<Quiz> quizzes) {
                ArrayList<Quiz> unTakenQuizzes = new ArrayList<>();
                // filter the taken quizzes
                for (Quiz quiz : quizzes){
                    if(!quiz.isTaken())
                        unTakenQuizzes.add(quiz);
                }
                adapter.setQuizzes(unTakenQuizzes);
                quizList.setAdapter(adapter);
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
