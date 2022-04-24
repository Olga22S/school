package ru.hogwarts.school.service;

import ru.hogwarts.exeption.FacultyNotFoundException;
import ru.hogwarts.exeption.StudentExistsException;
import ru.hogwarts.exeption.StudentNotFoundException;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StudentServiceImpl implements StudentService {

    private final Map<Long, Student> students = new HashMap<>();
    private long counter;


    @Override
    public Student add(Student student) {
        if (students.containsKey(student.getId())) {
            throw new StudentExistsException();
        }
        students.put(student.getId(), student);
        counter++;
        return student;
    }

    @Override
    public Student remove(Student student) {
        if (students.containsKey(student.getId())) {
            counter--;
            return students.remove(student.getId());
        }
        throw new StudentNotFoundException();
    }

    @Override
    public Student update(Student student) {
        if (students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            return student;
        }
        throw new StudentNotFoundException();
    }

    @Override
    public Student get(String name) {
        return students.values().stream()
                .filter(student -> student.getName().equals(name))
                .findFirst()
                .orElseThrow(FacultyNotFoundException::new);
    }

    @Override
    public Collection<Student> getAll() {
        return Collections.unmodifiableCollection(students.values());
    }
}
