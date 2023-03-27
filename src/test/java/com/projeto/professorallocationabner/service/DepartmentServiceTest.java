package com.projeto.professorallocationabner.service;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.projeto.professorallocationabner.entity.Department;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentServiceTest {
	@Autowired
	private DepartmentService departmentService;
	
	@Test
    @Disabled
	public void findAll() throws Exception {
		List<Department> departments = departmentService.findAll();
		departments.forEach(System.out::println);
	}

	@Test
	@Disabled
	public void findById() throws Exception {
		Long id1 = 1L;
		Long id2 = 2L;

		Department department = departmentService.findById(id1);
		System.out.println(department.toString());
	}

	@Test
	@Disabled
	public void save() throws Exception {
		Department department1 = new Department();
		department1.setId(1L);
		department1.setName("Department 1");
		Department department2 = new Department();
		department2.setId(2L);
		department2.setName("Department 2");

		departmentService.save(department1);
		departmentService.save(department2);
		System.out.println("departamento salvo");
	}

	@Test
	@Disabled
	public void update() throws Exception {
		Department department1 = new Department();
		department1.setId(1L);
		department1.setName("Department 1");
		Department department2 = new Department();
		department2.setId(2L);
		department2.setName("Department 2");
		
		departmentService.save(department1);
		departmentService.save(department2);
		System.out.println("departamento atualizado");
	}
	
	@Test
	@Disabled
	public void deleteById() throws Exception {
		Long id1 = 1L;
		Long id2 = 2L;

		departmentService.deleteById(id1);
		departmentService.deleteById(id2);
		System.out.println("departamento deletado");
    }
    
	@Test
	@Disabled
    public void deleteAll() throws Exception {
    	departmentService.deleteAll();
		System.out.println("departamentos deletados");
    }
}
