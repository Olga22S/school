package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentService {

    Student add(Student student);

    Student remove(String name);

    Student update(Student student);

    Student get(String name);

    Collection<Student> getAll();

    Collection<Student> getByAge(int age);
}
