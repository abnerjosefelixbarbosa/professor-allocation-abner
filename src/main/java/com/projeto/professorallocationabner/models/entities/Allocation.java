package com.projeto.professorallocationabner.models.entities;

import java.time.DayOfWeek;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "allocation")
public class Allocation {
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	@Enumerated(EnumType.STRING)
	@Column(name = "day", nullable = false)
	private DayOfWeek day;	
	@ApiModelProperty(example = "10:00-0300")
	@JsonFormat(pattern = "HH:mmZ")
	@JsonSerialize(using = DateSerializer.class)
	@JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
	@Temporal(TemporalType.TIME)
	@Column(name = "start", nullable = false)
	private Date startHour;	
	@ApiModelProperty(example = "12:00-0300")
	@JsonFormat(pattern = "HH:mmZ")
	@JsonSerialize(using = DateSerializer.class)
	@JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
	@Temporal(TemporalType.TIME)
	@Column(name = "end", nullable = false)
	private Date endHour;	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name = "course_id", nullable = false)
	private Long courseId;	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name = "professor_id", nullable = false)
	private Long professorId;	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@JsonIgnoreProperties({ "allocations" })
	@ManyToOne(optional = false)
	@JoinColumn(name = "professor_id", nullable = false, insertable = false, updatable = false)
	private Professor professor;	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@JsonIgnoreProperties({ "allocations" })
	@ManyToOne(optional = false)
	@JoinColumn(name = "course_id", nullable = false, insertable = false, updatable = false)
	private Course course;

	public Allocation() {
		super();
	}

	public Allocation(Long id, DayOfWeek day, Date startHour, Date endHour, Long courseId, Long professorId, Professor professor, Course course) {
		super();
		this.id = id;
		this.day = day;
		this.startHour = startHour;
		this.endHour = endHour;
		this.courseId = courseId;
		this.professorId = professorId;
		this.professor = professor;
		this.course = course;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DayOfWeek getDayOfWeek() {
		return day;
	}

	public void setDayOfWeek(DayOfWeek day) {
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

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Allocation [id=" + id + ", day=" + day + ", startHour=" + startHour + ", endHour=" + endHour
				+ ", courseId=" + courseId + ", professorId=" + professorId + "]";
	}
}
