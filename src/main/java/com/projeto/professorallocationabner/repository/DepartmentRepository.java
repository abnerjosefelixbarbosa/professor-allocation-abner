package com.projeto.professorallocationabner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.professorallocationabner.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
