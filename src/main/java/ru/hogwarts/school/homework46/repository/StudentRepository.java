package ru.hogwarts.school.homework46.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.homework46.model.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> getStudentsByAge(int age);

    List<Student> findByAgeBetween(int min, int max);

    Optional<Student> findStudentById(Long id);

    @Query(value = "SELECT COUNT (*) FROM student", nativeQuery = true)
    Integer countStudents();

    @Query(value = "SELECT AVG (age) as average_age FROM student", nativeQuery = true)
    Double averageAge();

    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT :number", nativeQuery = true)
    List<Student> getLastStudents(@Param("number") int number);
}
