package com.projeto.professorallocationabner.models.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projeto.professorallocationabner.models.entities.Department;
import com.projeto.professorallocationabner.models.entities.Professor;
import com.projeto.professorallocationabner.models.repositories.ProfessorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfessorService {
	private final ProfessorRepository professorRepository;
	private final DepartmentService departmentService;

	public List<Professor> findAll() {
		return professorRepository.findAll();
	}

	public Professor findById(Long id) {
		return professorRepository.findById(id).orElse(null);
	}
	
	public List<Professor> findByDepartment(Long departmentId) {
		return professorRepository.findByDepartmentId(departmentId);
	}

	public Professor save(Professor professor) {
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
