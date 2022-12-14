package ru.hogwarts.school.homework46.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.homework46.exception.StudentNotFoundException;
import ru.hogwarts.school.homework46.repository.FacultyRepository;
import ru.hogwarts.school.homework46.repository.StudentRepository;
import ru.hogwarts.school.homework46.model.Faculty;
import ru.hogwarts.school.homework46.model.Student;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Service
public class StudentService {
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    public Student createStudent(Student student){
        logger.info("Was invoked method for creation of student");
        return studentRepository.save(student);
    }

    public Student getStudent(Long id) {
        logger.info("Was invoked method to find student");
        return studentRepository.findStudentById(id)
                .orElseThrow(StudentNotFoundException::new);
    }

    public Student updateStudent(Student student) {
        logger.info("Was invoked method to update student");
        Student oldStudent = studentRepository.findStudentById(student.getId())
                .orElseThrow(StudentNotFoundException::new);
        oldStudent.setName(student.getName());
        oldStudent.setAge(student.getAge());
        logger.debug("Student has been successfully updated");
        return studentRepository.save(oldStudent);
    }

    public void deleteStudent(Long id) {
        logger.info("Was invoked method to delete student");
        studentRepository.deleteById(id);
    }

    public List<Student> getStudentsByAge(int age) {
        logger.info("Was invoked method to find students by age");
        return studentRepository.getStudentsByAge(age);
    }

    public List<Student> findByAgeBetween(int min, int max) {
        logger.info("Was invoked method to find students by age range");
        return studentRepository.findByAgeBetween(min, max);
    }

    public Faculty getFacultyById(Long id) {
        logger.info("Was invoked method to find faculty by student's id");
        Student student = studentRepository.findStudentById(id)
                .orElseThrow(StudentNotFoundException::new);
        return student.getFaculty();
    }

    public Integer countStudents() {
        logger.info("Was invoked method to count students");
        return studentRepository.countStudents();
    }

    public Double averageAge() {
        logger.info("Was invoked method to count average age of students");
        return studentRepository.averageAge();
    }

    public List<Student> getLastStudents(int number) {
        logger.info("Was invoked method to find last students");
        return studentRepository.getLastStudents(number);
    }

    public List<String> findAllInAlphabeticalOrder(String s) {
        logger.info("Was invoked method to find all students in alphabetical order");
        List<String> result = studentRepository.findAll()
                .stream()
                .map(Student::getName)
                .sorted(Comparator.naturalOrder())
                .filter(String -> String.startsWith(s))
                .map(String::toUpperCase)
                .toList();
        return result;
    }

    public Double middleAge() {
        logger.info("Was invoked method to count middle age of all students");
        Double result = studentRepository.findAll()
                .stream()
                .mapToDouble(Student::getAge)
                .average()
                .orElse(Double.NaN);
        return result;
    }

    public Integer number() {
        long time = System.currentTimeMillis();
        logger.info("Was invoked method number()");
        int sum = Stream.iterate(1, a -> a<=1_000_000, a -> a + 1)
                .reduce(0, Integer::sum);
        System.out.println(System.currentTimeMillis() - time);
        return sum;
    }
}
