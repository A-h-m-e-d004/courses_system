package com.ahmed.courses_system.controller;

import com.ahmed.courses_system.dtos.TeacherResponseDto;
import com.ahmed.courses_system.model.Teacher;
import com.ahmed.courses_system.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

	private final TeacherService teacherService;

	public TeacherController(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	@GetMapping
	public List<TeacherResponseDto> getAllTeacher(){
		return teacherService.getAllTeacher();
	}
}
