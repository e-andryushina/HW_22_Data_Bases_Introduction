package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent (Student student) {
        return this.studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student newStudent) {
        Student student =
                this.studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        student.setName(newStudent.getName());
        student.setAge((newStudent.getAge()));
        return this.studentRepository.save(student);

    }

    public Student getStudent(Long id) {
        return this.studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);

    }

    public Collection<Student> getStudentByAge(int age) {
        return this.studentRepository.getStudentByAge(age);
    }

    public Collection<Student> getAll() {
        return this.studentRepository.findAll();
    }

    public void removeStudent(Long id) {
        Student student =
                this.studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        this.studentRepository.delete(student);
    }

}
