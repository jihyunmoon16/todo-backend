package com.moon.todo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.moon.todo.domain.dtos.TodoDto;
import com.moon.todo.domain.entities.Todo;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TodoMapper {
	TodoDto toDto(Todo todo);
}
