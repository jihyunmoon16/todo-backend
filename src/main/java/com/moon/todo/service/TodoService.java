package com.moon.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moon.todo.domain.dtos.UpdateTodoRequest;
import com.moon.todo.domain.entities.Todo;
import com.moon.todo.domain.entities.User;
import com.moon.todo.exception.CustomBusinessException;
import com.moon.todo.exception.ErrorCode;
import com.moon.todo.mapper.TodoMapper;
import com.moon.todo.repository.TodoRepository;
import com.moon.todo.repository.UserRepository;
import com.moon.todo.security.TodoUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {
	private final TodoRepository todoRepository;
	private final UserRepository userRepository;
	private final TodoMapper todoMapper;

	public List<Todo> getTodos(Long userId) {
		return todoRepository.findByUserId(userId);
	}

	@Transactional
	public Todo createTodo(Todo todo, TodoUserDetails todoUserDetails) {

		User user = userRepository.findById(todoUserDetails.getId())
			.orElseThrow(() -> new IllegalArgumentException("User not found"));

		todo.setUser(user);

		return todoRepository.save(todo);
	}

	@Transactional
	public void deleteTodo(Long id) {
		todoRepository.deleteById(id);
	}

	@Transactional
	public Todo updateTodo(Long id, UpdateTodoRequest updateTodoRequest, TodoUserDetails user) {
		Todo todo = todoRepository.findById(id)
			.orElseThrow(() -> new CustomBusinessException(ErrorCode.TODO_NOT_FOUND));

		// 권한 체크
		if (!todo.getUser().getId().equals(user.getId())) {
			throw new CustomBusinessException(ErrorCode.UNAUTHORIZED_ACCESS);
		}

		todoMapper.updateFromRequest(updateTodoRequest, todo);
		return todo;
	}
}
