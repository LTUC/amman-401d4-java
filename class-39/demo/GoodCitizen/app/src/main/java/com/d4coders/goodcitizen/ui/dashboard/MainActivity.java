package com.d4coders.goodcitizen.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.pinpoint.PinpointConfiguration;
import com.amazonaws.mobileconnectors.pinpoint.PinpointManager;
import com.amplifyframework.analytics.AnalyticsEvent;
import com.amplifyframework.analytics.AnalyticsProperties;
import com.amplifyframework.analytics.UserProfile;
import com.amplifyframework.analytics.pinpoint.models.AWSPinpointUserProfile;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Problem;
import com.d4coders.goodcitizen.R;
import com.d4coders.goodcitizen.ui.dashboard.adapter.RecentObservationsAdapter;
import com.d4coders.goodcitizen.ui.observation.ObservationActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.FirebaseMessaging;

import com.amazonaws.mobileconnectors.pinpoint.targeting.TargetingClient;
import com.amazonaws.mobileconnectors.pinpoint.targeting.endpointProfile.EndpointProfileUser;
import com.amazonaws.mobileconnectors.pinpoint.targeting.endpointProfile.EndpointProfile;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static PinpointManager pinpointManager;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        // Initialize PinpointManager
        getPinpointManager(getApplicationContext());

        assignUserIdToEndpoint();

        userSession();

        List<Problem> observations = new ArrayList<>();
        observations.add(Problem.builder().title("Burning Car").userId("sdsakjdkshdkja").build());
        observations.add(Problem.builder().title("Burning House").userId("sdsakjdkshdkja").build());
        observations.add(Problem.builder().title("Burning Cat").userId("sdsakjdkshdkja").build());
        observations.add(Problem.builder().title("Burning Human").userId("sdsakjdkshdkja").build());
        observations.add(Problem.builder().title("Burning assignments").userId("sdsakjdkshdkja").build());
        observations.add(Problem.builder().title("Burning Truck").userId("sdsakjdkshdkja").build());
        observations.add(Problem.builder().title("Burning Laptop").userId("sdsakjdkshdkja").build());
        observations.add(Problem.builder().title("Burning BBQ").userId("sdsakjdkshdkja").build());

        RecyclerView recyclerView = findViewById(R.id.recent_observations);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecentObservationsAdapter(observations, view -> {
            Toast.makeText(getApplicationContext(), "It works", Toast.LENGTH_SHORT).show();
        }));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {

            // firebase and pinpoint events
            firebaseLogEvent();
            recordUser();
            recordAddObservationButtonTap();
            startActivity(new Intent(getApplicationContext(), ObservationActivity.class));
        });
    }

    private void userSession() {
        Amplify.Auth.fetchUserAttributes(
                attributes -> Log.i(TAG, "User attributes = " + attributes.toString()),
                error -> Log.e(TAG, "Failed to fetch user attributes.", error)
        );

                Amplify.Auth.fetchAuthSession(
                result -> {
                    Log.i(TAG, result.toString());
                },
                error -> Log.e(TAG, error.toString())
        );
    }

    public static PinpointManager getPinpointManager(final Context applicationContext) {
        if (pinpointManager == null) {
            final AWSConfiguration awsConfig = new AWSConfiguration(applicationContext);
            AWSMobileClient.getInstance().initialize(applicationContext, awsConfig, new Callback<UserStateDetails>() {
                @Override
                public void onResult(UserStateDetails userStateDetails) {
                    Log.i(TAG, "INIT => " + userStateDetails.getUserState().toString());
                }

                @Override
                public void onError(Exception e) {
                    Log.e("INIT", "Initialization error.", e);
                }
            });

            PinpointConfiguration pinpointConfig = new PinpointConfiguration(
                    applicationContext,
                    AWSMobileClient.getInstance(),
                    awsConfig);

            pinpointManager = new PinpointManager(pinpointConfig);

            FirebaseMessaging.getInstance().getToken()
                    .addOnCompleteListener(new OnCompleteListener<String>() {
                        @Override
                        public void onComplete(@NonNull Task<String> task) {
                            if (!task.isSuccessful()) {
                                Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                                return;
                            }
                            final String token = task.getResult();
                            Log.d(TAG, "Registering push notifications token: " + token);
                            pinpointManager.getNotificationClient().registerDeviceToken(token);
                        }
                    });
        }
        return pinpointManager;
    }

    public void assignUserIdToEndpoint() {
        TargetingClient targetingClient = pinpointManager.getTargetingClient();
        EndpointProfile endpointProfile = targetingClient.currentEndpoint();
        EndpointProfileUser endpointProfileUser = new EndpointProfileUser();
        endpointProfileUser.setUserId("UserIdValue");
        endpointProfile.setUser(endpointProfileUser);
        targetingClient.updateEndpointProfile(endpointProfile);
        Log.d(TAG, "Assigned user ID " + endpointProfileUser.getUserId() +
                " to endpoint " + endpointProfile.getEndpointId());
    }

    private void recordAddObservationButtonTap() {
        AnalyticsEvent event = AnalyticsEvent.builder()
                .name("Add Observation Button Pressed")
                .addProperty("Successful", true)
                .build();

        Amplify.Analytics.recordEvent(event);
    }

    private void recordUser() {
        UserProfile.Location location = UserProfile.Location.builder()
                .latitude(31.89810424121091)
                .longitude(35.86877187676645)
                .postalCode("98122")
                .city("Amman")
                .region("Marj Al Hamam")
                .country("Jordan")
                .build();

        AnalyticsProperties customProperties = AnalyticsProperties.builder()
                .add("property1", "Property value")
                .build();

        AnalyticsProperties userAttributes = AnalyticsProperties.builder()
                .add("someUserAttribute", "User attribute value")
                .build();

        AWSPinpointUserProfile profile = AWSPinpointUserProfile.builder()
                .name("test-user")
                .email("user@test.com")
                .plan("test-plan")
                .location(location)
                .customProperties(customProperties)
                .userAttributes(userAttributes)
                .build();

        String userId = Amplify.Auth.getCurrentUser().getUserId();

        Amplify.Analytics.identifyUser(userId, profile);
    }

    private void firebaseLogEvent() {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "FAB");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Button Press");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");

        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }
}