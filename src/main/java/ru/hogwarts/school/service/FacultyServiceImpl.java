package ru.hogwarts.school.service;

import ru.hogwarts.exeption.FacultyExistsException;
import ru.hogwarts.exeption.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FacultyServiceImpl implements FacultyService {

    private final Map<Long, Faculty> faculties = new HashMap<>();
    private long counter;

    @Override
    public Faculty add(Faculty faculty) {
        if (faculties.containsKey(faculty.getId())) {
            throw new FacultyExistsException();
        }
        faculties.put(faculty.getId(), faculty);
        counter++;
        return faculty;
    }

    @Override
    public Faculty remove(Faculty faculty) {
        if (faculties.containsKey(faculty.getId())) {
            return faculties.remove(faculty.getId());
        }
        throw new FacultyNotFoundException();
    }

    @Override
    public Faculty update(Faculty faculty) {
        if (faculties.containsKey(faculty.getId())) {
            faculties.put(faculty.getId(), faculty);
            return faculty;
        }
        throw new FacultyNotFoundException();
    }

    @Override
    public Faculty get(String name) {
        return faculties.values().stream()
                .filter(faculty -> faculty.getName().equals(name))
                .findFirst()
                .orElseThrow(FacultyNotFoundException::new);
    }

    @Override
    public Collection<Faculty> getAll() {
        return Collections.unmodifiableCollection(faculties.values());
    }
}
