package com.mycompany.performance;

import java.util.ArrayList;
import java.util.List;

public class StaticCollectionTest {
    public static List<Student> list = new ArrayList<Student>();

    public void addStudentsToList() {
        for (int i = 0; i < 1E7; i++) {
            list.add(new Student("studentName_" + i));
        }

        Waiting.pause();
    }

    public static void main(String[] args) {
        new StaticCollectionTest().addStudentsToList();
    }

}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }
}
