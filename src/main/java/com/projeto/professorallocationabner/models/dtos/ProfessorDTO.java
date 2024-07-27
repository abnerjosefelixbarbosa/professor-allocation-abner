package com.projeto.professorallocationabner.models.dtos;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ProfessorDTO(Long id,
		@NotNull(message = "name should not be null") @NotEmpty(message = "name should not be empty") String name,
		@NotNull(message = "cpf should not be null") @NotEmpty(message = "cpf should not be empty") @CPF(message = "cpf should not be invalid") String cpf,
		@NotNull(message = "department should not be null") DepartmentDTO department) {
}