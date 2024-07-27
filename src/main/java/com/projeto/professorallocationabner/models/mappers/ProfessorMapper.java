package com.projeto.professorallocationabner.models.mappers;

import org.springframework.stereotype.Component;

import com.projeto.professorallocationabner.models.dtos.DepartmentView;
import com.projeto.professorallocationabner.models.dtos.ProfessorDTO;
import com.projeto.professorallocationabner.models.dtos.ProfessorView;
import com.projeto.professorallocationabner.models.entities.Department;
import com.projeto.professorallocationabner.models.entities.Professor;

@Component
public class ProfessorMapper {
	public Professor toProfessor(Long id, ProfessorDTO dto) {
		Department department = Department.builder().id(dto.department().id()).name(dto.department().name()).build();

		return Professor.builder().id(id).cpf(dto.cpf()).department(department).name(dto.name()).build();
	}

	public ProfessorView toProfessorView(Professor professor) {
		DepartmentView departmentView = new DepartmentView(professor.getId(), professor.getName());

		return new ProfessorView(professor.getId(), professor.getName(), professor.getCpf(),
				departmentView);
	}
}
