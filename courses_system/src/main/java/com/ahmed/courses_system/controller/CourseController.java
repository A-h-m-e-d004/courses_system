package com.ahmed.courses_system.controller;


import com.ahmed.courses_system.dtos.CourseDto;
import com.ahmed.courses_system.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {

	private final CourseService courseService;

	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addCourse(@Valid @RequestBody CourseDto courseDto){
		courseService.addCourse(courseDto);
		return ResponseEntity.status(201).body("Course added successfully");
	}

	@GetMapping
	public ResponseEntity<?> getAllCourses(){
		return ResponseEntity.status(200).body(courseService.getAllCourses());
	}

	@DeleteMapping("/{course_id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteCourse(@PathVariable("course_id") Integer id){
		courseService.deleteCourse(id);
		return ResponseEntity.status(200).body("Course deleted successfully");
	}

	@GetMapping("/{course_title}")
	public ResponseEntity<?> getCourseByTitle(@PathVariable("course_title") String title){
		return ResponseEntity.status(200).body(courseService.getCourseByTitle(title));
	}
}
