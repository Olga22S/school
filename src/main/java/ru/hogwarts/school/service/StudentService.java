package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentService {

    Student add(Student student);

    void remove(Long id);

    Student update(Student student);

    Student get(Long id);

    Collection<Student> getAll();

    Collection<Student> getByAge(int age);

    Collection<Student> findByAgeBetween(int min, int max);

    Collection<Student> getStudentsByFacultyId(long id);

    Long getNumberOfAllStudents();

    Integer getAverageStudentsAge();

    Collection<Student> getLastFiveStudents();

    List<String> getStudentsNameBeginsWithLetter(char letter);

    Double getStudentAverageAgeUsingStream();

    Integer getIterating();

    void printStudentsName();

    void printStudentsNameSynchronized();
}
