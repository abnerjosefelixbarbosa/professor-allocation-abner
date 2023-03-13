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
			Long id = 1L;
			Course course = courseRepository.findById(id).orElse(null);

			System.out.println(course.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	@Disabled
	public void save_create() {
		try {
			Course course = new Course();
			course.setId(null);
			course.setName("Course 2");

			courseRepository.save(course);
			System.out.println("curso salvo");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	@Disabled
	public void save_update() {
		try {
			Long id = 1L;
			Course course = new Course();
			course.setId(1L);
			course.setName("Course 1");
			
			Course course1 = courseRepository.findById(id).orElse(null);
			BeanUtils.copyProperties(course, course1);

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
			Long id = 1L;
			
			courseRepository.deleteById(id);
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
