package com.moon.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moon.todo.domain.entities.Pomodoro;

public interface PomodoroRepository extends JpaRepository<Pomodoro, Long> {
	List<Pomodoro> findByTodoId(Long todoId);
}
