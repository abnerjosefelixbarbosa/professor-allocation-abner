package com.projeto.professorallocationabner.models.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.professorallocationabner.models.entities.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	Page<Department> findByNameIgnoreCase(String name, Pageable pageable);
}
