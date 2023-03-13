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
	public void findAll() {
		try {
			List<Department> departments = departmentRepository.findAll();
			departments.forEach(System.out::println);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
    @Disabled
	public void findById() {
        try {
        	Long id = 1L;
        	
            Department department = departmentRepository.findById(id).orElse(null);            
            System.out.println(department.toString()); 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	//@Disabled
	public void save_create() {
		try {
			Department department = new Department();
			department.setId(null);
			department.setName("Department 1");	
		
			departmentRepository.save(department);
			System.out.println("departamento salvo");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
    @Disabled
	public void save_update() {
		try {
			Long id = 1L;
			Department department = new Department();
			department.setId(1L);
			department.setName("Department 1");	
			
			Department department1 = departmentRepository.findById(id).orElse(null);  
			BeanUtils.copyProperties(department, department1);
			
			departmentRepository.save(department1);
			System.out.println("departamento atualizado");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	@Disabled
    public void deleteById() {
		try {
			Long id = 1L;
	        
			departmentRepository.deleteById(id);
			System.out.println("departamento deletado");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
	
	@Test
	@Disabled
    public void deleteAll() {
		try {
			departmentRepository.deleteAllInBatch();
			System.out.println("departamentos deletados");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
}
