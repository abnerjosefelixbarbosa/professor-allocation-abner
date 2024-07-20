package com.projeto.professorallocationabner.models.mappers;

import org.springframework.stereotype.Component;

import com.projeto.professorallocationabner.models.dtos.AllocationDTO;
import com.projeto.professorallocationabner.models.dtos.AllocationView;
import com.projeto.professorallocationabner.models.entities.Allocation;

@Component
public class AllocationMapper {
	
	public Allocation toAllocation(AllocationDTO dto) {
		return Allocation.builder()
				.day(dto.day())
				.startHour(dto.startHour())
				.endHour(dto.endHour())
				.courseId(dto.courseId())
				.professorId(dto.professorId())
				.build();
	}
	
	public AllocationView toAllocationView(Allocation allocation) {
		return new AllocationView(
				allocation.getId(),
				allocation.getDay(),
				allocation.getStartHour(),
				allocation.getEndHour(),
				allocation.getCourseId(),
				allocation.getProfessorId()
		);
	}
}