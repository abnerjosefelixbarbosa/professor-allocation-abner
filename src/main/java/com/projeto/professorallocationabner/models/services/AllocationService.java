package com.projeto.professorallocationabner.models.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projeto.professorallocationabner.models.dtos.AllocationDTO;
import com.projeto.professorallocationabner.models.dtos.AllocationView;
import com.projeto.professorallocationabner.models.entities.Allocation;
import com.projeto.professorallocationabner.models.entities.Course;
import com.projeto.professorallocationabner.models.entities.Professor;
import com.projeto.professorallocationabner.models.mappers.AllocationMapper;
import com.projeto.professorallocationabner.models.repositories.AllocationRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AllocationService {
	private final AllocationRepository allocationRepository;
	private final ProfessorService professorService;
	private final CourseService courseService;
	private final AllocationMapper allocationMapper;

	public Page<AllocationView> findAll(Pageable pageable) {
		return allocationRepository.findAll(pageable).map(allocationMapper::toAllocationView);
	}

	public AllocationView findById(Long id) {
		return allocationRepository.findById(id).map(allocationMapper::toAllocationView)
				.orElseThrow(() -> new EntityNotFoundException("allocation not found"));
	}

	public Page<AllocationView> findByProfessor(Long id, Pageable pageable) {
		return allocationRepository.findByProfessorId(id, pageable).map(allocationMapper::toAllocationView);
	}

	public Page<AllocationView> findByCourse(Long id, Pageable pageable) {
		return allocationRepository.findByCourseId(id, pageable).map(allocationMapper::toAllocationView);
	}

	public AllocationView save(AllocationDTO dto) {
		Allocation allocation = allocationMapper.toAllocation(null, dto);
		allocation = saveInternal(allocation);
		return allocationMapper.toAllocationView(allocation);
	}

	public AllocationView update(Long id, AllocationDTO dto) {
		return allocationRepository.findById(id).map((val) -> {
			Allocation allocation = allocationMapper.toAllocation(id, dto);
			allocation = saveInternal(allocation);
			return allocationMapper.toAllocationView(allocation);
		}).orElseThrow(() -> new EntityNotFoundException("allocation not found"));
	}

	public void deleteById(Long id) {
		Allocation allocation = allocationRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("allocation not found"));
		allocationRepository.delete(allocation);
	}

	public void deleteAll() {
		allocationRepository.deleteAllInBatch();
	}

	private Allocation saveInternal(Allocation allocation) {
		if (!isEndHourGreaterThanStartHour(allocation) || hasCollision(allocation)) {
			throw new RuntimeException("allocation invalid");
		} else {
			allocation = allocationRepository.save(allocation);

			Professor professor = professorService.findByProfessorId(allocation.getProfessor().getId());
			allocation.setProfessor(professor);

			Course course = courseService.findCourseById(allocation.getCourse().getId());
			allocation.setCourse(course);

			return allocation;
		}
	}

	private boolean hasCollision(Allocation allocation) {
		boolean hasCollision = false;
		Pageable pageable = PageRequest.ofSize(20);

		Page<Allocation> allocations = allocationRepository.findByProfessorId(allocation.getProfessor().getId(),
				pageable);

		for (Allocation val : allocations) {
			hasCollision = hasCollision(val, allocation);
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
				&& currentAllocation.getDayWeek() == newAllocation.getDayWeek()
				&& currentAllocation.getStartHour().compareTo(newAllocation.getEndHour()) < 0
				&& newAllocation.getStartHour().compareTo(currentAllocation.getEndHour()) < 0;
	}
}
