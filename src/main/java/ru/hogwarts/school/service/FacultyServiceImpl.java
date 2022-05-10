package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exeption.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository repository;

    public FacultyServiceImpl(FacultyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Faculty add(Faculty faculty) {
        return repository.save(faculty);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Faculty update(Faculty faculty) {
        return repository.save(faculty);
    }

    @Override
    public Faculty get(Long id) {
        return repository.findById(id).orElseThrow(FacultyNotFoundException::new);
    }

    @Override
    public Collection<Faculty> getAll() {
        return repository.findAll();
    }

    @Override
    public Collection<Faculty> getByColor(String color) {
        return repository.findFacultyByColor(color);
    }

    @Override
    public Faculty getFacultyByNameIgnoreCase(String name) {
        return repository.findFacultyByNameIgnoreCase(name);
    }

    @Override
    public Faculty getFacultyByStudentId(long id) {
        return repository.findFacultyByStudentsId(id);
    }
}
