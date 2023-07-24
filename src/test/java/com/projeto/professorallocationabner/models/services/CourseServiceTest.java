package com.projeto.professorallocationabner.models.services;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.projeto.professorallocationabner.models.entities.Course;
import com.projeto.professorallocationabner.models.services.CourseService;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class CourseServiceTest {
	@Autowired
	private CourseService courseService;
	
	@Test
	@Disabled
	public void findAll() throws Exception {
		List<Course> courses = courseService.findAll();
		courses.forEach(System.out::println);
	}
	
	@Test
	@Disabled
	public void findById() throws Exception {
		Long id1 = 1L;
		Long id2 = 2L;
		Course course = courseService.findById(id1);

		System.out.println(course.toString());
	}
	
	@Test
	@Disabled
	public void save() throws Exception {
		Course course1 = new Course();
		course1.setId(1L);
		course1.setName("Course 1");
		Course course2 = new Course();
		course2.setId(2L);
		course2.setName("Course 2");

		courseService.save(course1);
		courseService.save(course2);
		System.out.println("curso salvo");
	}
	
	@Test
	@Disabled
	public void update() throws Exception {
		Course course1 = new Course();
		course1.setId(1L);
		course1.setName("Course 1");
		Course course2 = new Course();
		course2.setId(2L);
		course2.setName("Course 2");

		courseService.save(course1);
		courseService.save(course2);
		System.out.println("curso atualizado");
	}
	
	@Test
	@Disabled
	public void deleteById() throws Exception {
		Long id1 = 1L;
		Long id2 = 2L;

		courseService.deleteById(id1);
		System.out.println("curso deletado");
	}
	
	@Test
	@Disabled
	public void deleteAll() throws Exception {
		courseService.deleteAll();
		System.out.println("cursos deletados");
	}
}
