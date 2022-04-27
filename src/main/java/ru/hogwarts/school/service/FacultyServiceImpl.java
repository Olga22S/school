package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.exeption.FacultyNotFoundException;
import ru.hogwarts.exeption.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final Map<Long, Faculty> faculties = new HashMap<>();
    private long counter;

    @Override
    public Faculty add(Faculty faculty) {
        counter++;
        faculty.setId(counter);
        faculties.put(counter, faculty);
        return faculty;
    }

    @Override
    public Faculty remove(Long id) {
        Faculty faculty = faculties.remove(id);
        if (isNull(faculty)) {
            throw new FacultyNotFoundException();
        }
        return faculty;
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
    public Faculty get(Long id) {
        Faculty faculty = faculties.get(id);
        if (isNull(faculty)) {
            throw new StudentNotFoundException();
        }
        return faculty;
    }

    @Override
    public Collection<Faculty> getAll() {
        return Collections.unmodifiableCollection(faculties.values());
    }

    @Override
    public Collection<Faculty> getByColor(String color) {
        return faculties.values().stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .collect(Collectors.toList());
    }
}
