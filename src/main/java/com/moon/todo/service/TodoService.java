package com.moon.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moon.todo.domain.entities.Todo;
import com.moon.todo.domain.entities.User;
import com.moon.todo.repository.TodoRepository;
import com.moon.todo.repository.UserRepository;
import com.moon.todo.security.TodoUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {
	private final TodoRepository todoRepository;
	private final UserRepository userRepository;

	public List<Todo> getTodos(Long userId) {
		return todoRepository.findByUserId(userId);
	}

	public Todo createTodo(Todo todo, TodoUserDetails todoUserDetails) {

		User user = userRepository.findById(todoUserDetails.getId())
			.orElseThrow(() -> new IllegalArgumentException("User not found"));

		todo.setUser(user);

		return todoRepository.save(todo);
	}

	public void deleteTodo(Long id) {
		todoRepository.deleteById(id);
	}
}
