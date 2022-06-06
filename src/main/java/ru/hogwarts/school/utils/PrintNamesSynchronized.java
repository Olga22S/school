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
            for (int i = startedIndex; i <= startedIndex + 1; i++) {
                System.out.println(students.get(i).getName());
            }
        }
    }
}
