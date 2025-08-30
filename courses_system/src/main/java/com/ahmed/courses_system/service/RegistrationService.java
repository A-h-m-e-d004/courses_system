package com.ahmed.courses_system.service;

import com.ahmed.courses_system.dtos.CourseDto;
import com.ahmed.courses_system.dtos.EnrollDto;
import com.ahmed.courses_system.model.Course;
import com.ahmed.courses_system.model.Enrollment;
import com.ahmed.courses_system.model.Student;
import com.ahmed.courses_system.repository.CourseRepository;
import com.ahmed.courses_system.repository.EnrollmentRepository;
import com.ahmed.courses_system.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {

	private final StudentRepository studentRepository;

	private final CourseRepository courseRepository;

	private final EnrollmentRepository enrollmentRepository;

	private final StudentProfileService studentProfileService;

	public RegistrationService(StudentRepository studentRepository, CourseRepository courseRepository, EnrollmentRepository enrollmentRepository, StudentProfileService studentProfileService) {
		this.studentRepository = studentRepository;
		this.courseRepository = courseRepository;
		this.enrollmentRepository = enrollmentRepository;
		this.studentProfileService = studentProfileService;
	}

	@Transactional
	public void enrollStudentInCourse(EnrollDto enrollDto){

		if (enrollmentRepository.existsByStudentIdAndCourseId(enrollDto.studentId(), enrollDto.courseId())){
			throw new IllegalArgumentException("Student is already enrolled in this course");
		}
		Student student = studentRepository.findById(enrollDto.studentId()).orElseThrow(() -> new IllegalArgumentException("Invalid Student ID"));
		Course course = courseRepository.findById(enrollDto.courseId()).orElseThrow(() -> new IllegalArgumentException("Invalid Course ID"));

		Enrollment enrollment = new Enrollment(student, course);
		enrollmentRepository.save(enrollment);
		studentProfileService.addCourseToProfile(student, course.getTitle());
	}

	@Transactional
	public void unEnrollStudentFromCourse(EnrollDto enrollDto){

		if (!enrollmentRepository.existsByStudentIdAndCourseId(enrollDto.studentId(), enrollDto.courseId())){
			throw new IllegalArgumentException("Student is not enrolled in this course");
		}
		enrollmentRepository.findByStudentIdAndCourseId(enrollDto.studentId(), enrollDto.courseId()).ifPresent(enrollmentRepository::delete);
	}

	public List<CourseDto> getEnrolledCoursesForStudent(int studentId){
		Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException("Invalid Student ID"));
		List<CourseDto> courses = student.getCourses().stream().map(course -> new CourseDto(course.getTitle())).toList();
		return courses;
	}
}
