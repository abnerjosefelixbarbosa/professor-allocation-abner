package com.projeto.professorallocationabner.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.professorallocationabner.models.dtos.ProfessorDTO;
import com.projeto.professorallocationabner.models.dtos.ProfessorView;
import com.projeto.professorallocationabner.models.services.ProfessorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/professors")
public class ProfessorController {
	private final ProfessorService professorService;

	public ProfessorController(ProfessorService professorService) {
		this.professorService = professorService;
	}

	@GetMapping("/find-all-professors")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Page<ProfessorView>> findAllProfessors(Pageable pageable) {
		Page<ProfessorView> responses = professorService.findAllProfessors(pageable);
		return ResponseEntity.status(200).body(responses);
	}

	@GetMapping("/find-professor-by-id")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ProfessorView> findProfessorById(@RequestParam("id") Long id) {
		ProfessorView response = professorService.findProfessorById(id);
		return ResponseEntity.status(200).body(response);
	}

	@GetMapping("/find-professor-by-name")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ProfessorView> findProfessorByName(@RequestParam("name") String name) {
		ProfessorView response = professorService.findProfessorByName(name);
		return ResponseEntity.status(200).body(response);
	}

	@PostMapping("/save-professor")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ProfessorView> saveProfessor(@RequestBody @Valid ProfessorDTO dto) {
		ProfessorView response = professorService.saveProfessor(dto);
		return ResponseEntity.status(201).body(response);
	}

	@PutMapping("/update-professor")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ProfessorView> updateProfessor(@RequestParam("id") Long id,
			@RequestBody @Valid ProfessorDTO dto) {
		ProfessorView response = professorService.updateProfessor(id, dto);
		return ResponseEntity.status(200).body(response);
	}

	@DeleteMapping("/delete-professor-by-id")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteProfessorById(@RequestParam("id") Long id) {
		professorService.deleteProfessorById(id);
		return ResponseEntity.status(204).body(null);
	}

	@DeleteMapping("/delete-professor-by-name")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteProfessorByName(@RequestParam("name") String name) {
		professorService.deleteProfessorByName(name);
		return ResponseEntity.status(204).body(null);
	}

	@DeleteMapping("/delete-all-professors")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteAllProfessors() {
		professorService.deleteAllProfessors();
		return ResponseEntity.status(204).body(null);
	}
}
