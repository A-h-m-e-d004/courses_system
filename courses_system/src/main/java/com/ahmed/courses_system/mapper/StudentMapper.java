package com.ahmed.courses_system.mapper;


import com.ahmed.courses_system.dtos.StudentDto;
import com.ahmed.courses_system.dtos.StudentResponseDto;
import com.ahmed.courses_system.model.Student;
import org.springframework.stereotype.Component;


@Component
public class StudentMapper {

	public Student toStudent(StudentDto studentDto){
		Student student = new Student();
		student.setName(studentDto.name());
		student.setEmail(studentDto.email());
		return student;
	}

	public StudentResponseDto toStudentResponseDto(Student student){
		return new StudentResponseDto(student.getName());
	}
}
