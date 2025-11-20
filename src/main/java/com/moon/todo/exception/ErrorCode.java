package com.moon.todo.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {
	// AUTH
	DUPLICATE_EMAIL(HttpStatus.CONFLICT, "A1001", "Email already exists."),
	INVALID_LOGIN_CREDENTIALS(HttpStatus.BAD_REQUEST, "A1002", "Invalid email or password."),
	LOGIN_FAILED(HttpStatus.UNAUTHORIZED, "A1003", "Login failed."),
	UNAUTHORIZED_ACCESS(HttpStatus.UNAUTHORIZED, "A1004", "Unauthorized access."),

	// GENERAL
	INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST,"G1001", "Input is not valid."),

	// TODO
	TODO_NOT_FOUND(HttpStatus.NOT_FOUND, "T1001", "Todo not found"),
;

	private final String message;
	private final String code;
	private final HttpStatus status;

	ErrorCode(final HttpStatus status, final String code, final String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}

}
