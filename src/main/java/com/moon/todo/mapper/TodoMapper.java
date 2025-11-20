package com.moon.todo.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.moon.todo.domain.dtos.CreateTodoRequest;
import com.moon.todo.domain.dtos.TodoDto;
import com.moon.todo.domain.dtos.UpdateTodoRequest;
import com.moon.todo.domain.dtos.UpdateTodoRequestDto;
import com.moon.todo.domain.entities.Todo;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TodoMapper {
	TodoDto toDto(Todo todo);

	Todo toEntity(CreateTodoRequest createTodoRequest);

	UpdateTodoRequest toUpdateTodoRequest(UpdateTodoRequestDto updateTodoRequestDto);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateFromRequest(UpdateTodoRequest updateTodoRequest, @MappingTarget Todo todo);

}
