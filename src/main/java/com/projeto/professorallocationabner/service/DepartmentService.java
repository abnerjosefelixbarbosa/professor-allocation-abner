package com.projeto.professorallocationabner.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projeto.professorallocationabner.entity.Department;
import com.projeto.professorallocationabner.repository.DepartmentRepository;

@Service
public class DepartmentService {
	private final DepartmentRepository departmentRepository;

	public DepartmentService(DepartmentRepository departmentRepository) {
		super();
		this.departmentRepository = departmentRepository;
	}
	
	public List<Department> findAll() {
		return departmentRepository.findAll();
	}
	
	public List<Department> findByNameIgnoreCase(String name) {
		return departmentRepository.findByNameIgnoreCase(name);
	}

	public Department findById(Long id) {
		return departmentRepository.findById(id).orElse(null);
	}

	public Department save(Department department) {
		return saveInternal(department);
	}

	public Department update(Department department) {
		Long id = department.getId();
		if (id != null && departmentRepository.existsById(id)) {
			return saveInternal(department);
		} else {
			return null;
		}
	}

	private Department saveInternal(Department department) {
		department = departmentRepository.save(department);
		return department;
	}
	
	public void deleteById(Long id) {
    	if (departmentRepository.existsById(id)) {
    		departmentRepository.deleteById(id);
    	}
    }
    
    public void deleteAll() {
    	departmentRepository.deleteAllInBatch();
    }
}
