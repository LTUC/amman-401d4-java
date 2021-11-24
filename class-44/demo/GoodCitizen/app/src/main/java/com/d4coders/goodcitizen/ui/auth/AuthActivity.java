package com.d4coders.goodcitizen.ui.auth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.amplifyframework.datastore.generated.model.User;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
import com.d4coders.goodcitizen.R;
import com.d4coders.goodcitizen.ui.auth.adapter.AuthenticationPagerAdapter;
import com.d4coders.goodcitizen.ui.auth.fragments.SignInFragment;
import com.d4coders.goodcitizen.ui.auth.fragments.SignUpFragment;
import com.d4coders.goodcitizen.ui.dashboard.DashboardActivity;

public class AuthActivity extends AppCompatActivity {

    public static final String USER_NAME = "userName";
    public static final String USER_NUMBER = "userNumber";
    public static final String USER_NATIONAL_ID = "userNationalId";
    private static final String USER_ID = "userId";
    private static final String USER_CREDITS = "userCredits";
    public static final String USER_EMAIL = "userEmail";
    public static final String USER_DATA = "userData";

    private SharedPreferences sharedPref;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        sharedPref = getSharedPreferences(USER_DATA, Context.MODE_PRIVATE);

        ViewPager viewPager = findViewById(R.id.viewPager);

        AuthenticationPagerAdapter pagerAdapter = new AuthenticationPagerAdapter(getSupportFragmentManager());

        // the order of the fragments added to the adapter decides which one is shown first / inflated
        SignInFragment signInFragment = SignInFragment.newInstance("", "");
        signInFragment.setSignInListener(() -> {
            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
            finish();
        });
        pagerAdapter.addFragments(signInFragment);
        SignUpFragment signUpFragment = SignUpFragment.newInstance("", "");
        signUpFragment.setSignUpListener(user -> {
            Log.i("AuthActivity", "The code works");

            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(USER_ID, user.getId());
            editor.putString(USER_EMAIL, user.getEmail());
            editor.putString(USER_NATIONAL_ID, user.getNationalId());
            editor.putString(USER_NUMBER, user.getNumber());
            editor.putString(USER_NAME, user.getFirstName() + " " + user.getLastName());
            editor.putInt(USER_CREDITS, user.getCredits());
            editor.apply();

            Intent intent = new Intent(getApplicationContext(), EmailConfirmationActivity.class);
            intent.putExtra(USER_EMAIL, user.getEmail());
            startActivity(intent);
        });
        pagerAdapter.addFragments(signUpFragment);
        viewPager.setAdapter(pagerAdapter);
    }
}