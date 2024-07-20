package com.projeto.professorallocationabner.models.dtos;

import java.time.DayOfWeek;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public record AllocationDTO(
		Long id,
        DayOfWeek day,
        @JsonFormat(pattern = "HH:mmZ")
        @Temporal(TemporalType.TIME)
        Date startHour,
        @JsonFormat(pattern = "HH:mmZ")
        @Temporal(TemporalType.TIME)
        Date endHour,
        Long courseId,
        Long professorId
) {}
