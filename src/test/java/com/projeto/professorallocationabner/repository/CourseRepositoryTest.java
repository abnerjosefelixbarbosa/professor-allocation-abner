package com.projeto.professorallocationabner.repository;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.projeto.professorallocationabner.entity.Course;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class CourseRepositoryTest {
	@Autowired
	private CourseRepository courseRepository;

	@Test
	@Disabled
	public void findAll() {
		try {
			List<Course> courses = courseRepository.findAll();
			courses.forEach(System.out::println);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	@Disabled
	public void findById() {
		try {
			Long id1 = 1L;
			Long id2 = 2L;
			Course course = courseRepository.findById(id1).orElse(null);

			System.out.println(course.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	@Disabled
	public void save_create() {
		try {
			Course course1 = new Course();
			course1.setId(1L);
			course1.setName("Course 1");
			Course course2 = new Course();
			course2.setId(2L);
			course2.setName("Course 2");

			courseRepository.save(course1);
			System.out.println("curso salvo");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	@Disabled
	public void save_update() {
		try {
			Course course1 = new Course();
			course1.setId(1L);
			course1.setName("Course 1");
			Course course2 = new Course();
			course2.setId(2L);
			course2.setName("Course 2");

			courseRepository.save(course1);
			System.out.println("curso atualizado");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	@Disabled
	public void deleteById() {
		try {
			Long id1 = 1L;
			Long id2 = 2L;
			
			courseRepository.deleteById(id1);
			System.out.println("curso deletado");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	@Disabled
	public void deleteAll() {
		try {
			courseRepository.deleteAllInBatch();
			System.out.println("cursos deletados");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
