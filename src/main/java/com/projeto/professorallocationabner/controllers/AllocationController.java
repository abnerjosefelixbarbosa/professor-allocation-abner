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

import com.projeto.professorallocationabner.models.dtos.AllocationDto;
import com.projeto.professorallocationabner.models.dtos.AllocationView;
import com.projeto.professorallocationabner.models.services.AllocationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/allocations")
@RequiredArgsConstructor
public class AllocationController {
	private final AllocationService allocationService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Page<AllocationView>> findAll(Pageable pageable) {
		Page<AllocationView> page = allocationService.findAll(pageable);
		return ResponseEntity.status(200).body(page);
	}

	@GetMapping(path = "/{allocation_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<AllocationView> findById(@PathVariable(name = "allocation_id") Long id) {
		AllocationView view = allocationService.findById(id);
		return ResponseEntity.status(200).body(view);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<AllocationView> save(@RequestBody @Valid AllocationDto dto) {
		AllocationView view = allocationService.save(dto);
		return ResponseEntity.status(201).body(view);
	}

	@PutMapping(path = "/{allocation_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<AllocationView> update(@PathVariable(name = "allocation_id") Long id,
			@RequestBody AllocationDto dto) {
		AllocationView view = allocationService.update(id, dto);
		return ResponseEntity.status(200).body(view);
	}

	@DeleteMapping(path = "/{allocation_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteById(@PathVariable(name = "allocation_id") Long id) {
		allocationService.deleteById(id);
		return ResponseEntity.status(204).body(null);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteAll() {
		allocationService.deleteAll();
		return ResponseEntity.status(204).body(null);
	}
}
