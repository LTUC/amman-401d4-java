package com.example.student_hub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class InstructorPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_page);

        findViewById(R.id.fromIntToHome).setOnClickListener(view -> {
            Intent intent = new Intent(InstructorPage.this,MainActivity.class);
           //when we start an activity we go through the lifeCycle again
            startActivity(intent);
        });
//we didn't put it inside the event listener because we only need to create it once and if i add it there it will called everytime I click the button

        // Storing data into SharedPreferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Creating an Editor object to edit(write to the file)
        SharedPreferences.Editor editor = sharedPreferences.edit();

        findViewById(R.id.saveData).setOnClickListener(view -> {
            TextView text = findViewById(R.id.InstNameInput);

            String name =text.getText().toString();

        // Storing the key and its value as the data fetched from edittext
            editor.putString("InstructorName",name);

         // Once the changes have been made,
         // we need to commit to apply those changes made,
         // otherwise, it will throw an error
            editor.apply();


        });



    }


}