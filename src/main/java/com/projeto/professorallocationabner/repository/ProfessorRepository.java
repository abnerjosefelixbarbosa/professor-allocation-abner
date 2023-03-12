package com.projeto.professorallocationabner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.professorallocationabner.entity.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}
