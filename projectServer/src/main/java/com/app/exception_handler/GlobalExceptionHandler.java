package com.app.exception_handler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e) {
		System.out.println("in res not found exc ");
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(new ApiResponse(e.getMessage()));
	}
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleAnyException(RuntimeException e) {
		System.out.println("in catch all " + e);
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ApiResponse(e.getMessage()));
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		System.out.println("in catch all " + e);
		 List<FieldError> list = e.getFieldErrors();
		 Map<String, String> map = list.stream()
		 .collect(Collectors.toMap
				 (FieldError::getField, FieldError::getDefaultMessage));
		return map;
	}
	
}
