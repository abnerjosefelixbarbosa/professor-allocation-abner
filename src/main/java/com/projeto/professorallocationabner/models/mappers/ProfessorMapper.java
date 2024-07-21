package com.projeto.professorallocationabner.models.mappers;

import org.springframework.stereotype.Component;

import com.projeto.professorallocationabner.models.dtos.ProfessorDTO;
import com.projeto.professorallocationabner.models.dtos.ProfessorView;
import com.projeto.professorallocationabner.models.entities.Professor;

@Component
public class ProfessorMapper {
	public Professor toProfessor(ProfessorDTO dto) {
		return Professor.builder()
				.cpf(dto.cpf())
				.departmentId(dto.departmentId())
				.name(dto.name())
				.build();
	}
	
	public ProfessorView toProfessorView(Professor professor) {
		return new ProfessorView(
				professor.getId(),
				professor.getName(),
				professor.getCpf(),
				professor.getDepartmentId()
		);
	}
}
