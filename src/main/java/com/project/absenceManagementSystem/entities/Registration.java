package com.project.absenceManagementSystem.entities;

import java.util.Date;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Registration {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Boolean status;
	private Date createdAt;
	@OneToMany(mappedBy = "registration")
	private List<Absence> absences;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "student_id",referencedColumnName = "id")
	private Student student;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "level_id",referencedColumnName = "id")
	private Level level;
	
	public Registration() {
		
	}
	
	public Registration(Boolean status, Date createdAt, Level level) {
		super();
		this.status = status;
		this.createdAt = createdAt;
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
