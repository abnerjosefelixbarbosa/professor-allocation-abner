package com.projeto.professorallocationabner.models.dtos;

public record ProfessorDTO(
		Long id,
		String name,
		String cpf,
		DepartmentDTO department
) {}