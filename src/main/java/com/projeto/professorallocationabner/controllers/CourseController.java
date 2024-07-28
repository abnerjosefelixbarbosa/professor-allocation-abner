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

import com.projeto.professorallocationabner.models.dtos.CourseDTO;
import com.projeto.professorallocationabner.models.dtos.CourseView;
import com.projeto.professorallocationabner.models.services.CourseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/courses")
@RequiredArgsConstructor
public class CourseController {
	private final CourseService courseService;

	@GetMapping("/find-all")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Page<CourseView>> findAll(Pageable pageable) {
		Page<CourseView> page = courseService.findAll(pageable);
		return ResponseEntity.status(201).body(page);
	}

	@GetMapping("/find-by-id")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<CourseView> findById(@RequestParam("id") Long id) {
		CourseView view = courseService.findById(id);
		return ResponseEntity.status(200).body(view);
	}
	
	@GetMapping("/find-by-name")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<CourseView> findByName(@RequestParam("name") String name) {
		CourseView view = courseService.findByName(name);
		return ResponseEntity.status(200).body(view);
	}

	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CourseView> save(@RequestBody @Valid CourseDTO dto) {
		CourseView view = courseService.save(dto);
		return ResponseEntity.status(201).body(view);
	}

	@PutMapping("/update-by-id")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<CourseView> updateById(@RequestParam("id") Long id, @Valid @RequestBody CourseDTO dto) {
		CourseView view = courseService.update(id, dto);
		return ResponseEntity.status(200).body(view);
	}
	
	@PutMapping("/update-by-name")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<CourseView> updateByName(@RequestParam("name") String name, @Valid @RequestBody CourseDTO dto) {
		CourseView view = courseService.update(name, dto);
		return ResponseEntity.status(200).body(view);
	}

	@DeleteMapping("delete-by-id")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteById(@RequestParam("id") Long id) {
		courseService.deleteById(id);
		return ResponseEntity.status(204).body(null);
	}
	
	@DeleteMapping("delete-by-name")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteByName(@RequestParam("name") String name) {
		courseService.deleteByName(name);
		return ResponseEntity.status(204).body(null);
	}

	@DeleteMapping("delete-all")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteAll() {
		courseService.deleteAll();
		return ResponseEntity.status(204).body(null);
	}
}
