package com.ahmed.courses_system.service;

import com.ahmed.courses_system.dtos.CourseDto;
import com.ahmed.courses_system.dtos.CourseResponseDto;
import com.ahmed.courses_system.mapper.CourseMapper;
import com.ahmed.courses_system.model.Course;
import com.ahmed.courses_system.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

	private final CourseRepository courseRepository;

	private final CourseMapper courseMapper;

	public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
		this.courseRepository = courseRepository;
		this.courseMapper = courseMapper;
	}

	public void addCourse(CourseDto courseDto){
		courseRepository.save(courseMapper.toCourse(courseDto));
	}

	public void deleteCourse(Integer id){
		courseRepository.deleteById(id);
	}

	public CourseDto getCourseByTitle(String title){
		Course course = courseRepository.findCourseByTitle(title);
		if (course == null){
			throw new IllegalArgumentException("Course not found");
		}
		return courseMapper.toCourseDto(course);
	}

	public List<CourseResponseDto> getAllCourses(){
		return courseRepository.findAll().stream().map(courseMapper::toCourseResponseDto).toList();
	}
}
