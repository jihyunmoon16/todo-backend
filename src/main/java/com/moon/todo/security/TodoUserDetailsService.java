package com.moon.todo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.moon.todo.domain.entities.User;
import com.moon.todo.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class TodoUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email)
			.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

		return new TodoUserDetails(user);
	}
}
