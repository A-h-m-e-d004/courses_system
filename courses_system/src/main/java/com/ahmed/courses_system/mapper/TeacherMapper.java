package com.ahmed.courses_system.mapper;

import com.ahmed.courses_system.dtos.TeacherDto;
import com.ahmed.courses_system.model.Teacher;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper {

	public Teacher toTeacher(TeacherDto teacherDto){
		if (teacherDto == null){
			throw new IllegalArgumentException("The teacher DTO should not be null");
		}
		Teacher teacher = new Teacher();
		teacher.setName(teacherDto.name());
		teacher.setEmail(teacherDto.email());
		teacher.setPassword(teacherDto.password());
		return teacher;
	}
}
