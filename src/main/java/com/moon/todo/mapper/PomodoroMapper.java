package com.moon.todo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.moon.todo.domain.dtos.CreatePomodoroRequest;
import com.moon.todo.domain.dtos.PomodoroDto;
import com.moon.todo.domain.entities.Pomodoro;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PomodoroMapper {
	PomodoroDto toDto(Pomodoro pomodoro);

	Pomodoro toEntity(CreatePomodoroRequest createPomodoroRequest);
}
