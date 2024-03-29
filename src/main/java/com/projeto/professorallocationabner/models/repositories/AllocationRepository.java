package com.projeto.professorallocationabner.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.professorallocationabner.models.entities.Allocation;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Long> {
	List<Allocation> findByProfessorId(Long professorId);
	List<Allocation> findByCourseId(Long courseId);
}
