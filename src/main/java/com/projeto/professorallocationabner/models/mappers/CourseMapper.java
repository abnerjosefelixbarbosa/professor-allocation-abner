package com.projeto.professorallocationabner.models.mappers;

import org.springframework.stereotype.Component;

import com.projeto.professorallocationabner.models.dtos.CourseDTO;
import com.projeto.professorallocationabner.models.dtos.CourseView;
import com.projeto.professorallocationabner.models.entities.Course;

@Component
public class CourseMapper {

	public Course toCourse(Long id, CourseDTO dto) {
		return Course.builder().id(id).name(dto.name()).build();
	}

	public CourseView toCourseView(Course course) {
		return new CourseView(course.getId(), course.getName());
	}
}
