package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exeption.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository repository;
    private final Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);

    public FacultyServiceImpl(FacultyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Faculty add(Faculty faculty) {
        logger.info("Was invoked method to add a faculty");
        return repository.save(faculty);
    }

    @Override
    public void remove(Long id) {
        logger.info("Was invoked method to remove a faculty");
        repository.deleteById(id);
    }

    @Override
    public Faculty update(Faculty faculty) {
        logger.info("Was invoked method to update a faculty");
        return repository.save(faculty);
    }

    @Override
    public Faculty get(Long id) {
        logger.info("Was invoked method to get a faculty by id={}", id);
        return repository.findById(id).orElseThrow(FacultyNotFoundException::new);
    }

    @Override
    public Collection<Faculty> getAll() {
        logger.info("Was invoked method to get all faculties");
        return repository.findAll();
    }

    @Override
    public Collection<Faculty> getByColor(String color) {
        logger.info("Was invoked method to get a faculty by color={}", color);
        return repository.findFacultyByColor(color);
    }

    @Override
    public Faculty getFacultyByNameIgnoreCase(String name) {
        logger.info("Was invoked method to get a faculty by name={}", name);
        return repository.findFacultyByNameIgnoreCase(name);
    }

    @Override
    public Faculty getFacultyByStudentId(long id) {
        logger.info("Was invoked method to get a faculty by student id={}", id);
        return repository.findFacultyByStudentsId(id);
    }

    @Override
    public String getLongestFacultyName() {
        logger.info("Was invoked method to get the longest faculty name");
        return repository.findAll().stream()
                .parallel()
                .map(Faculty::getName)
                .reduce("", (left, right) -> left.length() > right.length() ? left : right);
    }
}
