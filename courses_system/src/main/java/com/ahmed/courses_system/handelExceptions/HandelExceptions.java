package com.ahmed.courses_system.handelExceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class HandelExceptions {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handelValidationException(MethodArgumentNotValidException ex){
		Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream().collect(
				Collectors.toMap(
						FieldError::getField,
						fieldError -> fieldError.getDefaultMessage() == null ? "Invalid value" : fieldError.getDefaultMessage()
				)
		);
		return ResponseEntity.status(400).body(errors);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> handelIllegalArgumentException(IllegalArgumentException ex){
		return ResponseEntity.status(400).body(ex.getMessage());
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handelRuntimeException(RuntimeException ex){
		return ResponseEntity.status(400).body(ex.getMessage());
	}
}
