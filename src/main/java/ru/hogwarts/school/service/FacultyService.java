package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

public interface FacultyService {

    Faculty add(Faculty faculty);

    Faculty remove(Faculty faculty);

    Faculty update(Faculty faculty);

    Faculty get(String name);

    Collection<Faculty> getAll();
}
