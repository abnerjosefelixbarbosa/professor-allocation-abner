package com.projeto.professorallocationabner.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DepartmentDTO(Long id,
		@NotNull(message = "name should not be null") @NotEmpty(message = "name should not be empty") String name) {
}