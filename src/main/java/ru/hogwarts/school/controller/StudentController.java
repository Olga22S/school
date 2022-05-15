package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable long id) {
        return service.get(id);
    }

    @DeleteMapping("/{id}")
    public void removeStudent(@PathVariable long id) {
        service.remove(id);
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return service.add(student);
    }

    @PutMapping
    public Student updateStudent(@RequestBody Student student) {
        return service.update(student);
    }

    @GetMapping("/all")
    public Collection<Student> getAll() {
        return service.getAll();
    }

    @GetMapping("/age")
    public Collection<Student> getStudentsByAge(@RequestParam int age) {
        return service.getByAge(age);
    }

    @GetMapping("/age/")
    public Collection<Student> getStudentsByAgeBetween(@RequestParam int min, @RequestParam int max) {
        return service.findByAgeBetween(min, max);
    }

    @GetMapping("/faculty")
    public Collection<Student> getStudentsByFacultyId(@RequestParam long id) {
        return service.getStudentsByFacultyId(id);
    }

    @GetMapping("/student-counter")
    public Long getNumberOfAllStudents() {
        return service.getNumberOfAllStudents();
    }

    @GetMapping("/average-age")
    public Integer getAverageStudentsAge() {
        return service.getAverageStudentsAge();
    }

    @GetMapping("/last-five-students")
    public Collection<Student> getLastFiveStudents() {
        return service.getLastFiveStudents();
    }
}
