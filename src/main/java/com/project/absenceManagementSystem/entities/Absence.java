package com.project.absenceManagementSystem.entities;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "absences")
public class Absence {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Boolean justified;
	private Date createdAt;
	private Date deletedAt;
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "absence")
	private AbsenceJustification justification;
	@ManyToOne
    @JoinColumn(name = "session_type_id", referencedColumnName = "id")
	private SessionType sessionType;
	@ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
	private Teacher teacher;
	@ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
	private Student student;
	@ManyToOne
    @JoinColumn(name = "element_id", referencedColumnName = "id")
	private Element element;
	@ManyToOne
    @JoinColumn(name = "registration_id", referencedColumnName = "id")
	private Registration registration;
	

	public SessionType getSessionType() {
		return sessionType;
	}

	public void setSessionType(SessionType sessionType) {
		this.sessionType = sessionType;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}

	public Registration getRegistration() {
		return registration;
	}

	public void setRegistration(Registration registration) {
		this.registration = registration;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getJustified() {
		return justified;
	}

	public void setJustified(Boolean justified) {
		this.justified = justified;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	public AbsenceJustification getJustification() {
		return justification;
	}

	public void setJustification(AbsenceJustification justification) {
		this.justification = justification;
	}
	
}
