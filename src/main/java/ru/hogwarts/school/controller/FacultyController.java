package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;
import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

   FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    @Operation(summary = "Returns list of all faculties", tags = "faculty")
    public Collection<Faculty> getAll() {
        return this.facultyService.getAll();
    }


    @GetMapping ("/{id}")
    public Faculty getFacultyById(@PathVariable("id") long id) {
        return facultyService.getFaculty(id);
    }

    @GetMapping("/color/{color}")
    public Collection<Faculty> getFacultyByColor(@PathVariable("color") String color) {
        return facultyService.getFacultyByColor(color);
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @PutMapping("/{id}")
    public Faculty changeFaculty(@PathVariable("id") long id, @RequestBody Faculty faculty){
        return facultyService.updateFaculty(id, faculty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable("id") long id) {
        facultyService.removeFaculty(id);
        return ResponseEntity.noContent().build();
    }
}
