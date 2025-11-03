package com.moon.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moon.todo.domain.PomodoroSession;

public interface PomodoroSessionRepository extends JpaRepository<PomodoroSession, Long> {
	List<PomodoroSession> findByTodoId(Long todoId);
}
