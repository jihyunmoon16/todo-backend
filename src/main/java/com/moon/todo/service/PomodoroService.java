package com.moon.todo.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moon.todo.domain.entities.Pomodoro;
import com.moon.todo.domain.entities.Todo;
import com.moon.todo.repository.PomodoroRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PomodoroService {
	private final TodoService todoService;
	private final PomodoroRepository pomodoroRepository;

	@Transactional
	public Pomodoro createPomodoro(Long id, Pomodoro pomodoroToCreate) {
		Pomodoro newPomodoro = new Pomodoro();
		newPomodoro.setDuration(pomodoroToCreate.getDuration());
		newPomodoro.setCompletedAt(LocalDateTime.now());

		Todo todo = todoService.getTodo(id);
		newPomodoro.setTodo(todo);

		return pomodoroRepository.save(newPomodoro);
	}
}

