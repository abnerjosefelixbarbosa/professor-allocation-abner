package com.projeto.professorallocationabner.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.professorallocationabner.models.dtos.ProfessorDTO;
import com.projeto.professorallocationabner.models.dtos.ProfessorView;
import com.projeto.professorallocationabner.models.services.ProfessorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/professors")
@RequiredArgsConstructor
public class ProfessorController {
	private final ProfessorService professorService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Page<ProfessorView>> findAll(Pageable pageable) {
		Page<ProfessorView> page = professorService.findAll(pageable);
		return ResponseEntity.status(200).body(page);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ProfessorView> findById(@PathVariable(name = "id") Long id) {
		ProfessorView view = professorService.findById(id);
		return ResponseEntity.status(200).body(view);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ProfessorView> save(@RequestBody ProfessorDTO dto) {
		ProfessorView view = professorService.save(dto);
		return ResponseEntity.status(201).body(view);
	}

	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ProfessorView> update(@PathVariable(name = "id") Long id, @RequestBody ProfessorDTO dto) {
		ProfessorView view = professorService.update(id, dto);
		return ResponseEntity.status(200).body(view);
	}

	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteById(@PathVariable(name = "id") Long id) {
		professorService.deleteById(id);
		return ResponseEntity.status(204).body(null);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteAll() {
		professorService.deleteAll();
		return ResponseEntity.status(204).body(null);
	}
}
