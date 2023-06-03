package com.project.absenceManagementSystem.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "absences")
@AllArgsConstructor
public class Absence{

	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Boolean justified=false;
	private Date createdAt;
	private Date deletedAt;
	private Date start;
	private Date end;
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "absence")
	private AbsenceJustification justification;
    @JsonIgnore
	@ManyToOne
    @JoinColumn(name = "session_type_id", referencedColumnName = "id")
	private SessionType sessionType;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
	private Teacher teacher;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "element_id", referencedColumnName = "id")
	private Element element;
	@JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "registration_id", referencedColumnName = "id")
	private Registration registration;
	
	
	public Absence() {
		
	}

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

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
	
	
	
}
