package com.project.absenceManagementSystem.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "absenceJustifications")
public class AbsenceJustification {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String attachment;
	private Date createdAt;
	private Date deletedAt;
	@OneToOne
    @JoinColumn(name = "absence_id", referencedColumnName = "id")
	private Absence absence;
	
	public AbsenceJustification(String attachment, Date createdAt, Date deletedAt, Absence absence) {
		super();
		this.attachment = attachment;
		this.createdAt = createdAt;
		this.deletedAt = deletedAt;
		this.absence = absence;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
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

	public Absence getAbsence() {
		return absence;
	}

	public void setAbsence(Absence absence) {
		this.absence = absence;
	}
	
	
	
	
	
}
