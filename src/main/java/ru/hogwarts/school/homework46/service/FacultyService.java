package ru.hogwarts.school.homework46.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.homework46.exception.FacultyNotFoundException;
import ru.hogwarts.school.homework46.repository.FacultyRepository;
import ru.hogwarts.school.homework46.model.Faculty;
import ru.hogwarts.school.homework46.model.Student;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FacultyService {

    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Optional<Faculty> getFaculty(Long id) {
        return facultyRepository.findById(id);
    }

    public Faculty updateFaculty(Faculty faculty) {
        Faculty oldFaculty = facultyRepository.findById(faculty.getId())
                .orElseThrow(FacultyNotFoundException::new);
        oldFaculty.setColor(faculty.getColor());
        oldFaculty.setName(faculty.getName());
        return facultyRepository.save(oldFaculty);
    }

    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    public List<Faculty> getFacultiesByColor(String color) {
        return facultyRepository.getFacultiesByColor(color);
    }

    public Faculty findByNameOrColor(String name) {
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(name, name);
    }

    public Set<Student> getStudentsById(Long id) {
        Optional<Faculty> optional = facultyRepository.findById(id);
        if (optional.isPresent()) {
            Faculty facultyFromDb = optional.get();
            return facultyFromDb.getStudents();
        } else {
            return null;
        }
    }

    public String longestFacultyName() {
        String result = facultyRepository.findAll()
                .stream()
                .map(Faculty::getName)
                .max(Comparator.comparing(String::length))
                .orElse("No faculties found");
        return result;
    }
}
