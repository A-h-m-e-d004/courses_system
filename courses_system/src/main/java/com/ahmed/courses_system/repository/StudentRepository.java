package com.ahmed.courses_system.repository;

import com.ahmed.courses_system.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	boolean existsByEmail(String email);
}
