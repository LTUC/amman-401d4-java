package com.example.student_hub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowText extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_text);
//get the intent obj
        Intent intent = getIntent();
//get using key the string you passed
        String stdName = intent.getExtras().getString("StdName");

        TextView text = findViewById(R.id.stdNameLable);

        text.setText(stdName);

    }
}