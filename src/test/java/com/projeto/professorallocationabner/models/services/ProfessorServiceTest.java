package com.projeto.professorallocationabner.models.services;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.projeto.professorallocationabner.models.entities.Professor;
import com.projeto.professorallocationabner.models.services.ProfessorService;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorServiceTest {
	@Autowired
	private ProfessorService professorService;

	@Test
	@Disabled
	public void findAll() throws Exception {
		List<Professor> professors = professorService.findAll();
		professors.forEach(System.out::println);
	}

	@Test
	@Disabled
	public void findById() throws Exception {
		Long id1 = 1L;
		Long id2 = 2L;

		Professor professor = professorService.findById(id1);
		System.out.println(professor);
	}

	@Test
	@Disabled
	public void findByDepartment() throws Exception {
		Long id1 = 1L;
		Long id2 = 2L;

		List<Professor> professors = professorService.findByDepartment(id2);
		professors.forEach(System.out::println);
	}

	@Test
	@Disabled
	public void save() throws Exception {
		Professor professor1 = new Professor();
		professor1.setId(1L);
		professor1.setName("Professor 1");
		professor1.setCpf("111.111.111-11");
		professor1.setDepartmentId(1L);
		Professor professor2 = new Professor();
		professor2.setId(2L);
		professor2.setName("Professor 2");
		professor2.setCpf("222.222.222-22");
		professor2.setDepartmentId(2L);

		professorService.save(professor1);
		professorService.save(professor2);
		System.out.println("professor salvo");
	}

	@Test
	@Disabled
	public void update() throws Exception {
		Professor professor1 = new Professor();
		professor1.setId(1L);
		professor1.setName("Professor 1");
		professor1.setCpf("111.111.111-11");
		professor1.setDepartmentId(1L);
		Professor professor2 = new Professor();
		professor2.setId(2L);
		professor2.setName("Professor 2");
		professor2.setCpf("222.222.222-22");
		professor2.setDepartmentId(2L);

		professorService.save(professor1);
		professorService.save(professor2);
		System.out.println("professor atualizado");
	}

	@Test
	@Disabled
	public void deleteById() throws Exception {
		Long id1 = 1L;
		Long id2 = 2L;

		professorService.deleteById(id1);
		professorService.deleteById(id2);
		System.out.println("professor deletado");
	}

	@Test
	@Disabled
	public void deleteAll() throws Exception {
		professorService.deleteAll();
		System.out.println("professores deletados");
	}
}
