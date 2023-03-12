package com.projeto.professorallocationabner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.professorallocationabner.entity.Allocation;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Long> {

}
