package com.moon.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moon.todo.domain.Priority;
import com.moon.todo.domain.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

	// User 단위로 Todo 조회
	List<Todo> findByUserId(Long userId);

	// 특정 상태, 중요도별 조회
	List<Todo> findByUserIdAndPriority(Long userId, Priority priority);
	List<Todo> findByUserIdAndCompleted(Long userId, boolean completed);
}
