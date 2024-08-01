package com.projeto.professorallocationabner.models.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.professorallocationabner.models.entities.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
	Page<Professor> findByDepartmentId(Long departmentId, Pageable pageable);

	boolean existsByCpf(String cpf);

	Optional<Professor> findByName(String name);
}
