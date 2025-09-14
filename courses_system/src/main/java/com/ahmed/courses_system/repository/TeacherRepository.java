package com.ahmed.courses_system.repository;

import com.ahmed.courses_system.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
	boolean existsByEmail(String email);
	Optional<Teacher> findByName(String name);
}
