package com.projeto.professorallocationabner.models.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projeto.professorallocationabner.models.dtos.ProfessorDTO;
import com.projeto.professorallocationabner.models.dtos.ProfessorView;
import com.projeto.professorallocationabner.models.entities.Department;
import com.projeto.professorallocationabner.models.entities.Professor;
import com.projeto.professorallocationabner.models.mappers.ProfessorMapper;
import com.projeto.professorallocationabner.models.repositories.ProfessorRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfessorService {
	private final ProfessorRepository professorRepository;
	private final DepartmentService departmentService;
	private final ProfessorMapper professorMapper;

	public Page<ProfessorView> findAllProfessors(Pageable pageable) {
		return professorRepository.findAll(pageable).map(professorMapper::toProfessorView);
	}

	public ProfessorView findProfessorById(Long id) {
		return professorRepository.findById(id).map(professorMapper::toProfessorView)
				.orElseThrow(() -> new EntityNotFoundException("professor not found"));
	}
	
	public ProfessorView findProfessorByName(String name) {
		return professorRepository.findByName(name).map(professorMapper::toProfessorView)
				.orElseThrow(() -> new EntityNotFoundException("professor not found"));
	}

	public Professor getProfessorById(Long id) {
		return professorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("professor not found"));
	}

	public Page<ProfessorView> findAllProfessorsByDepartmentId(Long id, Pageable pageable) {
		return professorRepository.findByDepartmentId(id, pageable).map(professorMapper::toProfessorView);
	}

	public ProfessorView saveProfessor(ProfessorDTO dto) {
		Professor professor = professorMapper.toProfessor(dto);
		validateProfessor(professor);

		Department department = departmentService.getDepartmentById(professor.getDepartment().getId());
		professor.setDepartment(department);

		professorRepository.save(professor);
		return professorMapper.toProfessorView(professor);
	}

	public ProfessorView updateProfessor(Long id, ProfessorDTO dto) {
		Professor professor = professorMapper.toProfessor(dto);
		validateProfessor(professor);

		Professor response = professorRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("professor not found"));
		Department department = departmentService.getDepartmentByName(professor.getDepartment().getName());
		response.setDepartment(department);

		return professorMapper.toProfessorView(response);
	}

	public void deleteProfessorById(Long id) {
		Professor professor = professorRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("professor not found"));
		professorRepository.delete(professor);
	}
	
	public void deleteProfessorByName(String name) {
		Professor professor = professorRepository.findByName(name)
				.orElseThrow(() -> new EntityNotFoundException("professor not found"));
		professorRepository.delete(professor);
	}

	public void deleteAllProfessors() {
		professorRepository.deleteAllInBatch();
	}

	private void validateProfessor(Professor professor) {
		if (professorRepository.existsByCpf(professor.getCpf()))
			throw new RuntimeException("cpf exists");
	}
}
