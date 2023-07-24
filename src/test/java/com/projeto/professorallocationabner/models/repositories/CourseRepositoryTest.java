package com.projeto.professorallocationabner.models.repositories;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.projeto.professorallocationabner.models.entities.Course;
import com.projeto.professorallocationabner.models.repositories.CourseRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class CourseRepositoryTest {
	@Autowired
	private CourseRepository courseRepository;

	@Test
	@Disabled
	public void findAll() throws Exception {
		List<Course> courses = courseRepository.findAll();
		courses.forEach(System.out::println);
	}

	@Test
	@Disabled
	public void findById() throws Exception {
		Long id1 = 1L;
		Long id2 = 2L;
		Course course = courseRepository.findById(id1).orElse(null);

		System.out.println(course.toString());
	}

	@Test
	@Disabled
	public void save_create() throws Exception {
		Course course1 = new Course();
		course1.setId(1L);
		course1.setName("Course 1");
		Course course2 = new Course();
		course2.setId(2L);
		course2.setName("Course 2");

		courseRepository.save(course1);
		System.out.println("curso salvo");
	}

	@Test
	@Disabled
	public void save_update() throws Exception {
		Course course1 = new Course();
		course1.setId(1L);
		course1.setName("Course 1");
		Course course2 = new Course();
		course2.setId(2L);
		course2.setName("Course 2");

		courseRepository.save(course1);
		System.out.println("curso atualizado");
	}

	@Test
	@Disabled
	public void deleteById() throws Exception {
		Long id1 = 1L;
		Long id2 = 2L;

		courseRepository.deleteById(id1);
		System.out.println("curso deletado");
	}

	@Test
	@Disabled
	public void deleteAll() throws Exception {
		courseRepository.deleteAllInBatch();
		System.out.println("cursos deletados");
	}
}
