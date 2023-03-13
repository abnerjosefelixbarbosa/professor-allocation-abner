package com.projeto.professorallocationabner.repository;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
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
	//@Disabled
    public void save_create() {
        try {
        	Allocation allocation = new Allocation();
            allocation.setId(null);
            allocation.setDay(DayOfWeek.MONDAY);
            allocation.setStartHour(sdf.parse("19:00-0300"));
            allocation.setEndHour(sdf.parse("20:00-0300"));
            allocation.setProfessorId(2L);
            allocation.setCourseId(2L);

            allocationRepository.save(allocation);
            System.out.println("alocação salva");
        } catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
	
	@Test
	@Disabled
    public void save_update() {
        try {
        	Long id = 1L;
        	Allocation allocation = new Allocation();
            allocation.setId(1L);
            allocation.setDay(DayOfWeek.MONDAY);
            allocation.setStartHour(sdf.parse("19:00-0300"));
            allocation.setEndHour(sdf.parse("20:00-0300"));
            allocation.setProfessorId(1L);
            allocation.setCourseId(1L);            
            
            Allocation allocation1 = allocationRepository.findById(id).orElse(null);
            BeanUtils.copyProperties(allocation, allocation1);

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
			Long id = 1L;
	        
			allocationRepository.deleteById(id);
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
