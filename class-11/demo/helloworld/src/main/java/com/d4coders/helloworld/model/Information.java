package com.d4coders.helloworld.model;

import java.util.ArrayList;
import java.util.List;

public class Information {
    private final String className;
    private final int classCount;
    private final List<String> studentNames;

    public Information(String className, int classCount) {
        this.className = className;
        this.classCount = classCount;
        studentNames = new ArrayList<>();
    }

    public String getClassName() {
        return className;
    }

    public int getClassCount() {
        return classCount;
    }

    public List<String> getStudentNames() {
        return studentNames;
    }

    public void setStudentNames(String studentName) {
        studentNames.add(studentName);
    }
}
