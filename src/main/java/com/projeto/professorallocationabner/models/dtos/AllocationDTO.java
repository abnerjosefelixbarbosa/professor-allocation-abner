package com.projeto.professorallocationabner.models.dtos;

import java.time.DayOfWeek;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

public record AllocationDTO(@NotNull DayOfWeek day,
		@JsonFormat(pattern = "HH:mm") @Temporal(TemporalType.TIME) @NotNull Date startHour,
		@JsonFormat(pattern = "HH:mm") @Temporal(TemporalType.TIME) @NotNull Date endHour, @NotNull CourseDTO course,
		@NotNull ProfessorDTO professor) {
}