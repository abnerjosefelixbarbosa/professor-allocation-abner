package com.projeto.professorallocationabner.models.dtos;

import java.time.DayOfWeek;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public record AllocationView(
		Long id,
        DayOfWeek day,
        @JsonFormat(pattern = "HH:mmZ")
        @JsonSerialize(using = DateSerializer.class)
        @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
        @Temporal(TemporalType.TIME)
        Date startHour,
        @JsonFormat(pattern = "HH:mmZ")
        @JsonSerialize(using = DateSerializer.class)
        @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
        @Temporal(TemporalType.TIME)
        Date endHour,
        CourseView course,
        ProfessorView professor
) {}