package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.mapper.StudentMapper;
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

    public StudentDTO addStudent (Student student) {
        return StudentMapper.toDto(this.studentRepository.save(student));
    }

    public StudentDTO updateStudent(Long id, Student newStudent) {
        Student student =
                this.studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        student.setName(newStudent.getName());
        student.setAge((newStudent.getAge()));
        return StudentMapper.toDto(this.studentRepository.save(student));
    }

    public StudentDTO getStudent(Long id) {
        return this.studentRepository.findById(id).
                map(StudentMapper::toDto)
                .orElseThrow(StudentNotFoundException::new);
    }

    public Collection<StudentDTO> getStudentByAge(int age) {
        return this.studentRepository.getStudentByAge(age).stream()
                .map(StudentMapper::toDto)
                .collect(Collectors.toList());
    }

    public Collection<StudentDTO> getStudentsByAgeBetween(int minAge, int maxAge) {
        return this.studentRepository.getStudentsByAgeBetween(minAge, maxAge).stream()
                .map(StudentMapper::toDto)
                .collect(Collectors.toList());
    }

    public Collection<StudentDTO> getAll() {
        return this.studentRepository.findAll().stream()
                .map(StudentMapper::toDto)
                .collect(Collectors.toList());
    }

    public StudentDTO removeStudent(Long id) {
        Student student =
                this.studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        this.studentRepository.delete(student);
        return StudentMapper.toDto(student);
    }

}
