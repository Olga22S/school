package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

public interface FacultyService {

    Faculty add(Faculty faculty);

    Faculty remove(String name);

    Faculty update(Faculty faculty);

    Faculty get(String name);

    Collection<Faculty> getAll();

    Collection<Faculty> getByColor(String color);
}
