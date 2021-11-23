package com.d4coders.goodcitizen.ui.dashboard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

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
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Problem;
import com.d4coders.goodcitizen.R;
import com.d4coders.goodcitizen.ui.dashboard.adapter.RecentObservationsAdapter;
import com.d4coders.goodcitizen.ui.observation.ObservationCreationActivity;
import com.d4coders.goodcitizen.ui.observation.ObservationDetailsActivity;
import com.d4coders.goodcitizen.ui.profile.ProfileActivity;
import com.d4coders.goodcitizen.ui.settings.SettingsActivity;
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

public class DashboardActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static PinpointManager pinpointManager;

    private FirebaseAnalytics mFirebaseAnalytics;
    private List<Problem> observations;
    private RecentObservationsAdapter recentObservationsAdapter;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        observations = new ArrayList<>();

        // Initialize PinpointManager
        getPinpointManager(getApplicationContext());

        assignUserIdToEndpoint();

        userSession();

        RecyclerView recyclerView = findViewById(R.id.recent_observations);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recentObservationsAdapter = new RecentObservationsAdapter(observations, (view, position) -> {
            Intent intent = new Intent(getApplicationContext(), ObservationDetailsActivity.class);
            intent.putExtra(ObservationDetailsActivity.OBSERVATION_ID, observations.get(position).getId());
            startActivity(intent);
        });
        recyclerView.setAdapter(recentObservationsAdapter);

        Button editProfile = findViewById(R.id.button_editProfile);
        editProfile.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {

            // firebase and pinpoint events
            firebaseLogEvent();
            recordUser();
            recordAddObservationButtonTap();
            startActivity(new Intent(getApplicationContext(), ObservationCreationActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getRecentObservations();
    }

    private void getRecentObservations() {
        Amplify.API.query(ModelQuery.list(Problem.class), success-> {
            for (Problem problem :
                    success.getData()) {
                observations.add(problem);
            }

            runOnUiThread(() -> recentObservationsAdapter.notifyItemInserted(observations.size()));
        }, error -> {});
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;

            case R.id.logout:
                logout();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    private void logout() {
        // TODO: 11/18/21 call log out methos for cognito
    }
}