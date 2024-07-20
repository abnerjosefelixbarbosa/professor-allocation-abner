package com.projeto.professorallocationabner.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projeto.professorallocationabner.models.dtos.AllocationDto;
import com.projeto.professorallocationabner.models.entities.Allocation;
import com.projeto.professorallocationabner.models.entities.Course;
import com.projeto.professorallocationabner.models.entities.Professor;
import com.projeto.professorallocationabner.models.exceptions.NotFound;
import com.projeto.professorallocationabner.models.mappers.AllocationMapper;
import com.projeto.professorallocationabner.models.repositories.AllocationRepository;
import com.projeto.professorallocationabner.models.views.AllocationView;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AllocationService {
	private final AllocationRepository allocationRepository;
	private final ProfessorService professorService;
	private final CourseService courseService;
	private final AllocationMapper allocationMapper;

	public Page<AllocationView> findAll(Pageable pageable) {
		return allocationRepository
				.findAll(pageable)
				.map(allocationMapper::toAllocationView);
	}

	public AllocationView findById(Long id) {
		return allocationRepository
				.findById(id)
				.map(allocationMapper::toAllocationView)
				.orElseThrow(() -> new NotFound("id not found"));
	}

	public Page<AllocationView> findByProfessor(Long professorId, Pageable pageable) {
		return allocationRepository
				.findByProfessorId(professorId, pageable)
				.map(allocationMapper::toAllocationView);
	}

	public Page<AllocationView> findByCourse(Long courseId, Pageable pageable) {
		return allocationRepository
				.findByCourseId(courseId, pageable)
				.map(allocationMapper::toAllocationView);
	}

	public AllocationView save(AllocationDto dto) {
		Allocation allocation = saveInternal(allocationMapper.toAllocation(dto));
		return allocationMapper
				.toAllocationView(allocation);
	}

	public AllocationView update(AllocationDto dto) {
		return allocationRepository.findById(dto.id()).map((val) -> {
			Allocation allocation = saveInternal(allocationMapper.toAllocation(dto));
			return allocationMapper
					.toAllocationView(allocation);
		})
		.orElseThrow(() -> new NotFound("id not found"));
	}

	private Allocation saveInternal(Allocation allocation) {
		if (!isEndHourGreaterThanStartHour(allocation) || hasCollision(allocation)) {
			throw new RuntimeException("allocation invalid");
		} else {
			allocation = allocationRepository.save(allocation);

			Professor professor = professorService.findById(allocation.getProfessorId());
			allocation.setProfessor(professor);

			Course course = courseService.findById(allocation.getCourseId());
			allocation.setCourse(course);

			return allocation;
		}
	}

	public void deleteById(Long id) {
		if (allocationRepository.existsById(id)) {
			allocationRepository.deleteById(id);
		}
	}

	public void deleteAll() {
		allocationRepository.deleteAllInBatch();
	}

	private boolean hasCollision(Allocation newAllocation) {
		boolean hasCollision = false;

		List<Allocation> currentAllocations = allocationRepository.findByProfessorId(newAllocation.getProfessorId());

		for (Allocation currentAllocation : currentAllocations) {
			hasCollision = hasCollision(currentAllocation, newAllocation);
			if (hasCollision) {
				break;
			}
		}

		return hasCollision;
	}

	private boolean isEndHourGreaterThanStartHour(Allocation allocation) {
		return allocation != null && allocation.getStartHour() != null && allocation.getEndHour() != null
				&& allocation.getEndHour().compareTo(allocation.getStartHour()) > 0;
	}

	private boolean hasCollision(Allocation currentAllocation, Allocation newAllocation) {
		return !currentAllocation.getId().equals(newAllocation.getId())
				&& currentAllocation.getDay() == newAllocation.getDay()
				&& currentAllocation.getStartHour().compareTo(newAllocation.getEndHour()) < 0
				&& newAllocation.getStartHour().compareTo(currentAllocation.getEndHour()) < 0;
	}
}
