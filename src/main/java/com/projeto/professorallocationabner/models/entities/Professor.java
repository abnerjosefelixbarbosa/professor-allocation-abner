package com.projeto.professorallocationabner.models.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "professor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Professor implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "cpf", unique = true, nullable = false, length = 14)
	private String cpf;
	@ManyToOne
	@JoinColumn(name = "department_id", nullable = false)
	private Department department;
	@OneToMany(mappedBy = "professor")
	private List<Allocation> allocations;
}
