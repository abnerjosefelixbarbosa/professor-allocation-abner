package com.projeto.professorallocationabner.models.mappers;

import org.springframework.stereotype.Component;

import com.projeto.professorallocationabner.models.dtos.DepartmentDTO;
import com.projeto.professorallocationabner.models.dtos.DepartmentView;
import com.projeto.professorallocationabner.models.entities.Department;

@Component
public class DepartmentMapper {
	public Department toDepartment(DepartmentDTO dto) {
		return Department.builder()
				.name(dto.name())
				.build();
	}
	
	public DepartmentView toDepartmentView(Department department) {
		return new DepartmentView(
				department.getId(),
				department.getName()
		);
	}
}
