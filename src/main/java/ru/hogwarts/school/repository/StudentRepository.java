package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findStudentsByAge(int age);

    List<Student> findByAgeBetween(int min, int max);

    @Query("SELECT new Student(s.id, s.name, s.age) FROM Student s WHERE s.faculty.id = :id")
    Collection<Student> getStudentsByFacultyId(long id);
}
