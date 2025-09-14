package com.ahmed.courses_system.dtos;

import jakarta.validation.constraints.NotEmpty;

public record TeacherDto(

		@NotEmpty(message = "the name should not be empty")
		String name,

		@NotEmpty(message = "the email should not be empty")
		String email,

		@NotEmpty(message = "the password should not be empty")
		String password
) {
}
