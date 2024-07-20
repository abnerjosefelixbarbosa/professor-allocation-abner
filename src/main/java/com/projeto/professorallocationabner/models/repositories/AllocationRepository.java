package com.projeto.professorallocationabner.models.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.professorallocationabner.models.entities.Allocation;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Long> {
	Page<Allocation> findByProfessorId(Long professorId, Pageable pageable);
	Page<Allocation> findByCourseId(Long courseId, Pageable pageable);
	boolean existsByProfessorId(Long professorId);
}
