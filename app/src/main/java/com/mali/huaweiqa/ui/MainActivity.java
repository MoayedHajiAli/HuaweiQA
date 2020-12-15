package com.mali.huaweiqa.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.huawei.hms.support.hwid.service.HuaweiIdAuthService;
import com.mali.huaweiqa.R;
import com.mali.huaweiqa.domain.questions.QuestionsLibrary;
import com.mali.huaweiqa.domain.users_profile.Student;
import com.mali.huaweiqa.domain.users_profile.Teacher;
import com.mali.huaweiqa.domain.users_profile.UserRegistry;
import com.mali.huaweiqa.ui.Students.StudentFragment;
import com.mali.huaweiqa.ui.quizzes.QuizListFragment;
import com.mali.huaweiqa.ui.signup.LoginFragment;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    public NavigationView navigationView;
    public NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: For now call the getInstance in order to load the data
        QuestionsLibrary.getInstance();
        UserRegistry.getInstance();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setVisibility(View.GONE);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_quizzes, R.id.nav_students, R.id.nav_categories, R.id.nav_signUp, R.id.nav_login)
                .setDrawerLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}