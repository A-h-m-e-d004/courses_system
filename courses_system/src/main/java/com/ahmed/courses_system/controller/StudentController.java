package com.ahmed.courses_system.controller;

import com.ahmed.courses_system.dtos.StudentDto;
import com.ahmed.courses_system.dtos.StudentResponseDto;
import com.ahmed.courses_system.service.StudentProfileService;
import com.ahmed.courses_system.service.StudentService;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

	private final StudentService studentService;

	private final StudentProfileService studentProfileService;

	public StudentController(StudentService studentService, StudentProfileService studentProfileService) {
		this.studentService = studentService;
		this.studentProfileService = studentProfileService;
	}

	@GetMapping
	public List<StudentResponseDto> getAllStudents(){
		return studentService.getAllStudents();
	}

	@DeleteMapping("/{student_id}")
	public ResponseEntity<?> deleteStudent(@PathVariable("student_id") Integer id){
		studentService.deleteStudent(id);
		return ResponseEntity.status(200).body("Student deleted successfully");
	}

	@GetMapping("/profile/{student_id}")
	public ResponseEntity<?> getStudentProfile(@PathVariable("student_id") Integer id){
		return ResponseEntity.status(200).body(studentProfileService.getStudentProfile(id));
	}
}
