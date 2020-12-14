package com.mali.huaweiqa.ui.signup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mali.huaweiqa.R;
import com.mali.huaweiqa.domain.users_profile.Student;
import com.mali.huaweiqa.domain.users_profile.User;
import com.mali.huaweiqa.domain.users_profile.UserRegistry;

public class SignupFragment extends Fragment {

    private EditText name, email, password;
    private Button signUp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.signup_main, container, false);
        name = root.findViewById(R.id.SignUpName);
        email = root.findViewById(R.id.signUpEmail);
        password = root.findViewById(R.id.SignUpPassword);
        signUp = root.findViewById(R.id.SignUpButton);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create a new user
                User user = new Student(name.getText().toString(), email.getText().toString())
                        .withEmail(email.getText().toString())
                        .withPassword(password.getText().toString());
                UserRegistry.getInstance().addNewUser(user);
                getActivity().onBackPressed();
            }
        });

        return root;
    }
}
