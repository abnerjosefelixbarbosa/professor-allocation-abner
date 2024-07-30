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
import com.projeto.professorallocationabner.models.entities.Department;
import com.projeto.professorallocationabner.models.repositories.DepartmentRepository;

@SpringBootTest(classes = ProfessorAllocationAbnerApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class DepartmentControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@BeforeEach
	public void setup() {
		departmentRepository.deleteAll();
	}

	@AfterEach
	public void tearDown() {
		departmentRepository.deleteAll();
	}
	
	@Test
	public void shouldFindAllDepartmentsAndReturn200Status() throws Exception {
		Department department = new Department(null, "department1", null);
		departmentRepository.save(department);
		
		String url = String.format("/departments/find-all-departments");
		
		mockMvc.perform(get(url)).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(print());
	}
	
	@Test
	public void shouldFindDepartmentByIdAndReturn200Status() throws Exception {
		Department department = new Department(null, "department1", null);
		departmentRepository.save(department);
		
		String url = String.format("/departments/find-department-by-id?id=%d", department.getId());
		
		mockMvc.perform(get(url)).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(print());
	}
	
	@Test
	public void shouldFindDepartmentByNameAndReturn200Status() throws Exception {
		Department department = new Department(null, "department1", null);
		departmentRepository.save(department);
		
		String url = String.format("/departments/find-department-by-name?name=%s", department.getName());
		
		mockMvc.perform(get(url)).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(print());
	}
	
	@Test
	public void shouldSaveDepartmentAndReturn201Status() throws Exception {
		Department department = new Department(null, "department1", null);
		departmentRepository.save(department);
		
		DepartmentDTO dto = new DepartmentDTO(null, "department2");
		String json = objectMapper.writeValueAsString(dto);
				
		String url = String.format("/departments/save-department");		
		
		mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(MockMvcResultMatchers.status().isCreated())
				.andDo(print());
	}
	
	@Test
	public void shouldUpdateDepartmentAndReturn200Status() throws Exception {
		Department department = new Department(null, "department1", null);
		departmentRepository.save(department);
		
		DepartmentDTO dto = new DepartmentDTO(null, "department2");
		String json = objectMapper.writeValueAsString(dto);
				
		String url = String.format("/departments/update-department?id=%d", department.getId());		
		
		mockMvc.perform(put(url).contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(print());
	}
	
	@Test
	public void shouldDeleteDepartmentByIdAndReturn204Status() throws Exception {
		Department department = new Department(null, "department1", null);
		departmentRepository.save(department);
				
		String url = String.format("/departments/delete-department-by-id?id=%d", department.getId());		
		
		mockMvc.perform(delete(url)).andExpect(MockMvcResultMatchers.status().isNoContent())
				.andDo(print());
	}
	
	@Test
	public void shouldDeleteDepartmentByNameAndReturn204Status() throws Exception {
		Department department = new Department(null, "department1", null);
		departmentRepository.save(department);
				
		String url = String.format("/departments/delete-department-by-name?name=%s", department.getName());		
		
		mockMvc.perform(delete(url)).andExpect(MockMvcResultMatchers.status().isNoContent())
				.andDo(print());
	}
	
	@Test
	public void shouldDeleteAllDepartmentsAndReturn204Status() throws Exception {
		String url = String.format("/departments/delete-all-departments");		
		
		mockMvc.perform(delete(url)).andExpect(MockMvcResultMatchers.status().isNoContent())
				.andDo(print());
	}
}
