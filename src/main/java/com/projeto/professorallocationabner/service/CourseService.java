package com.projeto.professorallocationabner.service;

import org.springframework.stereotype.Service;

import com.projeto.professorallocationabner.entity.Course;
import com.projeto.professorallocationabner.repository.CourseRepository;

@Service
public class CourseService {
	private final CourseRepository courseRepository;

	public CourseService(CourseRepository courseRepository) {
		super();
		this.courseRepository = courseRepository;
	}

	public Course findById(Long courseId) {
		return courseRepository.findById(courseId).orElse(null);
	} 
}
