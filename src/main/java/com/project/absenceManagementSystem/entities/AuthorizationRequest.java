package com.project.absenceManagementSystem.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "authorizationRequests")
public class AuthorizationRequest {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Boolean status;
	private String message;
	private Date createdAt;
	private Date deletedAt;
	@ManyToOne
	@JoinColumn(name = "student_id",referencedColumnName = "id")
	private Student student;
	@ManyToOne
	@JoinColumn(name = "teacher_id",referencedColumnName = "id")
	private Teacher teacher;
	
	
	public AuthorizationRequest(Boolean status, String message, Date createdAt, Date deletedAt) {
		super();
		this.status = status;
		this.message = message;
		this.createdAt = createdAt;
		this.deletedAt = deletedAt;
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	
}
