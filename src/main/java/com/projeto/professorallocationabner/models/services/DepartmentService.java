package com.projeto.professorallocationabner.models.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projeto.professorallocationabner.models.dtos.DepartmentDTO;
import com.projeto.professorallocationabner.models.dtos.DepartmentView;
import com.projeto.professorallocationabner.models.entities.Department;
import com.projeto.professorallocationabner.models.mappers.DepartmentMapper;
import com.projeto.professorallocationabner.models.repositories.DepartmentRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentService {
	private final DepartmentRepository departmentRepository;
	private final DepartmentMapper departmentMapper;

	public Page<DepartmentView> findAll(Pageable pageable) {
		return departmentRepository.findAll(pageable).map(departmentMapper::toDepartmentView);
	}

	public Page<DepartmentView> findByNameIgnoreCase(String name, Pageable pageable) {
		return departmentRepository.findByNameIgnoreCase(name, pageable).map(departmentMapper::toDepartmentView);
	}

	public DepartmentView findById(Long id) {
		return departmentRepository.findById(id).map(departmentMapper::toDepartmentView)
				.orElseThrow(() -> new EntityNotFoundException("department not found"));
	}

	public Department findDepartmentById(Long id) {
		return departmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("department not found"));
	}

	public DepartmentView save(DepartmentDTO dto) {
		Department department = departmentMapper.toDepartment(null, dto);
		department = departmentRepository.save(department);
		return departmentMapper.toDepartmentView(department);
	}

	public DepartmentView update(Long id, DepartmentDTO dto) {
		return departmentRepository.findById(id).map((val) -> {
			val = departmentMapper.toDepartment(id, dto);
			departmentRepository.save(val);
			return departmentMapper.toDepartmentView(val);
		}).orElseThrow(() -> new EntityNotFoundException("department not found"));
	}

	public void deleteById(Long id) {
		Department department = departmentRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("department not found"));
		departmentRepository.delete(department);
	}

	public void deleteAll() {
		departmentRepository.deleteAllInBatch();
	}
}
