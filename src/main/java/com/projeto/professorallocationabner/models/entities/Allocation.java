package com.projeto.professorallocationabner.models.entities;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "allocation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Allocation implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	@Enumerated(EnumType.STRING)
	@Column(name = "day_week", nullable = false)
	private DayOfWeek dayWeek;	
	@Temporal(TemporalType.TIME)
	@Column(name = "start_hour", nullable = false)
	private Date startHour;	
	@Temporal(TemporalType.TIME)
	@Column(name = "end_hour", nullable = false)
	private Date endHour;	
	@ManyToOne
	@JoinColumn(name = "professor_id", nullable = false)
	private Professor professor;	
	@ManyToOne
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;
}
