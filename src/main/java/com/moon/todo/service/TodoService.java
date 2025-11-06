package com.moon.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moon.todo.domain.entities.Todo;
import com.moon.todo.repository.TodoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {
	private final TodoRepository todoRepository;

	public List<Todo> getTodos() {
		return todoRepository.findAll();
	}
}
