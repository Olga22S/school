package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService service;

    public FacultyController(FacultyService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Faculty getFaculty(@PathVariable long id) {
        return service.get(id);
    }

    @DeleteMapping("/{id}")
    public void removeFaculty(@PathVariable long id) {
        service.remove(id);
    }

    @PostMapping
    public Faculty addFaculty(@RequestBody Faculty faculty) {
        return service.add(faculty);
    }

    @PutMapping
    public Faculty updateFaculty(@RequestBody Faculty faculty) {
        return service.update(faculty);
    }

    @GetMapping("/all")
    public Collection<Faculty> getAll() {
        return service.getAll();
    }

    @GetMapping("/color")
    public Collection<Faculty> getFacultiesByColor(@RequestParam String color) {
        return service.getByColor(color);
    }

    @GetMapping("/name")
    public Faculty getFacultyByName(@RequestParam String name) {
        return service.getFacultyByNameIgnoreCase(name);
    }

    @GetMapping("/student")
    public Faculty getFacultyByStudentId(@RequestParam long id) {
        return service.getFacultyByStudentId(id);
    }
}
