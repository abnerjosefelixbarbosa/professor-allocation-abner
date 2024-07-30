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

@Service
public class DepartmentService {
	private final DepartmentRepository departmentRepository;
	private final DepartmentMapper departmentMapper;

	public DepartmentService(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
		this.departmentRepository = departmentRepository;
		this.departmentMapper = departmentMapper;
	}

	public Page<DepartmentView> findAllDepartments(Pageable pageable) {
		return departmentRepository.findAll(pageable).map(departmentMapper::toDepartmentView);
	}

	public Page<DepartmentView> findDepartmentByNameIgnoreCase(String name, Pageable pageable) {
		return departmentRepository.findByNameIgnoreCase(name, pageable).map(departmentMapper::toDepartmentView);
	}

	public DepartmentView findDepartmentById(Long id) {
		return departmentRepository.findById(id).map(departmentMapper::toDepartmentView)
				.orElseThrow(() -> new EntityNotFoundException("department not found"));
	}

	public DepartmentView findDepartmentByName(String name) {
		return departmentRepository.findByName(name).map(departmentMapper::toDepartmentView)
				.orElseThrow(() -> new EntityNotFoundException("department not found"));
	}

	public Department getDepartmentById(Long id) {
		return departmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("department not found"));
	}

	public Department getDepartmentByName(String name) {
		return departmentRepository.findByName(name)
				.orElseThrow(() -> new EntityNotFoundException("department not found"));
	}

	public DepartmentView saveDepartment(DepartmentDTO dto) {
		Department department = departmentMapper.toDepartment(null, dto);
		validateDepartment(department);
		department = departmentRepository.save(department);
		return departmentMapper.toDepartmentView(department);
	}

	public DepartmentView updateDepartment(Long id, DepartmentDTO dto) {
		Department department = departmentMapper.toDepartment(id, dto);
		validateDepartment(department);
		Department response = departmentRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("department not found"));
		response.setName(department.getName());
		return departmentMapper.toDepartmentView(response);
	}

	public void deleteDepartmentById(Long id) {
		Department department = departmentRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("department not found"));
		departmentRepository.delete(department);
	}

	public void deleteDepartmentByName(String name) {
		Department department = departmentRepository.findByName(name)
				.orElseThrow(() -> new EntityNotFoundException("department not found"));
		departmentRepository.delete(department);
	}

	public void deleteAllDepartments() {
		departmentRepository.deleteAllInBatch();
	}

	private void validateDepartment(Department department) {
		if (departmentRepository.existsByName(department.getName()))
			throw new RuntimeException("name exists");
	}
}
