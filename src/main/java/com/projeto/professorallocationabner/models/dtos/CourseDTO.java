package com.projeto.professorallocationabner.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CourseDTO(
		Long id,
		@NotNull(message = "name null")
		@NotEmpty(message = "name empty")
		String name
) {}