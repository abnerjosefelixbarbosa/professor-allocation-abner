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

	public Page<ProfessorView> findAll(Pageable pageable) {
		return professorRepository.findAll(pageable).map(professorMapper::toProfessorView);
	}

	public ProfessorView findById(Long id) {
		return professorRepository.findById(id).map(professorMapper::toProfessorView)
				.orElseThrow(() -> new EntityNotFoundException("professor not found"));
	}

	public Professor findByProfessorId(Long id) {
		return professorRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("professor not found"));
	}

	public Page<ProfessorView> findByDepartment(Long id, Pageable pageable) {
		return professorRepository.findByDepartmentId(id, pageable).map(professorMapper::toProfessorView);
	}

	public ProfessorView save(ProfessorDTO dto) {
		Professor professor = professorMapper.toProfessor(null, dto);
		validateProfessor(professor);
		
		Department department = departmentService.findDepartmentById(professor.getDepartment().getId());
		professor.setDepartment(department);
		
		professorRepository.save(professor);
		return professorMapper.toProfessorView(professor);
	}

	public ProfessorView update(Long id, ProfessorDTO dto) {
		Professor professor = professorMapper.toProfessor(id, dto);
		validateProfessor(professor);
		
		return professorRepository.findById(id).map((val) -> {
			Department department = departmentService.findDepartmentById(professor.getDepartment().getId());
			professor.setDepartment(department);
			val = professor;
			professorRepository.save(val);
			return professorMapper.toProfessorView(val);
		}).orElseThrow(() -> new EntityNotFoundException("professor not found"));
	}

	public void deleteById(Long id) {
		Professor professor = professorRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("professor not found"));
		professorRepository.delete(professor);
	}

	public void deleteAll() {
		professorRepository.deleteAllInBatch();
	}

	private void validateProfessor(Professor professor) {
		if (professor.getDepartment().getId() == null)
			throw new RuntimeException("id should not be null");
		if (professorRepository.existsByCpf(professor.getCpf()))
			throw new RuntimeException("cpf should not be exists");
	}
}
