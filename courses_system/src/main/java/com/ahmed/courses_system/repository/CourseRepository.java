package com.ahmed.courses_system.repository;

import com.ahmed.courses_system.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
	Course findCourseByTitle(String title);
}
