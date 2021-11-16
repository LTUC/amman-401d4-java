package com.d4coders.goodcitizen.ui.observation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.amplifyframework.core.Amplify;
import com.d4coders.goodcitizen.R;
import com.otaliastudios.cameraview.CameraException;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.PictureResult;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CameraActivity extends AppCompatActivity {

    private static final String TAG = "CameraActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        CameraView camera = findViewById(R.id.camera);
        camera.setLifecycleOwner(this);
        camera.addCameraListener(new CameraListener() {
            @Override
            public void onCameraClosed() {
                super.onCameraClosed();

                Log.i(TAG, "Camera was closed");
            }

            @Override
            public void onCameraError(@NonNull CameraException exception) {
                super.onCameraError(exception);

                Log.e(TAG, "An error occurred taking the photo => " + exception.getMessage());
            }

            @Override
            public void onPictureTaken(@NonNull PictureResult result) {
                super.onPictureTaken(result);

                uploadPicture(result);
            }
        });
    }

    private String generateFileName() {
        String pattern = "yyyy_MM_dd_HH_mm_ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.CANADA);

        return simpleDateFormat.format(new Date());
    }

    private void uploadPicture(PictureResult pictureResult) {
        String fileName = generateFileName();
        fileName = fileName + "." + pictureResult.getFormat().name();

        Log.i(TAG, "Filename is => " + fileName);

        File exampleFile = new File(getApplicationContext().getFilesDir(), fileName);

        String finalFileName = fileName;
        pictureResult.toFile(exampleFile, file -> {
            assert file != null;
            Amplify.Storage.uploadFile(
                    finalFileName,
                    file,
                    result -> {
                        Log.i(TAG, "Successfully uploaded: " + result.getKey());
//                        finish(); // closes the activity
                    },
                    storageFailure -> Log.e(TAG, "Upload failed", storageFailure)
            );
        });
    }

    /*
    private void uploadFile() {
    File exampleFile = new File(getApplicationContext().getFilesDir(), "ExampleKey");

    try {
        BufferedWriter writer = new BufferedWriter(new FileWriter(exampleFile));
        writer.append("Example file contents");
        writer.close();
    } catch (Exception exception) {
        Log.e("MyAmplifyApp", "Upload failed", exception);
    }

    Amplify.Storage.uploadFile(
            "ExampleKey",
            exampleFile,
            result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
            storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
    );
}
     */
}