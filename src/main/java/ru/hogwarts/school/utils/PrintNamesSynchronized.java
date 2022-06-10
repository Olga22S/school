package ru.hogwarts.school.utils;

import ru.hogwarts.school.model.Student;

import java.util.List;

public class PrintNamesSynchronized implements Runnable {

    private List<Student> students;
    private int startedIndex;

    public PrintNamesSynchronized(List<Student> students, int startedIndex) {
        this.students = students;
        this.startedIndex = startedIndex;
    }

    @Override
    public void run() {
        synchronized (students) {
            System.out.println(students.get(startedIndex).getName());
            System.out.println(students.get(startedIndex + 1).getName());
        }
    }
}
