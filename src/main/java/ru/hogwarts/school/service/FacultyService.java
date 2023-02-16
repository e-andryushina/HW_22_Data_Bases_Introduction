package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Collection<Faculty> getAll() {

        return this.facultyRepository.findAll();
    }

    public Faculty addFaculty(Faculty faculty) {
        return this.facultyRepository.save(faculty);
    }

    public Faculty updateFaculty(Long id, Faculty newFaculty) {
        Faculty faculty =
                this.facultyRepository.findById(id).orElseThrow(FacultyNotFoundException::new);
        faculty.setName(newFaculty.getName());
        faculty.setName(newFaculty.getColor());
        return this.facultyRepository.save(faculty);
    }

    public Faculty getFaculty(Long id) {
        return this.facultyRepository.findById(id)
                .orElseThrow(FacultyNotFoundException::new);
    }

    public Collection<Faculty> getFacultyByColor(String color) {
        return this.facultyRepository.findByColor(color);
    }

    public void removeFaculty(Long id) {
        Faculty faculty =
                this.facultyRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        this.facultyRepository.delete(faculty);
    }

}
