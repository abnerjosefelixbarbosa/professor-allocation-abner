package com.projeto.professorallocationabner.service;

import java.util.List;

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

	public List<Course> findAll() {
		return courseRepository.findAll();
	}

	public Course findById(Long id) {
		return courseRepository.findById(id).orElse(null);
	}

	public Course save(Course course) {
		course.setId(null);
		return saveInternal(course);
	}

	public Course update(Course course) {
		Long id = course.getId();
		if (id != null && courseRepository.existsById(id)) {
			return saveInternal(course);
		} else {
			return null;
		}
	}

	private Course saveInternal(Course course) {
		course = courseRepository.save(course);
		return course;
	}
	
	public void deleteById(Long id) {
    	if (courseRepository.existsById(id)) {
    		courseRepository.deleteById(id);
    	}
    }
    
    public void deleteAll() {
    	courseRepository.deleteAllInBatch();
    }
}
