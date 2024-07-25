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
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseService {
	private final CourseRepository courseRepository;
	private final CourseMapper courseMapper;

	public Page<CourseView> findAll(Pageable pageable) {
		return courseRepository.findAll(pageable).map(courseMapper::toCourseView);
	}

	public CourseView findById(Long id) {
		return courseRepository.findById(id).map(courseMapper::toCourseView)
				.orElseThrow(() -> new EntityNotFoundException("course not found"));
	}

	public Course findCourseById(Long id) {
		return courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("course not found"));
	}

	public CourseView save(CourseDTO dto) {
		Course course = courseMapper.toCourse(dto);
		course = saveInternal(course);
		return courseMapper.toCourseView(course);
	}

	public CourseView update(CourseDTO dto) {
		Long id = dto.id();

		return courseRepository.findById(id).map((val) -> {
			Course course = courseMapper.toCourse(dto);
			course = saveInternal(course);
			return courseMapper.toCourseView(course);
		}).orElseThrow(() -> new EntityNotFoundException("course not found"));
	}

	public void deleteById(Long id) {
		Course course = courseRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("course not found"));

		courseRepository.delete(course);
	}

	public void deleteAll() {
		courseRepository.deleteAllInBatch();
	}

	private Course saveInternal(Course course) {
		course = courseRepository.save(course);
		return course;
	}
}
