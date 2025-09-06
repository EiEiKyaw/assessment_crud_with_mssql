package org.akee.prj25.assessment.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse> handleAllExceptions(Exception ex, WebRequest request) {

		ApiResponse response = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(),
				System.currentTimeMillis(), request.getDescription(false));

		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {

		ApiResponse response = new ApiResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(),
				System.currentTimeMillis(), request.getDescription(false));

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<ApiResponse> handleInsufficientBalance(InsufficientBalanceException ex, WebRequest request) {

		ApiResponse response = new ApiResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
				System.currentTimeMillis(), request.getDescription(false));

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String, Object>> handleConstraintViolationException(ConstraintViolationException ex) {

		Map<String, Object> body = new HashMap<>();
		body.put("status", 400);
		body.put("message", "Validation failed");
		body.put("errors",
				ex.getConstraintViolations().stream().map(cv -> Map.of("message", cv.getMessage())).toList());
		body.put("timestamp", System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {

		Map<String, Object> body = new HashMap<>();
		body.put("status", 400);
		body.put("message", "Validation failed");
		body.put("errors", ex.getBindingResult().getFieldErrors().stream()
				.map(err -> Map.of("message", err.getDefaultMessage())).toList());

		body.put("timestamp", System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);

	}

}
