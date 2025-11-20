package com.moon.todo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moon.todo.domain.dtos.CreatePomodoroRequest;
import com.moon.todo.domain.dtos.PomodoroDto;
import com.moon.todo.domain.entities.Pomodoro;
import com.moon.todo.mapper.PomodoroMapper;
import com.moon.todo.service.PomodoroService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/todos/{id}/pomodoros")
@RequiredArgsConstructor
public class PomodoroController {
	private final PomodoroMapper pomodoroMapper;
	private final PomodoroService pomodoroService;

	@PostMapping()
	public ResponseEntity<PomodoroDto> createPomodoro(@PathVariable Long id,
		@RequestBody CreatePomodoroRequest createPomodoroRequest) {

		Pomodoro pomodoroToCreate = pomodoroMapper.toEntity(createPomodoroRequest);
		Pomodoro savedPomodoro = pomodoroService.createPomodoro(id, pomodoroToCreate);
		System.out.println("Saved Pomodoro: " + savedPomodoro);
		return new ResponseEntity<>(pomodoroMapper.toDto(savedPomodoro), HttpStatus.CREATED);
	}

}
