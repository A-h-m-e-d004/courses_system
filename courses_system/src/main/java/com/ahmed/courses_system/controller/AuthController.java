package com.ahmed.courses_system.controller;

import com.ahmed.courses_system.config.JwtUtil;
import com.ahmed.courses_system.dtos.JwtResponse;
import com.ahmed.courses_system.dtos.LoginRequest;
import com.ahmed.courses_system.dtos.StudentDto;
import com.ahmed.courses_system.dtos.TeacherDto;
import com.ahmed.courses_system.repository.StudentRepository;
import com.ahmed.courses_system.repository.TeacherRepository;
import com.ahmed.courses_system.service.StudentService;
import com.ahmed.courses_system.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final JwtUtil jwtUtil;

	private final StudentRepository studentRepository;

	private final TeacherRepository teacherRepository;

	private final PasswordEncoder passwordEncoder;

	private final StudentService studentService;

	private final TeacherService teacherService;

	public AuthController(JwtUtil jwtUtil, StudentRepository studentRepository, TeacherRepository teacherRepository, PasswordEncoder passwordEncoder, StudentService studentService, TeacherService teacherService) {
		this.jwtUtil = jwtUtil;
		this.studentRepository = studentRepository;
		this.teacherRepository = teacherRepository;
		this.passwordEncoder = passwordEncoder;
		this.studentService = studentService;
		this.teacherService = teacherService;
	}

	@PostMapping("/login/student")
	public ResponseEntity<?> studentLogin(@Valid @RequestBody LoginRequest loginRequest){
		var student = studentRepository.findByName(loginRequest.username());
		if (student.isEmpty()){
			return ResponseEntity.status(401).body("user not found");
		}
		if (passwordEncoder.matches(loginRequest.password(), student.get().getPassword())){
			String token = jwtUtil.generateToken(loginRequest.username(), "STUDENT");
			return ResponseEntity.status(200).body(new JwtResponse(token));
		}
		else {
			return ResponseEntity.badRequest().body("wrong password or username");
		}
	}

	@PostMapping("/login/teacher")
	public ResponseEntity<?> teacherLogin(@Valid @RequestBody LoginRequest loginRequest){
		var teacher = teacherRepository.findByName(loginRequest.username());
		if (teacher.isEmpty()){
			return ResponseEntity.status(401).body("user not found");
		}
		if (passwordEncoder.matches(loginRequest.password(), teacher.get().getPassword())){
			String token = jwtUtil.generateToken(loginRequest.username(), "TEACHER");
			return ResponseEntity.status(200).body(new JwtResponse(token));
		}
		else {
			return ResponseEntity.badRequest().body("wrong password or username");
		}
	}

	@PostMapping("/signup/student")
	public ResponseEntity<?> studentSignup(@Valid @RequestBody StudentDto studentDto){
		studentService.addStudent(studentDto);
		return ResponseEntity.status(201).body("Student added successfully");
	}

	@PostMapping("/signup/teacher")
	public ResponseEntity<?> teacherSignup(@Valid @RequestBody TeacherDto teacherDto){
		teacherService.addTeacher(teacherDto);
		return ResponseEntity.status(201).body("Teacher added successfully");
	}
}
