package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.exception.ObjectNotFoundException;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.mapper.FacultyMapper;
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

    public Collection<FacultyDTO> getAll() {
        return this.facultyRepository.findAll().stream()
                .map(FacultyMapper::toDto)
                .collect(Collectors.toList());
    }

    public FacultyDTO addFaculty(FacultyDTO faculty) {
        return FacultyMapper.toDto(this.facultyRepository.save(FacultyMapper.toEntity(faculty)));
    }

    public FacultyDTO updateFaculty(Long id, Faculty facultyDTO) {
        Faculty faculty =
                this.facultyRepository.findById(id).orElseThrow(FacultyNotFoundException::new);
        faculty.setName(facultyDTO.getName());
        faculty.setColor(facultyDTO.getColor());
        return FacultyMapper.toDto(this.facultyRepository.save(faculty));
    }

    public FacultyDTO getFaculty(Long id) {
        return this.facultyRepository.findById(id)
                .map(FacultyMapper::toDto)
                .orElseThrow(FacultyNotFoundException::new);
    }

    public Collection<FacultyDTO> getFacultyByColor(String color) {
                return this.facultyRepository.findByColor(color).stream()
                        .map(FacultyMapper::toDto)
                        .collect(Collectors.toList());
    }

    public FacultyDTO getByNameOrColor(String nameOrColor) {
        return this.facultyRepository
                .findByNameIgnoreCaseOrColorIgnoreCase(nameOrColor, nameOrColor)
                .map(FacultyMapper::toDto)
                .orElseThrow(ObjectNotFoundException::new);
    }

    public FacultyDTO removeFaculty(Long id) {
        Faculty faculty =
                this.facultyRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        this.facultyRepository.delete(faculty);
        return FacultyMapper.toDto(faculty);
    }

}
