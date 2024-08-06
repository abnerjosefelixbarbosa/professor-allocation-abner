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

import com.projeto.professorallocationabner.models.dtos.AllocationDTO;
import com.projeto.professorallocationabner.models.dtos.AllocationView;
import com.projeto.professorallocationabner.models.services.AllocationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/allocations")
public class AllocationController {
	private final AllocationService allocationService;

	public AllocationController(AllocationService allocationService) {
		this.allocationService = allocationService;
	}

	@GetMapping("/find-all-allocations")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Page<AllocationView>> findAllAllocations(Pageable pageable) {
		Page<AllocationView> page = allocationService.findAllAllocations(pageable);
		return ResponseEntity.status(200).body(page);
	}

	@GetMapping("/find-allocation-by-id")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<AllocationView> findAllocationById(@RequestParam Long id) {
		AllocationView view = allocationService.findAllocationById(id);
		return ResponseEntity.status(200).body(view);
	}

	@PostMapping("/save-allocation")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<AllocationView> saveAllocation(@RequestBody @Valid AllocationDTO dto) {
		AllocationView view = allocationService.saveAllocation(dto);
		return ResponseEntity.status(201).body(view);
	}

	@PutMapping("/update-allocation")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<AllocationView> updateAllocation(@RequestParam Long id, @RequestBody @Valid AllocationDTO dto) {
		AllocationView view = allocationService.updateAllocation(id, dto);
		return ResponseEntity.status(200).body(view);
	}

	@DeleteMapping("/delete-allocation-by-id")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteAllocationById(@RequestParam Long id) {
		allocationService.deleteAllocationById(id);
		return ResponseEntity.status(204).body(null);
	}

	@DeleteMapping("/delete-all-allocations")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteAllAllocations() {
		allocationService.deleteAllAllocations();
		return ResponseEntity.status(204).body(null);
	}
}
