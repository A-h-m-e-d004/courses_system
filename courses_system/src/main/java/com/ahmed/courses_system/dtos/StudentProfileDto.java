package com.ahmed.courses_system.dtos;

import java.util.List;

public record StudentProfileDto(
		String name,
		List<String> courses
) {
}
