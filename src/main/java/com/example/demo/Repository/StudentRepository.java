package com.example.demo.Repository;

import com.example.demo.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByName(String name);

    List<Student> findByAge(int age);
}
