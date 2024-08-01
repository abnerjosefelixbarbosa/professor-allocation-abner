package com.projeto.professorallocationabner.models.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.professorallocationabner.models.entities.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	boolean existsByName(String name);
	
	Optional<Department> findByName(String name);
}
