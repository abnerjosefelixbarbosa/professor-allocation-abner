package com.projeto.professorallocationabner.repository;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.projeto.professorallocationabner.entity.Department;

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
		Long id = 1L;
        Department department = departmentRepository.findById(id).orElse(null);
        
        boolean existsById = departmentRepository.existsById(id);
        if (!existsById) {
        	System.out.println("departamento não encotrada");
        }
        
        System.out.println(department.toString());
	}

	@Test
	@Disabled
	public void save() throws Exception {
		Department department = new Department();
		department.setId(null);
		department.setName("Department 3");	
	
		departmentRepository.save(department);
		System.out.println("departamento salvo");
	}
	
	@Test
    @Disabled
	public void update() throws Exception {
		Long id = 1L;
		Department department = new Department();
		department.setId(1L);
		department.setName("Department 2");	
		
		boolean existsById = departmentRepository.existsById(id);
		if (!existsById) {
			System.out.println("departamento não encotrada");
		} else {
			departmentRepository.save(department);
			System.out.println("departamento atualizado");
		}
	}
	
	@Test
	@Disabled
    public void deleteById() {
        Long id = 1L;
        
        boolean existsById = departmentRepository.existsById(id);
		if (!existsById) {
			System.out.println("departamento não encotrada");
		} else {
			departmentRepository.deleteById(id);
			System.out.println("departamento deletado");
		}
    }
	
	@Test
	@Disabled
    public void deleteAll() {
		departmentRepository.deleteAllInBatch();
		System.out.println("departamentos deletados");
    }
}
