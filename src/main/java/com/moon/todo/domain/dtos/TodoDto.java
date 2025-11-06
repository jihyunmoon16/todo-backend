package com.moon.todo.domain.dtos;

import com.moon.todo.domain.Priority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {
	private Long id;
	private String title;
	private String description;
	private Priority priority;
	private boolean completed;
}
