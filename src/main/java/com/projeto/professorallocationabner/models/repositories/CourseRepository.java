package com.projeto.professorallocationabner.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.professorallocationabner.models.entities.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
