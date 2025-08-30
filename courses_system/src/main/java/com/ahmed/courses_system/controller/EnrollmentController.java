package com.ahmed.courses_system.controller;


import com.ahmed.courses_system.dtos.UpdateGradeDto;
import com.ahmed.courses_system.service.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnrollmentController {

	private final EnrollmentService enrollmentService;

	public EnrollmentController(EnrollmentService enrollmentService) {
		this.enrollmentService = enrollmentService;
	}

	@PutMapping("/updateGrade")
	public ResponseEntity<Void> updateGrade(@RequestBody UpdateGradeDto updateGradeDto){
		enrollmentService.updateGrade(updateGradeDto);
		return ResponseEntity.ok().build();
	}

}
