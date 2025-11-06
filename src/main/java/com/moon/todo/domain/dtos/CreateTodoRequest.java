package com.moon.todo.domain.dtos;

import com.moon.todo.domain.Priority;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTodoRequest {
	@NotBlank(message = "Title must not be blank")
	private String title;
	private String description;
	private Priority priority;
}
