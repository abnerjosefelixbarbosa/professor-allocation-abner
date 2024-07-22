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

import com.projeto.professorallocationabner.models.dtos.CourseDTO;
import com.projeto.professorallocationabner.models.dtos.CourseView;
import com.projeto.professorallocationabner.models.services.CourseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/courses")
@RequiredArgsConstructor
public class CourseController {
	private final CourseService courseService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Page<CourseView>> findAll(Pageable pageable) {
		Page<CourseView> page = courseService.findAll(pageable);
	    return ResponseEntity.status(201).body(page);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<CourseView> findById(@PathVariable(name = "id") Long id) {
		CourseView view = courseService.findById(id);
		return ResponseEntity.status(200).body(view);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CourseView> save(@RequestBody CourseDTO dto) {
		CourseView view = courseService.save(dto);
	    return ResponseEntity.status(201).body(view);
	}
	
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<CourseView> update(@PathVariable(name = "id") Long id, @RequestBody CourseDTO dto) {
		CourseView view = courseService.update(dto);
	    return ResponseEntity.status(200).body(view);
	}
	
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteById(@PathVariable(name = "id") Long id) {
	    courseService.deleteById(id);
	    return ResponseEntity.status(204).body(null);
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteAll() {
		courseService.deleteAll();
	    return ResponseEntity.status(204).body(null);
	}
}
