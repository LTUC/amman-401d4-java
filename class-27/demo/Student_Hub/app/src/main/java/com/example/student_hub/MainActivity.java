package com.example.student_hub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//Intent Put and Get Extras (share data between Activities)


//        findViewById(R.id.GoToShow).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent goToshow = new Intent(MainActivity.this,ShowText.class);
//
//                TextView text = findViewById(R.id.stdNameInput);
//                String name = text.getText().toString();
       // this will work if we navigate to to the activity right away and that is why we are gonna change it to SharedPreference
//                 store as key and value to pass
//                goToshow.putExtra("StdName",name);
//
////                startActivity(goToshow);
//            }
//        });


        findViewById(R.id.GotoStdPage).setOnClickListener(view -> {
           Intent gotToStd = new Intent(MainActivity.this,AddStudent.class);
           startActivity(gotToStd);
        });

        findViewById(R.id.GoToInst).setOnClickListener(view -> {
            Intent gotToInst = new Intent(MainActivity.this,InstructorPage.class);
            startActivity(gotToInst);
        });





    }

    // if we used this here it will accessible only if the Activity go through the whole lifecycle again to start with onCreate.(becuase startActivity with recreat the Activity again)
//    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//    String instName = sharedPreferences.getString("InstructorName","Go and set the Instructor Name");
//
//    TextView welcome = findViewById(R.id.WelcomeMsg);
//        welcome.setText("Welcome " + instName);


    // we should have it onResume inorder to see the changes
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String instName = sharedPreferences.getString("InstructorName","Go and set the Instructor Name");

        TextView welcome = findViewById(R.id.WelcomeMsg);
        welcome.setText("Welcome " + instName);
    }
}