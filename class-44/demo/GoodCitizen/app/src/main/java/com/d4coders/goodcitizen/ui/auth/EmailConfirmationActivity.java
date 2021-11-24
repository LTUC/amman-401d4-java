package com.d4coders.goodcitizen.ui.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.core.Amplify;
import com.d4coders.goodcitizen.R;
import com.d4coders.goodcitizen.ui.auth.fragments.SignUpFragment;
import com.d4coders.goodcitizen.ui.dashboard.DashboardActivity;

public class EmailConfirmationActivity extends AppCompatActivity {

    private static final String TAG = "EmailConfirmationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_confirmation);

        Intent passedData = getIntent();
        String email = passedData.getStringExtra(AuthActivity.USER_EMAIL);

        EditText confirmationCode = findViewById(R.id.verification_code_input);

        Button confirmBtn = findViewById(R.id.confirm);
        confirmBtn.setOnClickListener(view -> {
            String code = confirmationCode.getText().toString();
            verifyEmail(code, email);
        });
    }

    private void verifyEmail(String verificationCode, String email) {
        Amplify.Auth.confirmSignUp(
                email,
                verificationCode,
                result -> {
                    Log.i(TAG,
                            result.isSignUpComplete() ?
                                    "Confirm signUp succeeded" :
                                    "Confirm sign up not complete");

                    startActivity(new Intent(EmailConfirmationActivity.this, DashboardActivity.class));
                },
                error -> Log.e(TAG, error.toString())
        );
    }
}