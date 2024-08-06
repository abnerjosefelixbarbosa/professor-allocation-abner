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

@Service
public class AllocationService {
	private final AllocationRepository allocationRepository;
	private final ProfessorService professorService;
	private final CourseService courseService;
	private final AllocationMapper allocationMapper;

	public AllocationService(AllocationRepository allocationRepository, ProfessorService professorService, CourseService courseService, AllocationMapper allocationMapper) {
		this.allocationRepository = allocationRepository;
		this.professorService = professorService;
		this.courseService = courseService;
		this.allocationMapper = allocationMapper;
	}

	public Page<AllocationView> findAllAllocations(Pageable pageable) {
		return allocationRepository.findAll(pageable).map(allocationMapper::toAllocationView);
	}

	public AllocationView findAllocationById(Long id) {
		return allocationRepository.findById(id).map(allocationMapper::toAllocationView).orElseThrow(() -> new EntityNotFoundException("allocation not found"));
	}

	public AllocationView saveAllocation(AllocationDTO dto) {
		Allocation allocation = allocationMapper.toAllocation(dto);
		validadeAllocation(allocation);

		Professor professor = professorService.getProfessorByName(allocation.getProfessor().getName());
		allocation.setProfessor(professor);
		Course course = courseService.getCourseByName(allocation.getCourse().getName());
		allocation.setCourse(course);
		allocation = allocationRepository.save(allocation);
		
		return allocationMapper.toAllocationView(allocation);
	}

	public AllocationView updateAllocation(Long id, AllocationDTO dto) {
		validadeAllocation(allocationMapper.toAllocation(dto));
		
		Allocation allocation = allocationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("allocation not found"));
		allocation.setDayWeek(dto.day());
		allocation.setEndHour(dto.endHour());
		allocation.setStartHour(dto.startHour());
		Professor professor = professorService.getProfessorByName(allocation.getProfessor().getName());
		allocation.setProfessor(professor);
		Course course = courseService.getCourseByName(allocation.getCourse().getName());
		allocation.setCourse(course);
		allocation = allocationRepository.save(allocation);
		
		return allocationMapper.toAllocationView(allocation);
	}

	public void deleteAllocationById(Long id) {
		Allocation allocation = allocationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("allocation not found"));
		allocationRepository.delete(allocation);
	}

	public void deleteAllAllocations() {
		allocationRepository.deleteAllInBatch();
	}
	
	private void validadeAllocation(Allocation allocation) {
		if (!isEndHourGreaterThanStartHour(allocation) || hasCollision(allocation)) 
			throw new RuntimeException("allocation invalid");
	}

	private boolean hasCollision(Allocation allocation) {
		boolean hasCollision = false;
		Pageable pageable = PageRequest.ofSize(20);
		Page<Allocation> allocations = allocationRepository.findByProfessorId(allocation.getProfessor().getId(), pageable);

		for (Allocation val : allocations) {
			hasCollision = hasCollision(val, allocation);
			if (hasCollision)
				break;
		}

		return hasCollision;
	}

	private boolean isEndHourGreaterThanStartHour(Allocation allocation) {
		return allocation != null 
				&& allocation.getStartHour() != null 
				&& allocation.getEndHour() != null 
				&& allocation.getEndHour().compareTo(allocation.getStartHour()) > 0;
	}

	private boolean hasCollision(Allocation currentAllocation, Allocation newAllocation) {
		return !currentAllocation.getId().equals(newAllocation.getId()) 
				&& currentAllocation.getDayWeek() == newAllocation.getDayWeek()
				&& currentAllocation.getStartHour().compareTo(newAllocation.getEndHour()) < 0
				&& newAllocation.getStartHour().compareTo(currentAllocation.getEndHour()) < 0;
	}
}
