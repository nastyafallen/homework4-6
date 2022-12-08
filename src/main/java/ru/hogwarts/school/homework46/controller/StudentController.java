package ru.hogwarts.school.homework46.controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.homework46.service.StudentService;
import ru.hogwarts.school.homework46.model.Faculty;
import ru.hogwarts.school.homework46.model.Student;

import java.util.List;

@RequestMapping("/student")
@RestController
@Validated
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.createStudent(student));
    }

    @GetMapping("/find")
    public ResponseEntity<Student> getStudent(@RequestParam("id") Long id) {
        return ResponseEntity.ok(studentService.getStudent(id));
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        return  ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(studentService.updateStudent(student));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Student>> getStudentsByAge(@RequestParam("age") int age) {
        if (age < 0) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(studentService.getStudentsByAge(age));
    }

    @GetMapping
    public ResponseEntity<List<Student>> findByAgeBetween(@RequestParam ("age1")int min, @RequestParam ("age2")int max) {
        if (min < 0 || max < 0) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(studentService.findByAgeBetween(min, max));
    }

    @GetMapping("/{id}/faculty")
    public Faculty getFacultyById(@PathVariable Long id) {
        return studentService.getFacultyById(id);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countStudents() {
        return ResponseEntity.ok(studentService.countStudents());
    }

    @GetMapping("/averageAge")
    public ResponseEntity<Double> averageAge() {
        return ResponseEntity.ok(studentService.averageAge());
    }

    @GetMapping("/lastStudents")
    public ResponseEntity<List<Student>> getLastStudents(@RequestParam("number") @Min(1) @Max(10) int number) {
        return ResponseEntity.ok(studentService.getLastStudents(number));
    }

    @GetMapping("/alphabeticalOrder")
    public ResponseEntity<List<String>> findAllInAlphabeticalOrder(@RequestParam("letter") String s) {
        return ResponseEntity.ok(studentService.findAllInAlphabeticalOrder(s));
    }

    @GetMapping("/middleAge")
    public ResponseEntity<String> middleAge() {
        return ResponseEntity.ok("Средний возраст всех студентов составил "
                + studentService.middleAge()
                + " лет");
    }

    @GetMapping("/info")
    public ResponseEntity<Integer> number() {
        return ResponseEntity.ok(studentService.number());
    }

    @GetMapping("/listOfStudents")
    public ResponseEntity<List<String>> findAllStudents() {
        return ResponseEntity.ok(studentService.findAllStudents());
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> handleStudentNotExistsException(EmptyResultDataAccessException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Студент с таким id не найден!");
    }
}
