package com.projeto.professorallocationabner.models.mappers;

import org.springframework.stereotype.Component;

import com.projeto.professorallocationabner.models.dtos.AllocationDto;
import com.projeto.professorallocationabner.models.entities.Allocation;
import com.projeto.professorallocationabner.models.views.AllocationView;

@Component
public class AllocationMapper {
	
	public Allocation toAllocation(AllocationDto dto) {
		return Allocation.builder()
				.id(null)
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