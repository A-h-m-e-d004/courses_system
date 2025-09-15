package com.ahmed.courses_system.mapper;


import com.ahmed.courses_system.dtos.CourseDto;
import com.ahmed.courses_system.dtos.CourseResponseDto;
import com.ahmed.courses_system.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

	public Course toCourse(CourseDto courseDto){
		return new Course(
				courseDto.title()
		);
	}

	public CourseDto toCourseDto(Course course){
		return new CourseDto(
				course.getTitle()
		);
	}

	public CourseResponseDto toCourseResponseDto(Course course){
		return new CourseResponseDto(course.getId(), course.getTitle());
	}
}
