package com.recurjun.betterhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Note;
import com.amplifyframework.datastore.generated.model.Task;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private EditText task;
    private EditText note;
    private TextView label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureAmplify();

        task = findViewById(R.id.edit_text_task);
        note = findViewById(R.id.edit_text_note);
        label = findViewById(R.id.text_view_item_name);

        Button logButton = findViewById(R.id.button_log);
        logButton.setOnClickListener(view -> {
            dataStore(task.getText().toString());
            api(note.getText().toString());
        });
    }

    private void dataStore(String data) {
        Task task = Task.builder().title(data).status("Pending").description("This is important work").build();

        // save with the datastore
        Amplify.DataStore.save(task, result -> {
            Log.i(TAG, "Task Saved");
        }, error -> {
            Log.i(TAG, "Task Not Saved");
        });

        // query with the datastore
        Amplify.DataStore.query(
                Task.class,
                queryMatches -> {
                    while (queryMatches.hasNext()) {
                        Log.i(TAG, "Successful query, found tasks.");
                        Task foundExpense = queryMatches.next();
                        Log.i(TAG, foundExpense.getTitle());
                        label.setText(foundExpense.getTitle());
                    }
                },
                error -> {
                    Log.i(TAG,  "Error retrieving expenses", error);
                });
    }

    private void api(String data) {
        Note note = Note.builder().content(data).build();
        // mutation with the API (GraphQL)
        Amplify.API.mutate(ModelMutation.create(note), success -> {
            Log.i(TAG, "Saved the note => " + success.getData().getId());
        }, error -> {
            Log.i(TAG, "Note not saved");
        });

        Amplify.API.query(
                ModelQuery.get(Note.class, ""), // TODO: 11/8/21 replace with your own ID
                success -> {
                    Log.i(TAG, "We got the record => " + success.getData().getContent());
                },
                error -> {
                    Log.i(TAG, "We got an error => " + error.toString());
                });
    }

    private void configureAmplify() {
        try {
            Amplify.addPlugin(new AWSDataStorePlugin()); // stores records locally
            Amplify.addPlugin(new AWSApiPlugin()); // stores things in DynamoDB and allows us to perform GraphQL queries
            Amplify.configure(getApplicationContext());

            Log.i(TAG, "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e(TAG, "Could not initialize Amplify", error);
        }
    }
}