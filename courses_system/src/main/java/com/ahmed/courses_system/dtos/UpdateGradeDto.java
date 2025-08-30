package com.ahmed.courses_system.dtos;

import jakarta.validation.constraints.NotNull;

public record UpdateGradeDto(

		@NotNull(message = "Student ID is required")
		Integer studentId,
		@NotNull(message = "Course ID is required")
		Integer courseId,
		@NotNull(message = "Grade is required")
		Integer grade
) {
}
