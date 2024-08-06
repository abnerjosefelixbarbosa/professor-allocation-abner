package com.projeto.professorallocationabner.models.dtos;

import java.time.DayOfWeek;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public record AllocationView(
		Long id,
		DayOfWeek day,
		@JsonFormat(pattern = "HH:mm")
		@Temporal(TemporalType.TIME)
		Date startHour,
		@JsonFormat(pattern = "HH:mm")
		@Temporal(TemporalType.TIME)
		Date endHour,
		CourseView course,
		ProfessorView professor
) {}