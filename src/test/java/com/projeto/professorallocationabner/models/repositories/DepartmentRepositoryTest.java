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

import com.projeto.professorallocationabner.models.entities.Department;
import com.projeto.professorallocationabner.models.repositories.DepartmentRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentRepositoryTest {
	@Autowired
	private DepartmentRepository departmentRepository;

	@Test
	@Disabled
	public void findAll() throws Exception {
		List<Department> departments = departmentRepository.findAll();
		departments.forEach(System.out::println);
	}

	@Test
	@Disabled
	public void findById() throws Exception {
		Long id1 = 1L;
		Long id2 = 2L;

		Department department = departmentRepository.findById(id1).orElse(null);
		System.out.println(department.toString());
	}

	@Test
	@Disabled
	public void save_create() throws Exception {
		Department department1 = new Department();
		department1.setId(1L);
		department1.setName("Department 1");
		Department department2 = new Department();
		department2.setId(2L);
		department2.setName("Department 2");

		departmentRepository.save(department2);
		System.out.println("departamento salvo");
	}

	@Test
	@Disabled
	public void save_update() throws Exception {
		Department department1 = new Department();
		department1.setId(1L);
		department1.setName("Department 1");
		Department department2 = new Department();
		department2.setId(2L);
		department2.setName("Department 2");

		departmentRepository.save(department1);
		System.out.println("departamento atualizado");
	}

	@Test
	@Disabled
	public void deleteById() throws Exception {
		Long id1 = 1L;
		Long id2 = 2L;

		departmentRepository.deleteById(id1);
		System.out.println("departamento deletado");
	}

	@Test
	@Disabled
	public void deleteAll() throws Exception {
		departmentRepository.deleteAllInBatch();
		System.out.println("departamentos deletados");
	}
}
