package com.example.lectureprep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);


        Button addStudentBTN = findViewById(R.id.submitStudentInfo);
        addStudentBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText studentNameField = findViewById(R.id.studentNameInput);
                String studentName = studentNameField.getText().toString();

                EditText graduationYearField = findViewById(R.id.studentGraduationYearInput);
                int graduationYear = Integer.parseInt(graduationYearField.getText().toString());

                Student student = new Student(studentName, graduationYear);
                Long addedStudentID = AppDatabase.getInstance(getApplicationContext()).studentDao().insertStudent(student);

                System.out.println(
                        "++++++++++++++++++++++++++++++++++++++++++++++++++" +
                                " Student ID : " + addedStudentID
                                +
                                "++++++++++++++++++++++++++++++++++++++++++++++++++"
                );


            }
        });


    }
}