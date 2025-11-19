package com.moon.todo.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.moon.todo.domain.dtos.SignupRequest;
import com.moon.todo.domain.entities.User;
import com.moon.todo.exception.CustomBusinessException;
import com.moon.todo.exception.ErrorCode;
import com.moon.todo.mapper.UserMapper;
import com.moon.todo.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserMapper userMapper;

	@Value("${jwt.secret}")
	private String secretKey;

	private final Long jwtExpiryMs = 86400000L;

	public UserDetails authenticate(String email, String password) {
		try {
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(email, password)
			);
			return userDetailsService.loadUserByUsername(email);
		} catch (BadCredentialsException ex) {
			throw new CustomBusinessException(ErrorCode.INVALID_LOGIN_CREDENTIALS);
		} catch (AuthenticationException ex) {
			throw new CustomBusinessException(ErrorCode.LOGIN_FAILED);
		}
	}

	public String generateToken(UserDetails userDetails) {
		long now = System.currentTimeMillis();

		return Jwts.builder()
			.subject(userDetails.getUsername())
			.issuedAt(new Date(now))
			.expiration(new Date(now + jwtExpiryMs))
			.signWith(getSigningKey())
			.compact();
	}

	public UserDetails validateToken(String token) {
		String username = extractUsername(token);
		return userDetailsService.loadUserByUsername(username);
	}

	private String extractUsername(String token) {
		Claims claims =  Jwts.parser()
			.verifyWith(getSigningKey())
			.build()
			.parseSignedClaims(token)
			.getPayload();

		return claims.getSubject();
	}

	private SecretKey getSigningKey() {
		byte[] keyBytes = secretKey.getBytes();
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public void signup(SignupRequest signupRequest) {
		userRepository.findByEmail(signupRequest.getEmail())
			.ifPresent(user -> {
				throw new CustomBusinessException(ErrorCode.DUPLICATE_EMAIL);
			});

		User newUser = userMapper.toEntity(signupRequest, passwordEncoder);


		userRepository.save(newUser);
	}
}
