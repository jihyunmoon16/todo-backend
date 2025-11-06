package com.moon.todo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.moon.todo.domain.dtos.ApiErrorResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@ControllerAdvice
@Slf4j
public class ErrorController {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiErrorResponse> handleException(Exception e) {
		log.error("Unhandled exception occurred: ", e);
		ApiErrorResponse errorResponse = ApiErrorResponse.builder()
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.message("An unexpected error occurred.")
				.build();
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
		log.error("Illegal argument exception occurred: ", e);
		ApiErrorResponse errorResponse = ApiErrorResponse.builder()
				.status(HttpStatus.BAD_REQUEST.value())
				.message(e.getMessage())
				.build();
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}
