package com.project.absenceManagementSystem.entities;

import java.util.Date;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Registration {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Boolean status;
	private Date createdAt;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "registration")
	private List<Absence> absences;
	@ManyToOne
	@JoinColumn(name = "student_id",referencedColumnName = "id")
	private Student student;
	@ManyToOne
	@JoinColumn(name = "level_id",referencedColumnName = "id")
	private Level level;
	
	public Registration(Boolean status, Date createdAt, List<Absence> absences, Student student, Level level) {
		super();
		this.status = status;
		this.createdAt = createdAt;
		this.absences = absences;
		this.student = student;
		this.level = level;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public List<Absence> getAbsences() {
		return absences;
	}

	public void setAbsences(List<Absence> absences) {
		this.absences = absences;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
	
}
