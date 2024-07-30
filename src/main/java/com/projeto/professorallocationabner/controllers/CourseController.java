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

@RestController
@RequestMapping(path = "/courses")
public class CourseController {
	private final CourseService courseService;
	
	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	@GetMapping("/find-all-courses")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Page<CourseView>> findAllCourses(Pageable pageable) {
		Page<CourseView> page = courseService.findAllCourses(pageable);
		return ResponseEntity.status(200).body(page);
	}

	@GetMapping("/find-course-by-id")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<CourseView> findCourseById(@RequestParam("id") Long id) {
		CourseView view = courseService.findCourseById(id);
		return ResponseEntity.status(200).body(view);
	}
	
	@GetMapping("/find-course-by-name")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<CourseView> findCourseByName(@RequestParam("name") String name) {
		CourseView view = courseService.findCourseByName(name);
		return ResponseEntity.status(200).body(view);
	}

	@PostMapping("/save-course")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CourseView> saveCourse(@RequestBody @Valid CourseDTO dto) {
		CourseView view = courseService.saveCourse(dto);
		return ResponseEntity.status(201).body(view);
	}
	
	@PutMapping("/update-course")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<CourseView> updateCourse(@RequestParam("id") Long id, @Valid @RequestBody CourseDTO dto) {
		CourseView view = courseService.updateCourse(id, dto);
		return ResponseEntity.status(200).body(view);
	}

	@DeleteMapping("/delete-course-by-id")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteCourseById(@RequestParam("id") Long id) {
		courseService.deleteCourseById(id);
		return ResponseEntity.status(204).body(null);
	}
	
	@DeleteMapping("/delete-course-by-name")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteCourseByName(@RequestParam("name") String name) {
		courseService.deleteCourseByName(name);
		return ResponseEntity.status(204).body(null);
	}

	@DeleteMapping("/delete-all-courses")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteAllCourses() {
		courseService.deleteAllCourses();
		return ResponseEntity.status(204).body(null);
	}
}
