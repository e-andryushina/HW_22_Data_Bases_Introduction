package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final Map<Long, Student> students = new HashMap<>();
    private long counter = 0L;

    public Student addStudent(Student student) {
        long newId = this.counter++;
        student.setId(counter);
        students.put(newId, student);
        return student;
    }

    public Student updateStudent(Long id, Student newStudent) {
        if (this.students.containsKey(id)) {
            Student oldStudent = this.students.get(id);
            oldStudent.setAge(newStudent.getAge());
            oldStudent.setName(newStudent.getName());
            return oldStudent;
        }
        else {
            throw new StudentNotFoundException();
        }
    }

    public Student getStudent(Long id) {
        if(this.students.containsKey(id)) {
            return this.students.get(id);
        }
        else {
            throw new StudentNotFoundException();
        }
    }

    public Collection<Student> getStudentByAge(int age) {
        return students.values()
                .stream()
                .filter(s -> s.getAge() == age)
                .collect(Collectors.toList());
    }

    public Collection<Student> getAll() {
        return this.students.values();
    }

    public void removeStudent(Long id) {
        if (this.students.containsValue(id)) {
            students.remove(id);
        }
        else {
            throw new StudentNotFoundException();
        }
    }
}
