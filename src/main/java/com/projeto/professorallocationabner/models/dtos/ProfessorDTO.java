package com.projeto.professorallocationabner.models.dtos;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ProfessorDTO(
		Long id,
		@NotNull(message = "name null")
		@NotEmpty(message = "name empty") 
		String name,
		@NotNull(message = "cpf null")
		@NotEmpty(message = "cpf empty")
		@CPF(message = "cpf invalid") String cpf,
		@NotNull(message = "department null")
		DepartmentDTO department
) {}