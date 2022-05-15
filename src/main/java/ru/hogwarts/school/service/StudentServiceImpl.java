package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exeption.StudentNotFoundException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Student add(Student student) {
        return repository.save(student);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Student update(Student student) {
        return repository.save(student);
    }

    @Override
    public Student get(Long id) {
        return repository.findById(id).orElseThrow(StudentNotFoundException::new);
    }

    @Override
    public Collection<Student> getAll() {
        return repository.findAll();
    }

    @Override
    public Collection<Student> getByAge(int age) {
        return repository.findStudentsByAge(age);
    }

    @Override
    public Collection<Student> findByAgeBetween(int min, int max) {
        return repository.findByAgeBetween(min, max);
    }

    @Override
    public Collection<Student> getStudentsByFacultyId(long id) {
        return repository.getStudentsByFacultyId(id);
    }

    @Override
    public Long getNumberOfAllStudents() {
        return repository.getNumberOfAllStudents();
    }

    @Override
    public Integer getAverageStudentsAge() {
        return repository.getAverageStudentsAge();
    }

    @Override
    public Collection<Student> getLastFiveStudents() {
        return repository.getLastFiveStudents();
    }
}
