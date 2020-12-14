package com.mali.huaweiqa.ui.main_activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.mali.huaweiqa.R;
import com.mali.huaweiqa.domain.questions.QuestionsLibrary;
import com.mali.huaweiqa.domain.users_profile.Teacher;

public class TeacherMainActivity extends Fragment {

    private AppBarConfiguration mAppBarConfiguration;
    public Teacher teacher;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO: For now call the getInstance in order to load the data
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.student_main, container, false);

        QuestionsLibrary.getInstance();
        teacher = (Teacher) getArguments().getSerializable("User");

        return root;
    }

}