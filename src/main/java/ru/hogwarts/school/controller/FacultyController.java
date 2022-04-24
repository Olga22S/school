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

    @GetMapping("/{name}")
    public Faculty getFaculty(@PathVariable String name) {
        return service.get(name);
    }

    @DeleteMapping("/{name}")
    public Faculty removeFaculty(@PathVariable String name) {
        return service.remove(name);
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

    @GetMapping
    public Collection<Faculty> getFacultiesByColor(@RequestParam String color) {
        return service.getByColor(color);
    }
}
