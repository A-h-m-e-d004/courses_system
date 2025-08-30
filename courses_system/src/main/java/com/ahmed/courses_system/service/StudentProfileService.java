package com.ahmed.courses_system.service;


import com.ahmed.courses_system.dtos.StudentProfileDto;
import com.ahmed.courses_system.mapper.StudentProfileMapper;
import com.ahmed.courses_system.model.Student;
import com.ahmed.courses_system.model.StudentProfile;
import com.ahmed.courses_system.repository.StudentProfileRepository;
import com.ahmed.courses_system.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentProfileService {

	private final StudentProfileRepository studentProfileRepository;

	private final StudentRepository studentRepository;

	private final StudentProfileMapper studentProfileMapper;

	public StudentProfileService(StudentProfileRepository studentProfileRepository, StudentRepository studentRepository, StudentProfileMapper studentProfileMapper) {
		this.studentProfileRepository = studentProfileRepository;
		this.studentRepository = studentRepository;
		this.studentProfileMapper = studentProfileMapper;
	}

	public void createProfile(Student student){
		StudentProfile studentProfile = new StudentProfile(student);
		studentProfileRepository.save(studentProfile);
	}

	public void addCourseToProfile(Student student, String courseTitle) {
		if (student.getStudentProfile() != null) {
			student.getStudentProfile().addCourse(courseTitle);
			studentProfileRepository.save(student.getStudentProfile());
		}
	}

	public StudentProfileDto getStudentProfile(Integer id) {
		var student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found"));
		return studentProfileMapper.toStudentProfileDto(student);
	}
}
