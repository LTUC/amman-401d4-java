package com.example.studentsrecycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create data to use in the view:

        ArrayList<Student> studentData = new ArrayList<Student>();

        studentData.add(new Student("Osaid",2019));
        studentData.add(new Student("Farah",2020));
        studentData.add(new Student("Mohammad",2010));
        studentData.add(new Student("AbdelRahman",2012));
        studentData.add(new Student("Abrar",2020));
        studentData.add(new Student("Anas",2020));
        studentData.add(new Student("KAfaween",2020));
        studentData.add(new Student("Adham",2020));
        studentData.add(new Student("Ammar",2020));


        // get the Recyler view
        RecyclerView allStudentRecyclerView = findViewById(R.id.recycleViewId);

        // set a layout manager
        allStudentRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // set the adapter for this recycler view
        allStudentRecyclerView.setAdapter(new StudentAdapter(studentData));


    }
}