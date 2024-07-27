package com.projeto.professorallocationabner.models.mappers;

import org.springframework.stereotype.Component;

import com.projeto.professorallocationabner.models.dtos.DepartmentView;
import com.projeto.professorallocationabner.models.dtos.ProfessorDTO;
import com.projeto.professorallocationabner.models.dtos.ProfessorView;
import com.projeto.professorallocationabner.models.entities.Department;
import com.projeto.professorallocationabner.models.entities.Professor;

@Component
public class ProfessorMapper {
	public Professor toProfessor(ProfessorDTO dto) {
		Department department = Department.builder().name(dto.department().name()).build();

		Professor professor = Professor.builder().cpf(dto.cpf()).department(department).name(dto.name()).build();

		return professor;
	}

	public ProfessorView toProfessorView(Professor professor) {
		DepartmentView departmentView = new DepartmentView(professor.getId(), professor.getName());

		ProfessorView professorView = new ProfessorView(professor.getId(), professor.getName(), professor.getCpf(),
				departmentView);

		return professorView;
	}
}
