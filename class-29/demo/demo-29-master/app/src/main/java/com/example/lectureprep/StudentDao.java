package com.example.lectureprep;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StudentDao {


    @Query("SELECT * FROM student")
    List<Student> getAll();

    @Query("SELECT * FROM student WHERE id = :id")
    Student getStudentById(Long id);

    @Insert
    Long insertStudent(Student student); // void: is not gonna return anytihng where the long return the id of the inserted student



}
