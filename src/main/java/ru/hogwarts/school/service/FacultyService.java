package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final Map<Long, Faculty> faculties = new HashMap<>();
    private long counter = 0L;

    public Collection<Faculty> getAll() {
        return this.faculties.values();
    }

    public Faculty addFaculty(Faculty faculty) {
        long newId = this.counter++;
        faculty.setId(counter);
        faculties.put(newId, faculty);
        return faculty;
    }

    public Faculty updateFaculty(Long id, Faculty newStudent) {
        if (this.faculties.containsKey(id)) {
            Faculty oldStudent = this.faculties.get(id);
            oldStudent.setColor(newStudent.getColor());
            oldStudent.setName(newStudent.getName());
            return oldStudent;
        }
        else {
            throw new FacultyNotFoundException();
        }
    }

    public Faculty getFaculty(Long id) {
        if(this.faculties.containsValue(id)) {
            return this.faculties.get(id);
        }
        else {
            throw new FacultyNotFoundException();
        }
    }

    public Collection<Faculty> getStudentByColor(String color) {
        return faculties.values()
                .stream()
                .filter(s -> s.getColor().equals(color))
                .collect(Collectors.toList());
    }

    public void removeFaculty(Long id) {
        if (this.faculties.containsValue(id)) {
            faculties.remove(id);
        }
        else {
            throw new FacultyNotFoundException();
        }
    }
}
