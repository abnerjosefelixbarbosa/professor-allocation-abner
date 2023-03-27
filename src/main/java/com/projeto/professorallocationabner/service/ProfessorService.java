package com.projeto.professorallocationabner.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projeto.professorallocationabner.entity.Department;
import com.projeto.professorallocationabner.entity.Professor;
import com.projeto.professorallocationabner.repository.ProfessorRepository;

@Service
public class ProfessorService {
	private final ProfessorRepository professorRepository;
	private final DepartmentService departmentService;
	
	public ProfessorService(ProfessorRepository professorRepository, DepartmentService departmentService) {
		super();
		this.professorRepository = professorRepository;
		this.departmentService = departmentService;
	}

	public List<Professor> findAll() {
		return professorRepository.findAll();
	}

	public Professor findById(Long id) {
		return professorRepository.findById(id).orElse(null);
	}
	
	public List<Professor> findByDepartment(Long departmentId) {
		return professorRepository.findByDepartment(departmentId);
	}

	public Professor save(Professor professor) {
		professor.setId(null);
		return saveInternal(professor);
	}

	public Professor update(Professor professor) {
		Long id = professor.getId();
		if (id != null && professorRepository.existsById(id)) {
			return saveInternal(professor);
		} else {
			return null;
		}
	}

	private Professor saveInternal(Professor professor) {
		professor = professorRepository.save(professor);
		
		Department department = departmentService.findById(professor.getDepartmentId());
		professor.setDepartment(department);
		
		return professor;
	}
	
	public void deleteById(Long id) {
    	if (professorRepository.existsById(id)) {
    		professorRepository.deleteById(id);
    	}
    }
    
    public void deleteAll() {
    	professorRepository.deleteAllInBatch();
    }
}
