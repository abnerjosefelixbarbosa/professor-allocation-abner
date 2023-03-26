package com.projeto.professorallocationabner.service;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.projeto.professorallocationabner.entity.Allocation;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationServiceTest {
	@Autowired
	private AllocationService allocationService;
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mmZ");

	@Test
	@Disabled
	public void findAll() throws Exception  {
			List<Allocation> allocations = allocationService.findAll();
			allocations.forEach(System.out::println);
	}

	@Test
	@Disabled
	public void findByProfessor() throws Exception  {
			Long id = 1L;
			List<Allocation> allocations = allocationService.findByProfessor(id);
			allocations.forEach(System.out::println);
	}

	@Test
	@Disabled
	public void findByCourse() throws Exception  {
			Long id = 1L;
			List<Allocation> allocations = allocationService.findByCourse(id);
			allocations.forEach(System.out::println);
	}

	@Test
	@Disabled
	public void findById() throws Exception {
		Long id = 1L;
		Allocation allocation = allocationService.findById(id);
		System.out.println(allocation);
	}

	@Test
	@Disabled
	public void save() throws Exception {
		Allocation allocation = new Allocation();
        allocation.setId(null);
        allocation.setDayOfWeek(DayOfWeek.WEDNESDAY);
        allocation.setStartHour(sdf.parse("19:00-0300"));
        allocation.setEndHour(sdf.parse("20:00-0300"));
        allocation.setProfessorId(1L);
        allocation.setCourseId(1L);
        allocation = allocationService.save(allocation);
        System.out.println(allocation);
	}
	
	@Test
	@Disabled
	public void update() throws Exception {
		Allocation allocation = new Allocation();
        allocation.setId(1L);
        allocation.setDayOfWeek(DayOfWeek.WEDNESDAY);
        allocation.setStartHour(sdf.parse("19:00-0300"));
        allocation.setEndHour(sdf.parse("20:00-0300"));
        allocation.setProfessorId(1L);
        allocation.setCourseId(1L);
        allocation = allocationService.update(allocation);
        System.out.println(allocation);
	}
	
	@Test
	@Disabled
    public void deleteById() throws Exception {
        Long id = 1L;
        allocationService.deleteById(id);
    }
	
	@Test
	@Disabled
    public void deleteAll() throws Exception {
        allocationService.deleteAll();
    }
}
