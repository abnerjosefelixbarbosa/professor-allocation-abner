package com.projeto.professorallocationabner.models.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projeto.professorallocationabner.models.dtos.CourseDTO;
import com.projeto.professorallocationabner.models.dtos.CourseView;
import com.projeto.professorallocationabner.models.entities.Course;
import com.projeto.professorallocationabner.models.mappers.CourseMapper;
import com.projeto.professorallocationabner.models.repositories.CourseRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CourseService {
	private final CourseRepository courseRepository;
	private final CourseMapper courseMapper;

	public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
		this.courseRepository = courseRepository;
		this.courseMapper = courseMapper;
	}

	public Page<CourseView> findAllCourses(Pageable pageable) {
		return courseRepository.findAll(pageable).map(courseMapper::toCourseView);
	}

	public CourseView findCourseById(Long id) {
		return courseRepository.findById(id).map(courseMapper::toCourseView)
				.orElseThrow(() -> new EntityNotFoundException("course not found"));
	}

	public CourseView findCourseByName(String name) {
		return courseRepository.findByName(name).map(courseMapper::toCourseView)
				.orElseThrow(() -> new EntityNotFoundException("course not found"));
	}

	public Course getCourseById(Long id) {
		return courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("course not found"));
	}

	public Course getCourseByName(String name) {
		return courseRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("course not found"));
	}

	public CourseView saveCourse(CourseDTO dto) {
		Course course = courseMapper.toCourse(dto);
		validateCourse(course);
		course = courseRepository.save(course);
		return courseMapper.toCourseView(course);
	}

	public CourseView updateCourse(Long id, CourseDTO dto) {
		Course course = courseMapper.toCourse(dto);
		validateCourse(course);
		Course response = courseRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("course not found"));
		response.setName(course.getName());
		return courseMapper.toCourseView(response);
	}

	public void deleteCourseById(Long id) {
		Course course = courseRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("course not found"));
		courseRepository.delete(course);
	}

	public void deleteCourseByName(String name) {
		Course course = courseRepository.findByName(name)
				.orElseThrow(() -> new EntityNotFoundException("course not found"));
		courseRepository.delete(course);
	}

	public void deleteAllCourses() {
		courseRepository.deleteAllInBatch();
	}

	private void validateCourse(Course course) {
		if (courseRepository.existsByName(course.getName()))
			throw new RuntimeException("name exists");
	}
}
