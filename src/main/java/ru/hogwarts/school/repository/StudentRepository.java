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

    Collection<Student> getStudentsByFacultyId(long id);

    @Query("SELECT COUNT(s) FROM Student s")
    Long getNumberOfAllStudents();

    @Query("SELECT AVG(s.age) FROM Student s")
    Integer getAverageStudentsAge();

    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    Collection<Student> getLastFiveStudents();
}
