package com.projeto.professorallocationabner.models.dtos;

public record ProfessorView(
		Long id,
		String name,
		String cpf,
		Long departmentId
) {}