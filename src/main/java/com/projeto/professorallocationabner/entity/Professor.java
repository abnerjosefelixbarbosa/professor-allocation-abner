package com.projeto.professorallocationabner.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "professor")
public class Professor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "cpf", unique = true, nullable = false, length = 14)
	private String cpf;
	@Column(name = "department_id", nullable = false)
	private Long departmentId;
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(optional = false)
	@JoinColumn(name = "department_id", nullable = false, insertable = false, updatable = false)
	private Department department;
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(mappedBy = "professor")
	private List<Allocation> allocations;

	public Professor() {
		super();
	}

	public Professor(Long id, String name, String cpf, Long departmentId, Department department,
			List<Allocation> allocations) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.departmentId = departmentId;
		this.department = department;
		this.allocations = allocations;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Allocation> getAllocations() {
		return allocations;
	}

	public void setAllocations(List<Allocation> allocations) {
		this.allocations = allocations;
	}

	@Override
	public String toString() {
		return "Professor [id=" + id + ", name=" + name + ", cpf=" + cpf + ", departmentId=" + departmentId + "]";
	}
}
