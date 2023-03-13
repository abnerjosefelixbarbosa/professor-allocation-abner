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

import com.projeto.professorallocationabner.entity.Professor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorRepositoryTest {
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Test
    @Disabled
	public void findAll() {
		try {
			List<Professor> professors = professorRepository.findAll();
			professors.forEach(System.out::println);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
    @Disabled
	public void findById() {
		
        try {
        	Long id = 2L;
        	
            Professor professor = professorRepository.findById(id).orElse(null);
            System.out.println(professor.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	@Disabled
	public void save_create() {
		try {
			Professor professor = new Professor();
			professor.setId(null);
			professor.setName("Professor 1");
			professor.setCpf("111.111.111-11");
			professor.setDepartmentId(4L);
		
			professorRepository.save(professor);
			System.out.println("professor salvo");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
    @Disabled
	public void save_update() {
		try {
			Long id = 2L;
			Professor professor = new Professor();
			professor.setId(2L);
			professor.setName("Professor 1");
			professor.setCpf("111.111.111-11");
			professor.setDepartmentId(4L);
			
            Professor professor1 = professorRepository.findById(id).orElse(null);
            BeanUtils.copyProperties(professor, professor1);
			
			professorRepository.save(professor1);
			System.out.println("professor atualizado");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	@Disabled
    public void deleteById() {
		try {
			Long id = 1L;
	        
			professorRepository.deleteById(id);
			System.out.println("professor deletado");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
	
	@Test
	@Disabled
    public void deleteAll() {
		try {
			professorRepository.deleteAllInBatch();
			System.out.println("professores deletados");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
}
