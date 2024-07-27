package com.projeto.professorallocationabner.models.dtos;

import java.time.DayOfWeek;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

public record AllocationDTO(Long id,
		@NotNull(message = "day should not be null") DayOfWeek day,
		@JsonFormat(pattern = "HH:mm") @Temporal(TemporalType.TIME) @NotNull(message = "start hour should not be null") Date startHour,
		@JsonFormat(pattern = "HH:mm") @Temporal(TemporalType.TIME) @NotNull(message = "end hour should not be null") Date endHour,
		@NotNull(message = "course should not be null") CourseDTO course,
		@NotNull(message = "professor should not be null") ProfessorDTO professor) {
}