package com.projeto.professorallocationabner.models.services;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.projeto.professorallocationabner.models.entities.Allocation;
import com.projeto.professorallocationabner.models.services.AllocationService;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationServiceTest {
	@Autowired
	private AllocationService allocationService;
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mmZ");

	@Test
	@Disabled
	public void findAll() throws Exception {
		List<Allocation> allocations = allocationService.findAll();
		allocations.forEach(System.out::println);
	}

	@Test
	@Disabled
	public void findById() throws Exception {
		Long id1 = 1L;
		Long id2 = 2L;

		Allocation allocation = allocationService.findById(id1);
		System.out.println(allocation);
	}

	@Test
	@Disabled
	public void findByProfessor() throws Exception {
		Long id1 = 1L;
		Long id2 = 2L;
		
		List<Allocation> allocations = allocationService.findByProfessor(id1);
		allocations.forEach(System.out::println);
	}

	@Test
	@Disabled
	public void findByCourse() throws Exception {
		Long id1 = 1L;
		Long id2 = 2L;

		List<Allocation> allocations = allocationService.findByCourse(id1);
		allocations.forEach(System.out::println);
	}

	@Test
	@Disabled
	public void save() throws Exception {
		Allocation allocation1 = new Allocation();
		allocation1.setId(null);
		allocation1.setDay(DayOfWeek.WEDNESDAY);
		allocation1.setStartHour(sdf.parse("19:00-0300"));
		allocation1.setEndHour(sdf.parse("20:00-0300"));
		allocation1.setProfessorId(1L);
		allocation1.setCourseId(1L);
		Allocation allocation2 = new Allocation();
		allocation2.setId(2L);
		allocation2.setDay(DayOfWeek.MONDAY);
		allocation2.setStartHour(sdf.parse("22:00-0300"));
		allocation2.setEndHour(sdf.parse("24:00-0300"));
		allocation2.setProfessorId(2L);
		allocation2.setCourseId(2L);

		allocationService.save(allocation1);
		allocationService.save(allocation2);
		System.out.println("alocação salva");
	}

	@Test
	@Disabled
	public void update() throws Exception {
		Allocation allocation1 = new Allocation();
		allocation1.setId(1L);
		allocation1.setDay(DayOfWeek.WEDNESDAY);
		allocation1.setStartHour(sdf.parse("19:00-0300"));
		allocation1.setEndHour(sdf.parse("20:00-0300"));
		allocation1.setProfessorId(1L);
		allocation1.setCourseId(1L);
		Allocation allocation2 = new Allocation();
		allocation2.setId(2L);
		allocation2.setDay(DayOfWeek.MONDAY);
		allocation2.setStartHour(sdf.parse("22:00-0300"));
		allocation2.setEndHour(sdf.parse("24:00-0300"));
		allocation2.setProfessorId(2L);
		allocation2.setCourseId(2L);

		allocationService.save(allocation1);
		System.out.println("alocação atualizada");
	}

	@Test
	@Disabled
	public void deleteById() throws Exception {
		Long id1 = 1L;
		Long id2 = 2L;

		allocationService.deleteById(id1);
		System.out.println("alocação deletada");
	}

	@Test
	@Disabled
	public void deleteAll() throws Exception {
		allocationService.deleteAll();
		System.out.println("alocações deletadas");
	}
}
