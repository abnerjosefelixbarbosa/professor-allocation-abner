package com.projeto.professorallocationabner.service;

import org.springframework.stereotype.Service;

import com.projeto.professorallocationabner.entity.Professor;
import com.projeto.professorallocationabner.repository.ProfessorRepository;

@Service
public class ProfessorService {
	private final ProfessorRepository professorRepository;

	public ProfessorService(ProfessorRepository professorRepository) {
		super();
		this.professorRepository = professorRepository;
	}

	public Professor findById(Long id) {
		return professorRepository.findById(id).orElse(null);
	}
}
