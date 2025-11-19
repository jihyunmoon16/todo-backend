package com.moon.todo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moon.todo.domain.dtos.LoginRequest;
import com.moon.todo.domain.dtos.LoginResponse;
import com.moon.todo.domain.dtos.SignupRequest;
import com.moon.todo.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
		UserDetails userDetails = authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
		String tokenValue = authService.generateToken(userDetails);
		LoginResponse loginResponse = LoginResponse.builder()
			.token(tokenValue)
			.expiresIn(86400)
			.build();

		return ResponseEntity.ok(loginResponse);

	}

	@PostMapping("/signup")
	public ResponseEntity<Void> signup(@RequestBody SignupRequest signupRequest) {
		authService.signup(signupRequest);
		return ResponseEntity.ok().build();
	}

}
