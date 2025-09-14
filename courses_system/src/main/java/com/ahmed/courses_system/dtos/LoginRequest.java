package com.ahmed.courses_system.dtos;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(

		@NotEmpty(message = "The username should not be empty")
		String username,

		@NotEmpty(message = "The password should not be empty")
		String password
) {
}
