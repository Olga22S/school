package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.exeption.StudentNotFoundException;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class StudentServiceImpl implements StudentService {

    private final Map<Long, Student> students = new HashMap<>();
    private long counter;

    @Override
    public Student add(Student student) {
        counter++;
        student.setId(counter);
        students.put(counter, student);
        return student;
    }

    @Override
    public Student remove(Long id) {
        Student student = students.remove(id);
        if (isNull(student)) {
            throw new StudentNotFoundException();
        }
        return student;
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
    public Student get(Long id) {
        Student student = students.get(id);
        if (isNull(student)) {
            throw new StudentNotFoundException();
        }
        return student;
    }

    @Override
    public Collection<Student> getAll() {
        return Collections.unmodifiableCollection(students.values());
    }

    @Override
    public Collection<Student> getByAge(int age) {
        return students.values().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
    }
}
