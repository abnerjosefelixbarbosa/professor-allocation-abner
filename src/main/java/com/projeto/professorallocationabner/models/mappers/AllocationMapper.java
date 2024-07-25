package com.projeto.professorallocationabner.models.mappers;

import org.springframework.stereotype.Component;

import com.projeto.professorallocationabner.models.dtos.AllocationDTO;
import com.projeto.professorallocationabner.models.dtos.AllocationView;
import com.projeto.professorallocationabner.models.dtos.CourseView;
import com.projeto.professorallocationabner.models.dtos.DepartmentView;
import com.projeto.professorallocationabner.models.dtos.ProfessorView;
import com.projeto.professorallocationabner.models.entities.Allocation;
import com.projeto.professorallocationabner.models.entities.Course;
import com.projeto.professorallocationabner.models.entities.Department;
import com.projeto.professorallocationabner.models.entities.Professor;

@Component
public class AllocationMapper {

	public Allocation toAllocation(AllocationDTO dto) {
		Course course = Course.builder().id(dto.course().id()).name(dto.course().name()).build();

		Department department = Department.builder().id(dto.professor().department().id())
				.name(dto.professor().department().name()).build();

		Professor professor = Professor.builder().id(dto.professor().id()).name(dto.professor().name())
				.cpf(dto.professor().cpf()).department(department).build();

		Allocation allocation = Allocation.builder().dayOfWeek(dto.day()).startHour(dto.startHour()).endHour(dto.endHour())
				.course(course).professor(professor).build();

		return allocation;
	}

	public AllocationView toAllocationView(Allocation allocation) {
		CourseView courseView = new CourseView(allocation.getCourse().getId(), allocation.getCourse().getName());
		
		DepartmentView departmentView = new DepartmentView(allocation.getProfessor().getDepartment().getId(),
				allocation.getProfessor().getDepartment().getName());

		ProfessorView professorView = new ProfessorView(allocation.getProfessor().getId(),
				allocation.getProfessor().getName(), allocation.getProfessor().getCpf(), departmentView);

		AllocationView allocationView = new AllocationView(allocation.getId(), allocation.getDayOfWeek(),
				allocation.getStartHour(), allocation.getEndHour(), courseView, professorView);

		return allocationView;
	}
}