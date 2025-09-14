package com.ahmed.courses_system.service;


import com.ahmed.courses_system.dtos.StudentDto;
import com.ahmed.courses_system.dtos.StudentResponseDto;
import com.ahmed.courses_system.mapper.StudentMapper;
import com.ahmed.courses_system.model.Student;
import com.ahmed.courses_system.repository.StudentRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

	private final StudentMapper studentMapper;

	private final StudentRepository studentRepository;

	private final StudentProfileService studentProfileService;

	private final PasswordEncoder passwordEncoder;

	public StudentService(StudentMapper studentMapper, StudentRepository studentRepository, StudentProfileService studentProfileService, PasswordEncoder passwordEncoder) {
		this.studentMapper = studentMapper;
		this.studentRepository = studentRepository;
		this.studentProfileService = studentProfileService;
		this.passwordEncoder = passwordEncoder;
	}

	public void addStudent(StudentDto studentDto){
		if(studentRepository.existsByEmail(studentDto.email())) {
			throw new IllegalArgumentException("Email already exists");
		}

		Student student = studentMapper.toStudent(studentDto);
		student.setPassword(passwordEncoder.encode(student.getPassword()));
		student = studentRepository.save(student);
		studentProfileService.createProfile(student);
	}

	public void deleteStudent(Integer id){
		studentRepository.deleteById(id);
	}

	public List<StudentResponseDto> getAllStudents(){
		return studentRepository.findAll().stream().map(studentMapper::toStudentResponseDto).toList();
	}
}
