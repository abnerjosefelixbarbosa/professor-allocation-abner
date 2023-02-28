package com.projeto.professorallocationabner.entity;

import java.time.DayOfWeek;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "allocation")
public class Allocation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	@Column(name = "day", nullable = false)
	private DayOfWeek day;
	@Temporal(TemporalType.TIME)
	@Column(name = "start", nullable = false)
	private Date startHour;
	@Temporal(TemporalType.TIME)
	@Column(name = "end", nullable = false)
	private Date endHour;
	@Column(name = "professor_id", nullable = false)
	private Long courseId;
	@Column(name = "course_id", nullable = false)
	private Long professorId;

	public Allocation() {
		super();
	}

	public Allocation(Long id, DayOfWeek day, Date startHour, Date endHour, Long courseId, Long professorId) {
		super();
		this.id = id;
		this.day = day;
		this.startHour = startHour;
		this.endHour = endHour;
		this.courseId = courseId;
		this.professorId = professorId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DayOfWeek getDay() {
		return day;
	}

	public void setDay(DayOfWeek day) {
		this.day = day;
	}

	public Date getStartHour() {
		return startHour;
	}

	public void setStartHour(Date startHour) {
		this.startHour = startHour;
	}

	public Date getEndHour() {
		return endHour;
	}

	public void setEndHour(Date endHour) {
		this.endHour = endHour;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getProfessorId() {
		return professorId;
	}

	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}

	@Override
	public String toString() {
		return "Allocation [id=" + id + ", day=" + day + ", startHour=" + startHour + ", endHour=" + endHour
				+ ", courseId=" + courseId + ", professorId=" + professorId + "]";
	}

}
