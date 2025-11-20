package com.moon.todo.domain.dtos;

import com.moon.todo.domain.Priority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTodoRequestDto {
	private String title;
	private String description;
	private Priority priority;
	private Boolean completed;
}
