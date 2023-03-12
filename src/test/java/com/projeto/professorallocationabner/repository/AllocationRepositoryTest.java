package com.projeto.professorallocationabner.repository;

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

	@Test
	@Disabled
	public void findAll() {
		List<Allocation> allocations = allocationRepository.findAll();
		allocations.forEach(System.out::println);
	}
	
	@Test
	@Disabled
    public void findById() {
        Long id = 1L;
        Allocation allocation = allocationRepository.findById(id).orElse(null);
        System.out.println(allocation);
    }
}
