package com.ahmed.courses_system.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record StudentDto(

		@NotEmpty(message = "the name should not be empty")
		String name,

		@NotEmpty(message = "the email should not be empty")
		String email
) {
}
