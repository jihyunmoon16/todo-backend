package com.moon.todo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.moon.todo.domain.entities.Todo;
import com.moon.todo.domain.entities.User;
import com.moon.todo.repository.TodoRepository;
import com.moon.todo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {
	private final TodoRepository todoRepository;
	private final UserRepository userRepository;

	public List<Todo> getTodos() {
		return todoRepository.findAll();
	}

	public Todo createTodo(Todo todo) {

		// TODO: needs to be deleted - dummy user data
		User user = userRepository.findByEmail("test@example.com")
			.orElseGet(() -> {
				User newUser = new User();
				newUser.setEmail("test@example.com");
				newUser.setPassword("1234");
				newUser.setNickname("TestUser");
				newUser.setCreatedAt(LocalDateTime.now());
				return userRepository.save(newUser);
			});

		todo.setUser(user);

		return todoRepository.save(todo);
	}

	public void deleteTodo(Long id) {
		todoRepository.deleteById(id);
	}
}
