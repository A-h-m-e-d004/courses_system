package com.ahmed.courses_system.service;

import com.ahmed.courses_system.dtos.UpdateGradeDto;
import com.ahmed.courses_system.repository.EnrollmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

	private final EnrollmentRepository enrollmentRepository;

	public EnrollmentService(EnrollmentRepository enrollmentRepository) {
		this.enrollmentRepository = enrollmentRepository;
	}

	@Transactional
	public void updateGrade(UpdateGradeDto updateGradeDto){
		if(!enrollmentRepository.existsByStudentIdAndCourseId(updateGradeDto.studentId(), updateGradeDto.courseId())){
			throw new IllegalArgumentException("Student is not enrolled in this course");
		}
		if(updateGradeDto.grade() < 0 || updateGradeDto.grade() > 100){
			throw new IllegalArgumentException("Grade must be between 0 and 100");
		}
		enrollmentRepository.findByStudentIdAndCourseId(updateGradeDto.studentId(), updateGradeDto.courseId()).ifPresent(enrollment -> {
			enrollment.setGrade(updateGradeDto.grade());
			enrollmentRepository.save(enrollment);
		});
	}
}
