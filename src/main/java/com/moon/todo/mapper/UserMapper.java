package com.moon.todo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.moon.todo.domain.dtos.SignupRequest;
import com.moon.todo.domain.entities.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

	@Mapping(target = "password",
		expression = "java(passwordEncoder.encode(request.getPassword()))")
	User toEntity(SignupRequest request, PasswordEncoder passwordEncoder);
}
