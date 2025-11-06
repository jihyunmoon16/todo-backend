package com.moon.todo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moon.todo.domain.dtos.TodoDto;
import com.moon.todo.mapper.TodoMapper;
import com.moon.todo.service.TodoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/todos")
@RequiredArgsConstructor
public class TodoController {

	private final TodoService todoService;
	private final TodoMapper todoMapper;

	@GetMapping()
	public ResponseEntity<List<TodoDto>> getTodos() {
		List<TodoDto> todos = todoService.getTodos().stream()
				.map(todoMapper::toDto)
				.toList();
		return ResponseEntity.ok(todos);
	}

}
