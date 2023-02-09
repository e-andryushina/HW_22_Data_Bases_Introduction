package ru.hogwarts.school.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {
    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @Operation(summary = "Returns list of all students", tags = "student")
    public Collection<Student> getAll() {
        return this.studentService.getAll();
    }

    @GetMapping ("/{id}")
    @Operation(summary = "Returns a student by id", tags = "student")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Student model", content = @Content(schema = @Schema(implementation = Student.class))),
            @ApiResponse(responseCode = "404", description = "Student not found", content = @Content)
    })
    public Student getStudentById(@PathVariable("id") long id) {
        return studentService.getStudent(id);
    }

    @GetMapping("/age/{age}")
    public Collection<Student> getStudentsByAge(@PathVariable("age") int age) {
        return studentService.getStudentByAge(age);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping("/{id}")
    public Student changeStudent(@PathVariable("id") long id, @RequestBody Student student){
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") long id) {
        studentService.removeStudent(id);
        return ResponseEntity.noContent().build();
    }


}
