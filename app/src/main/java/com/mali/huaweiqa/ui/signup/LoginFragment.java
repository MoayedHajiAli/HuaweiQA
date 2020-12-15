package com.mali.huaweiqa.ui.signup;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.FirebaseDatabase;
import com.huawei.hms.common.ApiException;
import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.support.hwid.HuaweiIdAuthManager;
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParams;
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParamsHelper;
import com.huawei.hms.support.hwid.result.AuthHuaweiId;
import com.huawei.hms.support.hwid.service.HuaweiIdAuthService;
import com.mali.huaweiqa.R;
import com.mali.huaweiqa.domain.users_profile.UserRegistry;
import com.mali.huaweiqa.domain.users_profile.Student;
import com.mali.huaweiqa.ui.MainActivity;

/**
 * A fragment that handles sign-in sign-out
 */
public class LoginFragment extends Fragment implements UserRegistry.UserAuthenticationListener, NavigationView.OnNavigationItemSelectedListener {

    //Log tag
    public static final String TAG = "LoginActivity";
    private HuaweiIdAuthService mAuthManager;
    private HuaweiIdAuthParams mAuthParam;

    private TextView loginBT, HMSLoginBT;
    private TextView signUpTV;
    private EditText emailET, passwordET;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((MainActivity) getActivity()).navigationView.setNavigationItemSelectedListener(this);

        View root = inflater.inflate(R.layout.login_main, container, false);
        loginBT = root.findViewById(R.id.login);
        HMSLoginBT = root.findViewById(R.id.hms_login);
        emailET = root.findViewById(R.id.email);
        passwordET = root.findViewById(R.id.password);
        signUpTV = root.findViewById(R.id.signup_text);
        loginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emailET.getText().toString().equals("admin") && emailET.getText().toString().equals("admin")){
                    Navigation.findNavController(getView()).navigate(R.id.nav_students);
                }
                else {
                    UserRegistry.getInstance().getStudent(emailET.getText().toString(), passwordET.getText().toString(), LoginFragment.this);
                }
            }
        });

        // disable if not Huawei phone
        Toast.makeText(getContext(), Build.MANUFACTURER.toString(), Toast.LENGTH_SHORT).show();
        System.out.println(Build.MANUFACTURER);
        if(Build.MANUFACTURER.toLowerCase().equals("huawei")) {
            HMSLoginBT.setVisibility(View.VISIBLE);
            HMSLoginBT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signIn();
                }
            });
        }

        signUpTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_signUp);
            }
        });

        mAuthParam = new HuaweiIdAuthParamsHelper(HuaweiIdAuthParams.DEFAULT_AUTH_REQUEST_PARAM)
                .setIdToken()
                .setAccessToken()
                .createParams();

        return root;
    }

    private void signIn() {
        mAuthManager = HuaweiIdAuthManager.getService(getContext(), mAuthParam);
        Task<AuthHuaweiId> signInTask = mAuthManager.silentSignIn();
        signInTask.addOnSuccessListener(new OnSuccessListener<AuthHuaweiId>() {
            @Override
            public void onSuccess(AuthHuaweiId authHuaweiId) {
                Toast.makeText(getContext(), "Welcome " + authHuaweiId.getDisplayName().toString(), Toast.LENGTH_SHORT).show();
                // create a new user
                Student student = new Student(authHuaweiId.getEmail(), authHuaweiId.getDisplayName())
                        .withEmail(authHuaweiId.getEmail())
                        .withPassword("");

                UserRegistry.getInstance().addIfNotContained(student);
                UserRegistry.getInstance().getStudent(student.getStudentID(), "", LoginFragment.this);
            }
        });

        signInTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                startActivityForResult(mAuthManager.getSignInIntent(), 5000);
            }
        });
    }

    public void signOut(){
        Intent mainActivity = new Intent(getActivity(), MainActivity.class);
        getActivity().startActivity(mainActivity);
        mainActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainActivity);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 5000){
            Task<AuthHuaweiId> task = HuaweiIdAuthManager.parseAuthResultFromIntent(data);
            if(task.isSuccessful()){
                task.getResult(); // return authHuaweiID;

                // create a new user
                Student student = new Student(task.getResult().getDisplayName(), task.getResult().getDisplayName())
                        .withEmail(task.getResult().getDisplayName())
                        .withPassword("");

                UserRegistry.getInstance().addNewStudent(student);
                Bundle bundle = new Bundle();
                bundle.putSerializable("User", student);
                Navigation.findNavController(getView()).navigate(R.id.nav_quizzes, bundle);
            }
            else{
                ApiException exp = (ApiException) task.getException();
                Toast.makeText(getContext(), String.valueOf(exp.getStatusCode()), Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onAuthenticatedUser(Student student) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("User", student);
        Navigation.findNavController(getView()).navigate(R.id.nav_quizzes, bundle);
    }

    @Override
    public void onWrongPassword() {
        Toast.makeText(getContext(), "Wrong password", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWrongEmail() {
        Toast.makeText(getContext(), "Wrong Email", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        NavController navCo = navHostFragment.getNavController();
        switch (item.getItemId()){
            case R.id.nav_signout:
                Toast.makeText(getContext(), "Signing out", Toast.LENGTH_SHORT).show();
                signOut();
                break;
            case R.id.nav_students:
                navCo.navigate(R.id.nav_students);
            case R.id.nav_categories:
                navCo.navigate(R.id.nav_categories);
        }

        DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
