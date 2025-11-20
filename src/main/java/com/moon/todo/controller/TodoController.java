package com.moon.todo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moon.todo.domain.dtos.CreateTodoRequest;
import com.moon.todo.domain.dtos.TodoDto;
import com.moon.todo.domain.dtos.UpdateTodoRequest;
import com.moon.todo.domain.dtos.UpdateTodoRequestDto;
import com.moon.todo.domain.entities.Todo;
import com.moon.todo.mapper.TodoMapper;
import com.moon.todo.security.TodoUserDetails;
import com.moon.todo.service.TodoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/todos")
@RequiredArgsConstructor
public class TodoController {

	private final TodoService todoService;
	private final TodoMapper todoMapper;

	@GetMapping()
	public ResponseEntity<List<TodoDto>> getTodos(@AuthenticationPrincipal TodoUserDetails user) {
		Long userId = user.getId();

		List<TodoDto> todos = todoService.getTodos(userId).stream()
				.map(todoMapper::toDto)
				.toList();
		return ResponseEntity.ok(todos);
	}

	@PostMapping()
	public ResponseEntity<TodoDto> createTodo(@Valid @RequestBody CreateTodoRequest createTodoRequest,
												@AuthenticationPrincipal TodoUserDetails user) {
		Todo todoToCreate = todoMapper.toEntity(createTodoRequest);
		Todo savedTodo = todoService.createTodo(todoToCreate, user);

		return new ResponseEntity<>(todoMapper.toDto(savedTodo), HttpStatus.CREATED);
	}

	@PatchMapping(path = "/{id}")
	public ResponseEntity<TodoDto> updateTodo(@PathVariable Long id,
		@AuthenticationPrincipal TodoUserDetails user,
		@RequestBody UpdateTodoRequestDto updateTodoRequestDto) {
		UpdateTodoRequest todoRequest = todoMapper.toUpdateTodoRequest(updateTodoRequestDto);
		Todo updatedTodo = todoService.updateTodo(id, todoRequest, user);
		TodoDto updatedTodoDto = todoMapper.toDto(updatedTodo);
		return ResponseEntity.ok(updatedTodoDto);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
		todoService.deleteTodo(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<TodoDto> getTodoById(@PathVariable Long todoId,
		@AuthenticationPrincipal TodoUserDetails user) {

		Todo todo = todoService.getTodo(todoId, user);

		return ResponseEntity.ok(todoMapper.toDto(todo));
	}

}
