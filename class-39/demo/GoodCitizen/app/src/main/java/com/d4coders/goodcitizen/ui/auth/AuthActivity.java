package com.d4coders.goodcitizen.ui.auth;

import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.analytics.pinpoint.AWSPinpointAnalyticsPlugin;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
import com.d4coders.goodcitizen.R;
import com.d4coders.goodcitizen.ui.auth.adapter.AuthenticationPagerAdapter;
import com.d4coders.goodcitizen.ui.auth.fragments.SignInFragment;
import com.d4coders.goodcitizen.ui.auth.fragments.SignUpFragment;

public class AuthActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        configureAWS();

        ViewPager viewPager = findViewById(R.id.viewPager);

        AuthenticationPagerAdapter pagerAdapter = new AuthenticationPagerAdapter(getSupportFragmentManager());

        // the order of the fragments added to the adapter decides which one is shown first / inflated
        pagerAdapter.addFragments(SignInFragment.newInstance("", ""));
        SignUpFragment signUpFragment = SignUpFragment.newInstance("", "");
        signUpFragment.setSignUpListener(new SignUpFragment.OnSignUpComplete() {
            @Override
            public void signUpCompleted() {
                Log.i("AuthActivity", "The code works");
            }
        });
        pagerAdapter.addFragments(signUpFragment);
        viewPager.setAdapter(pagerAdapter);

    }

    private void configureAWS() {
        try {
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.addPlugin(new AWSPinpointAnalyticsPlugin(getApplication()));
            Amplify.configure(getApplicationContext());

            Log.i("Tutorial", "Initialized Amplify");
        } catch (AmplifyException e) {
            Log.e("Tutorial", "Could not initialize Amplify", e);
        }
    }
}