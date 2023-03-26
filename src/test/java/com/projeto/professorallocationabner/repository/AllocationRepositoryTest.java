package com.projeto.professorallocationabner.repository;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.projeto.professorallocationabner.entity.Allocation;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationRepositoryTest {
	@Autowired
	private AllocationRepository allocationRepository;
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mmZ");

	@Test
	@Disabled
	public void findAll() {
		try {
			List<Allocation> allocations = allocationRepository.findAll();
			allocations.forEach(System.out::println);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	@Disabled
    public void findById() {
        try {
        	Long id = 1L;
            Allocation allocation = allocationRepository.findById(id).orElse(null);
            System.out.println(allocation.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
	
	@Test
	@Disabled
    public void save_create() {
        try {
        	Allocation allocation1 = new Allocation();
            allocation1.setId(1L);
            allocation1.setDayOfWeek(DayOfWeek.MONDAY);
            allocation1.setStartHour(sdf.parse("19:00-0300"));
            allocation1.setEndHour(sdf.parse("20:00-0300"));
            allocation1.setProfessorId(1L);
            allocation1.setCourseId(1L);
            Allocation allocation2 = new Allocation();
            allocation2.setId(2L);
            allocation2.setDayOfWeek(DayOfWeek.MONDAY);
            allocation2.setStartHour(sdf.parse("22:00-0300"));
            allocation2.setEndHour(sdf.parse("24:00-0300"));
            allocation2.setProfessorId(2L);
            allocation2.setCourseId(2L);

            allocationRepository.save(allocation1);
            System.out.println("alocação salva");
        } catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
	
	@Test
	@Disabled
    public void save_update() {
        try {
        	Allocation allocation1 = new Allocation();
            allocation1.setId(1L);
            allocation1.setDayOfWeek(DayOfWeek.MONDAY);
            allocation1.setStartHour(sdf.parse("19:00-0300"));
            allocation1.setEndHour(sdf.parse("20:00-0300"));
            allocation1.setProfessorId(1L);
            allocation1.setCourseId(1L);  
            Allocation allocation2 = new Allocation();
            allocation2.setId(2L);
            allocation2.setDayOfWeek(DayOfWeek.MONDAY);
            allocation2.setStartHour(sdf.parse("22:00-0300"));
            allocation2.setEndHour(sdf.parse("24:00-0300"));
            allocation2.setProfessorId(2L);
            allocation2.setCourseId(2L);   

            allocationRepository.save(allocation1);
            System.out.println("alocação salva");
        } catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
	
	@Test
	@Disabled
    public void deleteById() {
		try {
			Long id1 = 1L;
			Long id2 = 2L;
	        
			allocationRepository.deleteById(id1);
			System.out.println("alocação deletada");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
	
	@Test
	@Disabled
    public void deleteAll() {
		try {
			allocationRepository.deleteAllInBatch();
			System.out.println("alocações deletadas");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
}
