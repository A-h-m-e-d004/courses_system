package com.ahmed.courses_system.dtos;

import jakarta.validation.constraints.NotEmpty;

public record CourseDto(

		@NotEmpty
		String title
) {
}
