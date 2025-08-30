package com.ahmed.courses_system.dtos;

import jakarta.validation.constraints.NotNull;

public record EnrollDto(

		@NotNull(message = "the student id should not be empty")
		Integer studentId,

		@NotNull(message = "the course id should not be empty")
		Integer courseId
) {
}
