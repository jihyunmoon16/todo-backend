package com.moon.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.moon.todo.domain.entities.Pomodoro;

public interface PomodoroRepository extends JpaRepository<Pomodoro, Long> {
	List<Pomodoro> findByTodoId(Long todoId);

	@Query("SELECT COALESCE(SUM(p.duration), 0) FROM Pomodoro p WHERE p.todo.id = :todoId")
	int getTotalDurationByTodoId(@Param("todoId") Long todoId);
}
