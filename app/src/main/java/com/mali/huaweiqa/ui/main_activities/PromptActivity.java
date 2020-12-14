package com.mali.huaweiqa.ui.main_activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.huawei.hmf.tasks.Task;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.support.hwid.HuaweiIdAuthManager;
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParams;
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParamsHelper;
import com.huawei.hms.support.hwid.result.AuthHuaweiId;
import com.huawei.hms.support.hwid.service.HuaweiIdAuthService;
import com.mali.huaweiqa.R;

public class PromptActivity extends AppCompatActivity {

    //Log tag
    public static final String TAG = "PromptActivity";
    private HuaweiIdAuthService mAuthManager;
    private HuaweiIdAuthParams mAuthParam;
    private AppBarConfiguration mAppBarConfiguration;

    private Button loginBT, HMSLoginBT;
    private EditText emailET, passwordET;
    private Intent teacherIntent, studentIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        loginBT = findViewById(R.id.login);
        HMSLoginBT = findViewById(R.id.hms_login);
        emailET = findViewById(R.id.email);
        passwordET = findViewById(R.id.password);
        Intent teacherIntent = new Intent(this, TeacherMainActivity.class);

        loginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(emailET.getText());
                startActivity(teacherIntent);
            }
        });

        HMSLoginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }


    private void signIn() {
        mAuthParam = new HuaweiIdAuthParamsHelper(HuaweiIdAuthParams.DEFAULT_AUTH_REQUEST_PARAM)
                .setIdToken()
                .setAccessToken()
                .createParams();
        mAuthManager = HuaweiIdAuthManager.getService(PromptActivity.this, mAuthParam);
    }

    private void signInCode() {
        mAuthParam = new HuaweiIdAuthParamsHelper(HuaweiIdAuthParams.DEFAULT_AUTH_REQUEST_PARAM)
                .setProfile()
                .setAuthorizationCode()
                .createParams();
        mAuthManager = HuaweiIdAuthManager.getService(PromptActivity.this, mAuthParam);
    }

}
