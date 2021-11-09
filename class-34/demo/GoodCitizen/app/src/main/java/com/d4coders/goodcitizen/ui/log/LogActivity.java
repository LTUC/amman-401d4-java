package com.d4coders.goodcitizen.ui.log;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.User;
import com.d4coders.goodcitizen.R;

public class LogActivity extends AppCompatActivity {

    private static final String TAG = "LogActivity";

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        handler = new Handler(Looper.getMainLooper(), msg -> {
            apiCreateLogCall(msg.getData().getString("userId"));
            return true;
        });

        configureAWS();
        apiCreateUserCall();
    }

    private void apiCreateUserCall() {
        User user = User.builder()
                .firstName("Marah")
                .lastName("Joseph")
                .nationalId(Double.valueOf("9981059652"))
                .build();

        Amplify.API.mutate(
                ModelMutation.create(user),
                userSaved -> {
                    Log.i(TAG, "User saved");
                    Bundle bundle = new Bundle();
                    bundle.putString("userId", userSaved.getData().getId());

                    Message message = new Message();
                    message.setData(bundle);

                    handler.sendMessage(message);
                },
                error -> {
                    Log.e(TAG, "User not Saved => " + error.toString());
                }
        );
    }

    private void apiCreateLogCall(String userId) {
        com.amplifyframework.datastore.generated.model.Log log =
                com.amplifyframework.datastore.generated.model.Log.builder()
                        .name("Burst Mains")
                        .userId(userId)
                        .description("Burst mains at Jabbal Hussein Junction")
                        .build();

        Amplify.API.mutate(
                ModelMutation.create(log),
                logSaved -> {
                    Log.i(TAG, "Log saved");
                },
                error -> {
                    Log.e(TAG, "Log not Saved");
                });
    }

    private void configureAWS() {
        try {
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());

            Log.i("Tutorial", "Initialized Amplify");
        } catch (AmplifyException e) {
            Log.e("Tutorial", "Could not initialize Amplify", e);
        }
    }
}