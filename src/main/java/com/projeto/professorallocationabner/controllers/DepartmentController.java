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

import com.projeto.professorallocationabner.models.dtos.DepartmentDTO;
import com.projeto.professorallocationabner.models.dtos.DepartmentView;
import com.projeto.professorallocationabner.models.services.DepartmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/departments")
@RequiredArgsConstructor
public class DepartmentController {
	private final DepartmentService departmentService;

	@GetMapping("/find-all")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Page<DepartmentView>> findAll(Pageable pageable) {
		Page<DepartmentView> page = departmentService.findAll(pageable);
		return ResponseEntity.status(200).body(page);
	}

	@GetMapping("/find-by-name-ignore-case")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Page<DepartmentView>> findByNameIgnoreCase(@RequestParam("name") String name,
			Pageable pageable) {
		Page<DepartmentView> page = departmentService.findByNameIgnoreCase(name, pageable);
		return ResponseEntity.status(200).body(page);
	}

	@GetMapping("/find-by-id")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<DepartmentView> findById(@RequestParam("id") Long id) {
		DepartmentView view = departmentService.findById(id);
		return ResponseEntity.status(200).body(view);
	}
	
	@GetMapping("/find-by-name")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<DepartmentView> findByName(@RequestParam("name") String name) {
		DepartmentView view = departmentService.findByName(name);
		return ResponseEntity.status(200).body(view);
	}

	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<DepartmentView> save(@RequestBody @Valid DepartmentDTO dto) {
		DepartmentView view = departmentService.save(dto);
		return ResponseEntity.status(201).body(view);
	}

	@PutMapping("/update-by-id")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<DepartmentView> update(@RequestParam("id") Long id, @Valid @RequestBody DepartmentDTO dto) {
		DepartmentView view = departmentService.update(id, dto);
		return ResponseEntity.status(200).body(view);
	}

	@DeleteMapping("/delete-by-id")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteById(@RequestParam("id") Long id) {
		departmentService.deleteById(id);
		return ResponseEntity.status(204).body(null);
	}
	
	@DeleteMapping("/delete-by-name")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteByName(@RequestParam("name") String name) {
		departmentService.deleteByName(name);
		return ResponseEntity.status(204).body(null);
	}

	@DeleteMapping("/delete-all")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteAll() {
		departmentService.deleteAll();
		return ResponseEntity.status(204).body(null);
	}
}
