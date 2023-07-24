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

import com.projeto.professorallocationabner.models.entities.Professor;
import com.projeto.professorallocationabner.models.repositories.ProfessorRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorRepositoryTest {
	@Autowired
	private ProfessorRepository professorRepository;

	@Test
	@Disabled
	public void findAll() throws Exception {
		List<Professor> professors = professorRepository.findAll();
		professors.forEach(System.out::println);
	}

	@Test
	@Disabled
	public void findById() throws Exception {
		Long id1 = 1L;
		Long id2 = 2L;

		Professor professor = professorRepository.findById(id1).orElse(null);
		System.out.println(professor);
	}

	@Test
	@Disabled
	public void save_create() throws Exception {
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

		professorRepository.save(professor2);
		System.out.println("professor salvo");
	}

	@Test
	@Disabled
	public void save_update() throws Exception {
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

		professorRepository.save(professor1);
		System.out.println("professor atualizado");
	}

	@Test
	@Disabled
	public void deleteById() throws Exception {
		Long id1 = 1L;
		Long id2 = 2L;

		professorRepository.deleteById(id1);
		System.out.println("professor deletado");
	}

	@Test
	@Disabled
	public void deleteAll() throws Exception {
		professorRepository.deleteAllInBatch();
		System.out.println("professores deletados");
	}
}
