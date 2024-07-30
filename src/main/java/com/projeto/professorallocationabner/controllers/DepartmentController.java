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

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {
	private final DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@GetMapping("/find-all-departments")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Page<DepartmentView>> findAllDepartments(Pageable pageable) {
		Page<DepartmentView> responses = departmentService.findAllDepartments(pageable);
		return ResponseEntity.status(200).body(responses);
	}

	@GetMapping("/find-department-by-name-ignore-case")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Page<DepartmentView>> findDepartmentByNameIgnoreCase(@RequestParam("name") String name,
			Pageable pageable) {
		Page<DepartmentView> responses = departmentService.findDepartmentByNameIgnoreCase(name, pageable);
		return ResponseEntity.status(200).body(responses);
	}

	@GetMapping("/find-department-by-id")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<DepartmentView> findDepartmentById(@RequestParam("id") Long id) {
		DepartmentView response = departmentService.findDepartmentById(id);
		return ResponseEntity.status(200).body(response);
	}
	
	@GetMapping("/find-department-by-name")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<DepartmentView> findDepartmentByName(@RequestParam("name") String name) {
		DepartmentView view = departmentService.findDepartmentByName(name);
		return ResponseEntity.status(200).body(view);
	}

	@PostMapping("/save-department")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<DepartmentView> saveDepartment(@RequestBody @Valid DepartmentDTO dto) {
		DepartmentView view = departmentService.saveDepartment(dto);
		return ResponseEntity.status(201).body(view);
	}

	@PutMapping("/update-department")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<DepartmentView> updateDepartment(@RequestParam("id") Long id, @Valid @RequestBody DepartmentDTO dto) {
		DepartmentView view = departmentService.updateDepartment(id, dto);
		return ResponseEntity.status(200).body(view);
	}

	@DeleteMapping("/delete-department-by-id")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteDepartmentById(@RequestParam("id") Long id) {
		departmentService.deleteDepartmentById(id);
		return ResponseEntity.status(204).body(null);
	}
	
	@DeleteMapping("/delete-department-by-name")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteDepartmentByName(@RequestParam("name") String name) {
		departmentService.deleteDepartmentByName(name);
		return ResponseEntity.status(204).body(null);
	}

	@DeleteMapping("/delete-all-departments")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteAllDepartments() {
		departmentService.deleteAllDepartments();
		return ResponseEntity.status(204).body(null);
	}
}
