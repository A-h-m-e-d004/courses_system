package com.ahmed.courses_system.mapper;

import com.ahmed.courses_system.dtos.StudentProfileDto;
import com.ahmed.courses_system.model.Student;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentProfileMapper {

	public StudentProfileDto toStudentProfileDto(Student student){
		return new StudentProfileDto(student.getName(), student.getStudentProfile().getCourses());
	}
}
