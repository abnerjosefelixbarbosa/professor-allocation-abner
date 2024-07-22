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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.professorallocationabner.models.dtos.DepartmentDTO;
import com.projeto.professorallocationabner.models.dtos.DepartmentView;
import com.projeto.professorallocationabner.models.services.DepartmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/departments")
@RequiredArgsConstructor
public class DepartmentController {
	private final DepartmentService departmentService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Page<DepartmentView>> findAll(Pageable pageable) {
		Page<DepartmentView> page = departmentService.findAll(pageable);
		return ResponseEntity.status(200).body(page);
	}

	@GetMapping(path = "/find-by-name", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Page<DepartmentView>> findByNameIgnoreCase(
			@RequestParam(name = "name", required = false) String name, Pageable pageable) {
		Page<DepartmentView> page = departmentService.findByNameIgnoreCase(name, pageable);
		return ResponseEntity.status(200).body(page);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<DepartmentView> findById(@PathVariable(name = "id") Long id) {
		DepartmentView view = departmentService.findById(id);
		return ResponseEntity.status(200).body(view);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<DepartmentView> save(@RequestBody DepartmentDTO dto) {
		DepartmentView view = departmentService.save(dto);
		return ResponseEntity.status(201).body(view);
	}

	@PutMapping(path = "/{department_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<DepartmentView> update(@PathVariable(name = "department_id") Long id,
			@RequestBody DepartmentDTO dto) {
		DepartmentView view = departmentService.update(dto);
		return ResponseEntity.status(200).body(view);
	}
	
	@DeleteMapping(path = "/{department_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteById(@PathVariable(name = "department_id") Long id) {
		departmentService.deleteById(id);
		return ResponseEntity.status(204).body(null);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteAll() {
		departmentService.deleteAll();
		return ResponseEntity.status(204).body(null);
	}
}
