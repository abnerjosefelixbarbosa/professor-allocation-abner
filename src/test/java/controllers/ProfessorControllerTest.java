package controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.professorallocationabner.ProfessorAllocationAbnerApplication;
import com.projeto.professorallocationabner.models.dtos.DepartmentDTO;
import com.projeto.professorallocationabner.models.dtos.ProfessorDTO;
import com.projeto.professorallocationabner.models.entities.Department;
import com.projeto.professorallocationabner.models.entities.Professor;
import com.projeto.professorallocationabner.models.repositories.DepartmentRepository;
import com.projeto.professorallocationabner.models.repositories.ProfessorRepository;

@SpringBootTest(classes = ProfessorAllocationAbnerApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class ProfessorControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private ProfessorRepository professorRepository;
	
	@BeforeEach
	public void setup() {
		professorRepository.deleteAll();
		departmentRepository.deleteAll();
	}

	@AfterEach
	public void tearDown() {
		professorRepository.deleteAll();
		departmentRepository.deleteAll();
	}
	
	@Test
	public void shouldFindAllProfessorsAndReturn200Status() throws Exception {
		Department department = new Department(null, "department1", null);
		department = departmentRepository.save(department);
		Professor professor = new Professor(null, "professor1", "579.557.310-85", department, null);
		professorRepository.save(professor);
		
		String url = String.format("/professors/find-all-professors");
		
		mockMvc.perform(get(url)).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(print());
	}
	
	@Test
	public void shouldFindProfessorByIdAndReturn200Status() throws Exception {
		Department department = new Department(null, "department1", null);
		department = departmentRepository.save(department);
		Professor professor = new Professor(null, "professor1", "579.557.310-85", department, null);
		professor = professorRepository.save(professor);
		
		String url = String.format("/professors/find-professor-by-id?id=%d", professor.getId());
		
		mockMvc.perform(get(url)).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(print());
	}
	
	@Test
	public void shouldFindProfessorByNameAndReturn200Status() throws Exception {
		Department department = new Department(null, "department1", null);
		department = departmentRepository.save(department);
		Professor professor = new Professor(null, "professor1", "579.557.310-85", department, null);
		professor = professorRepository.save(professor);
		
		String url = String.format("/professors/find-professor-by-name?name=%s", professor.getName());
		
		mockMvc.perform(get(url)).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(print());
	}
	
	@Test
	public void shouldSaveProfessorAndReturn201Status() throws Exception {
		Department department = new Department(null, "department1", null);
		department = departmentRepository.save(department);
		
		DepartmentDTO departmentDTO = new DepartmentDTO(department.getId(), department.getName());
		ProfessorDTO professorDTO = new ProfessorDTO(null, "professor1", "579.557.310-85", departmentDTO);
	    String json = objectMapper.writeValueAsString(professorDTO);
				
		String url = String.format("/professors/save-professor");		
		
		mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(MockMvcResultMatchers.status().isCreated())
				.andDo(print());
	}
	
	@Test
	public void shouldUpdateProfessorAndReturn200Status() throws Exception {
		Department department = new Department(null, "department1", null);
		department = departmentRepository.save(department);
		Professor professor = new Professor(null, "professor1", "579.557.310-85", department, null);
		professor = professorRepository.save(professor);
		
		DepartmentDTO departmentDTO = new DepartmentDTO(department.getId(), department.getName());
		ProfessorDTO professorDTO = new ProfessorDTO(null, "professor1", "507.607.820-08", departmentDTO);
	    String json = objectMapper.writeValueAsString(professorDTO);
				
		String url = String.format("/professors/update-professor?id=%d", professor.getId());		
		
		mockMvc.perform(put(url).contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(print());
	}
	
	@Test
	public void shouldDeleteProfessorByIdAndReturn204Status() throws Exception {
		Department department = new Department(null, "department1", null);
		department = departmentRepository.save(department);
		Professor professor = new Professor(null, "professor1", "579.557.310-85", department, null);
		professor = professorRepository.save(professor);
				
		String url = String.format("/professors/delete-professor-by-id?id=%d", professor.getId());		
		
		mockMvc.perform(delete(url)).andExpect(MockMvcResultMatchers.status().isNoContent())
				.andDo(print());
	}
	
	@Test
	public void shouldDeleteProfessorByNameAndReturn204Status() throws Exception {
		Department department = new Department(null, "department1", null);
		department = departmentRepository.save(department);
		Professor professor = new Professor(null, "professor1", "579.557.310-85", department, null);
		professor = professorRepository.save(professor);
				
		String url = String.format("/professors/delete-professor-by-name?name=%s", professor.getName());		
		
		mockMvc.perform(delete(url)).andExpect(MockMvcResultMatchers.status().isNoContent())
				.andDo(print());
	}
	
	@Test
	public void shouldDeleteAllProfessorsAndReturn204Status() throws Exception {
		String url = String.format("/professors/delete-all-professors");		
		
		mockMvc.perform(delete(url)).andExpect(MockMvcResultMatchers.status().isNoContent())
				.andDo(print());
	}
}
