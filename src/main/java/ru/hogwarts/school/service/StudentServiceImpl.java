package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exeption.StudentNotFoundException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.utils.PrintNamesSynchronized;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;
    private final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Student add(Student student) {
        logger.info("Was invoked method to add a student");
        return repository.save(student);
    }

    @Override
    public void remove(Long id) {
        logger.info("Was invoked method to remove a student");
        repository.deleteById(id);
    }

    @Override
    public Student update(Student student) {
        logger.info("Was invoked method to update a student={}", student);
        return repository.save(student);
    }

    @Override
    public Student get(Long id) {
        logger.info("Was invoked method to get a student");
        return repository.findById(id).orElseThrow(StudentNotFoundException::new);
    }

    @Override
    public Collection<Student> getAll() {
        logger.info("Was invoked method to get all students");
        return repository.findAll();
    }

    @Override
    public Collection<Student> getByAge(int age) {
        logger.info("Was invoked method to get students by age={}", age);
        return repository.findStudentsByAge(age);
    }

    @Override
    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.info("Was invoked method to get students by age between min={} max={}", min, max);
        return repository.findByAgeBetween(min, max);
    }

    @Override
    public Collection<Student> getStudentsByFacultyId(long id) {
        logger.info("Was invoked method to get students by faculty id={}", id);
        return repository.getStudentsByFacultyId(id);
    }

    @Override
    public Long getNumberOfAllStudents() {
        logger.info("Was invoked method to get a number of all students");
        return repository.getNumberOfAllStudents();
    }

    @Override
    public Integer getAverageStudentsAge() {
        logger.info("Was invoked method to get average student age");
        return repository.getAverageStudentsAge();
    }

    @Override
    public Collection<Student> getLastFiveStudents() {
        logger.info("Was invoked method to get the last five students");
        return repository.getLastFiveStudents();
    }

    @Override
    public List<String> getStudentsNameBeginsWithLetter(char letter) {
        logger.info("Was invoked method to get the names begins with letter={}", letter);
        return repository.findAll().stream()
                .parallel()
                .filter(student -> student.getName().startsWith(String.valueOf(letter)))
                .map(student -> student.getName().toUpperCase())
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public Double getStudentAverageAgeUsingStream() {
        logger.info("Was invoked method to get average age using stream");
        return repository.findAll().stream()
                .parallel()
                .mapToInt(Student::getAge)
                .average()
                .getAsDouble();
    }

    @Override
    public Integer getIterating() {
        return Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .reduce(0, Integer::sum);
    }

    @Override
    public void printStudentsName() {
        List<Student> students = repository.findAll();
        System.out.println(students.get(0).getName());
        System.out.println(students.get(1).getName());
        new Thread(() -> {
            System.out.println(students.get(2).getName());
            System.out.println(students.get(3).getName());
            new Thread(() -> {
                System.out.println(students.get(4).getName());
                System.out.println(students.get(5).getName());
            }).start();
        }).start();
    }

    @Override
    public void printStudentsNameSynchronized() {
        List<Student> students = repository.findAll();
        for (int i = 0; i < students.size(); i += 2) {
            new Thread(new PrintNamesSynchronized(students, i)).start();
        }
    }
}
