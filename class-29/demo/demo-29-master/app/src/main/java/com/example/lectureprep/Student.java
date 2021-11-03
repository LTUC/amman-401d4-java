package com.example.lectureprep;


// create the model class
// create the fragment (represent a single). it created to be used over and over again to cover all the data items
// create adapter

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Student {

    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo(name = "student_name")
    public String student;

    @ColumnInfo(name = "year_of_graduation")
    public Integer yearOfGradauattion;

    public Student(String student, Integer yearOfGradauattion){
        this.student = student;
        this.yearOfGradauattion =yearOfGradauattion;

    }

}
