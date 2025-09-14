package com.ahmed.courses_system.controller;


import com.ahmed.courses_system.dtos.EnrollDto;
import com.ahmed.courses_system.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enroll")
public class RegistrationController {

	private final RegistrationService registrationService;

	public RegistrationController(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	@PostMapping
	public ResponseEntity<?> enrollStudentInCourse(@Valid @RequestBody EnrollDto enrollDto){
		registrationService.enrollStudentInCourse(enrollDto);
		return ResponseEntity.status(201).body("Student enrolled in course successfully");
	}

	@GetMapping("/{student_id}")
	public ResponseEntity<?> getEnrolledCoursesForStudent(@PathVariable("student_id") Integer studentId){
		return ResponseEntity.status(200).body(registrationService.getEnrolledCoursesForStudent(studentId));
	}

	@DeleteMapping
	public ResponseEntity<?> unEnrollStudentFromCourse(@Valid @RequestBody EnrollDto enrollDto){
		registrationService.unEnrollStudentFromCourse(enrollDto);
		return ResponseEntity.status(200).body("Student unenrolled from course successfully");
	}

}
