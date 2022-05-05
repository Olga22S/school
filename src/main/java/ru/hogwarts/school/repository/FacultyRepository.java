package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Collection<Faculty> findFacultyByColor(String color);

    Faculty findFacultyByNameIgnoreCase(String name);

    @Query("SELECT new Faculty (f.id, f.name, f.color) " +
            "FROM Faculty as f, Student as s WHERE s.faculty.id = f.id AND s.id=:id")
    Faculty getFacultyByStudentId(long id);
}
