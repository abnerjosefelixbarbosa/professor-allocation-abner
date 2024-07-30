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
import com.projeto.professorallocationabner.models.dtos.CourseDTO;
import com.projeto.professorallocationabner.models.entities.Course;
import com.projeto.professorallocationabner.models.repositories.CourseRepository;

@SpringBootTest(classes = ProfessorAllocationAbnerApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class CourseControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private CourseRepository courseRepository;

	@BeforeEach
	public void setup() {
		courseRepository.deleteAll();
	}

	@AfterEach
	public void tearDown() {
		courseRepository.deleteAll();
	}
	
	@Test
	public void shouldFindAllCoursesAndReturn200Status() throws Exception {
		Course course = new Course(null, "course1", null);
		courseRepository.save(course);
		
		String url = String.format("/courses/find-all-courses");
		
		mockMvc.perform(get(url)).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(print());
	}
	
	@Test
	public void shouldFindCourseByIdAndReturn200Status() throws Exception {
		Course course = new Course(null, "course1", null);
		courseRepository.save(course);
		
		String url = String.format("/courses/find-course-by-id?id=%d", course.getId());
		
		mockMvc.perform(get(url)).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(print());
	}
	
	@Test
	public void shouldFindCourseByNameAndReturn200Status() throws Exception {
		Course course = new Course(null, "course1", null);
		courseRepository.save(course);
		
		String url = String.format("/courses/find-course-by-name?name=%s", course.getName());
		
		mockMvc.perform(get(url)).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(print());
	}
	
	@Test
	public void shouldSaveCourseAndReturn201Status() throws Exception {
		Course course = new Course(null, "course1", null);
		courseRepository.save(course);
		
		CourseDTO dto = new CourseDTO(null, "course2");
		String json = objectMapper.writeValueAsString(dto);
				
		String url = String.format("/courses/save-course");		
		
		mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(MockMvcResultMatchers.status().isCreated())
				.andDo(print());
	}
	
	@Test
	public void shouldUpdateCourseAndReturn200Status() throws Exception {
		Course course = new Course(null, "course1", null);
		courseRepository.save(course);
		
		CourseDTO dto = new CourseDTO(null, "course2");
		String json = objectMapper.writeValueAsString(dto);
				
		String url = String.format("/courses/update-course?id=%d", course.getId());		
		
		mockMvc.perform(put(url).contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(print());
	}
	
	@Test
	public void shouldDeleteCourseByIdAndReturn204Status() throws Exception {
		Course course = new Course(null, "course1", null);
		courseRepository.save(course);
				
		String url = String.format("/courses/delete-course-by-id?id=%d", course.getId());		
		
		mockMvc.perform(delete(url)).andExpect(MockMvcResultMatchers.status().isNoContent())
				.andDo(print());
	}
	
	@Test
	public void shouldDeleteCourseByNameAndReturn204Status() throws Exception {
		Course course = new Course(null, "course1", null);
		courseRepository.save(course);
				
		String url = String.format("/courses/delete-course-by-name?name=%s", course.getName());		
		
		mockMvc.perform(delete(url)).andExpect(MockMvcResultMatchers.status().isNoContent())
				.andDo(print());
	}
	
	@Test
	public void shouldDeleteAllCoursesAndReturn204Status() throws Exception {
		String url = String.format("/courses/delete-all-courses");		
		
		mockMvc.perform(delete(url)).andExpect(MockMvcResultMatchers.status().isNoContent())
				.andDo(print());
	}
}