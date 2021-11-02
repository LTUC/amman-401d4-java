package com.example.studentsrecycleview;


// create the model class
// create the fragment (represent a single). it created to be used over and over again to cover all the data items
// create adapter
public class Student {
    public String student;
    public Integer yearOfGradauattion;

    public Student(String student, Integer yearOfGradauattion){
        this.student = student;
        this.yearOfGradauattion =yearOfGradauattion;

    }

}
