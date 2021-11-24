package com.d4coders.goodcitizen.ui.observation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Category;
import com.amplifyframework.datastore.generated.model.Problem;
import com.d4coders.goodcitizen.R;
import com.d4coders.goodcitizen.util.PointsManager;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObservationCreationActivity extends AppCompatActivity {

    private static final String TAG = "ObservationCreationActivity";

    private static final int PERMISSION_ID = 44;

    private FusedLocationProviderClient mFusedLocationClient;

    private final LocationCallback mLocationCallback = new LocationCallback() {
        @Override

        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            Log.i(TAG, "The location is => " + mLastLocation);
        }
    };

    // start activity for the result
    private final ActivityResultLauncher<Intent> cameraActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        assert data != null;
                        String key = data.getExtras().getString(CameraActivity.RESULT);
                        Log.i(TAG, "===============> " + key);
                        getObservationImageURL(key);
                    }
                }
            });

    private double latitude;
    private double longitude;
    private List<String> categories;
    private Map<String, String> categoriesMap;
    private ArrayAdapter<String> adapter;
    private String imageURL;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observation_creation);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        EditText titleEditText = findViewById(R.id.et_title);
        EditText descriptionEditText = findViewById(R.id.et_description);

        categories = new ArrayList<>();
        Spinner spinner = findViewById(R.id.observation_category);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        save = findViewById(R.id.btn_save);
        save.setOnClickListener(view -> {
            SharedPreferences sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
            String userId = sharedPreferences.getString("userId", "NO ID FOUND");

            String title = titleEditText.getText().toString();
            String description = descriptionEditText.getText().toString();
            String categoryId = categoriesMap.get(spinner.getSelectedItem().toString());

            reportProblem(userId, title, description, categoryId, imageURL, latitude, longitude);
            PointsManager.calculatePoints(5, getApplicationContext());
        });

        ImageButton camera = findViewById(R.id.camera);
        camera.setOnClickListener(view -> {
            cameraActivityResultLauncher.launch(new Intent(ObservationCreationActivity.this, CameraActivity.class));
        });
    }

    private void getObservationImageURL(String key) {
        Amplify.Storage.getUrl(key, success -> {
            imageURL = success.getUrl().toString();
            Log.i(TAG, "Image URL ====================> " + imageURL);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    save.setEnabled(true);
                }
            });
        }, error -> {
            Log.e(TAG, "Error getting URL ====================> " + error.toString());
        });
    }

    private void getCategories() {
        categoriesMap = new HashMap<>();
        Amplify.API.query(ModelQuery.list(Category.class), success -> {
            for (Category category:
                 success.getData()) {
                categories.add(category.getTitle());
                categoriesMap.put(category.getTitle(), category.getId());
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
        }, error -> {
            Log.e(TAG, "Error getting categories");
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 214) {
            if (resultCode == RESULT_OK) {
                assert data != null;
                String[] list = data.getStringArrayExtra("resultData");

                Log.i(TAG, "the images are 1 => " + list[0]);
                Log.i(TAG, "the images are 2 => " + list[1]);
                Log.i(TAG, "the images are 3 => " + list[2]);
            }
        }
    }

    private void reportProblem(String userId, String title, String description, String categoryId, String image, double latitude, double longitude) {
        Problem problem = Problem.builder()
                        .title(title)
                        .userId(userId)
                        .categoryId(categoryId)
                        .imageUrl(image)
                        .description(description)
                        .latitude(latitude)
                        .longitude(longitude)
                        .build();

        Amplify.API.mutate(
                ModelMutation.create(problem),
                observationSaved -> {
                    runOnUiThread(() -> Toast.makeText(getApplicationContext(),
                            "Problem reported",
                            Toast.LENGTH_SHORT).show());
                },
                error -> {
                    Log.e(TAG, "Problem not reported => " + error);
                });
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        if (checkPermissions()) {

            if (isLocationEnabled()) {

                mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {

                        Location location = task.getResult();

                        if (location == null) {
                            requestNewLocationData();
                        } else {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                });
            } else {
                Toast.makeText(this, "Please turn on your location...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {
        // Initializing LocationRequest
        // object with appropriate methods
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5);
        locationRequest.setFastestInterval(0);
        locationRequest.setNumUpdates(10);

        // setting LocationRequest
        // on FusedLocationClient
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this); // this may or may not be needed
        mFusedLocationClient.requestLocationUpdates(locationRequest, mLocationCallback, Looper.myLooper());
    }

    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        // If we want background location
        // on Android 10.0 and higher,
        // use:
        // ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID);
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getLastLocation();
        getCategories();
    }
}