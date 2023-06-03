package com.project.absenceManagementSystem.dto;

import java.util.Date;

public class AbscenceDto {
	
	private Long id;
	private Date start;
	private Date end;
	private String startString;
	private String endString;
	private Boolean justified;
	private Long teacher;
	private String teacher_name;
	private Long student;
	private String student_name;
	private Long element;
	private Long registration;
	private Long session;
	private String element_label;
	private Date createdAt;
	
	
	
	public AbscenceDto(Long id, Date start, Date end, Boolean justified, Long teacher, Long student,
			String student_name, Long element, String element_label, Date createdAt) {
		super();
		this.id = id;
		this.start = start;
		this.end = end;
		this.justified = justified;
		this.teacher = teacher;
		this.student = student;
		this.student_name = student_name;
		this.element = element;
		this.element_label = element_label;
		this.createdAt = createdAt;
	}
	public AbscenceDto() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Boolean getJustified() {
		return justified;
	}
	public void setJustified(Boolean justified) {
		this.justified = justified;
	}
	public Long getTeacher() {
		return teacher;
	}
	public void setTeacher(Long teacher) {
		this.teacher = teacher;
	}
	public Long getStudent() {
		return student;
	}
	public void setStudent(Long student) {
		this.student = student;
	}
	public Long getElement() {
		return element;
	}
	public void setElement(Long element) {
		this.element = element;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getElement_label() {
		return element_label;
	}
	public void setElement_label(String element_label) {
		this.element_label = element_label;
	}
	public String getTeacher_name() {
		return teacher_name;
	}
	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	public Long getRegistration() {
		return registration;
	}
	public void setRegistration(Long registration) {
		this.registration = registration;
	}
	public Long getSession() {
		return session;
	}
	public void setSession(Long session) {
		this.session = session;
	}

	public String getStartString() {
		return startString;
	}
	public void setStartString(String startString) {
		this.startString = startString;
	}
	public String getEndString() {
		return endString;
	}
	public void setEndString(String endString) {
		this.endString = endString;
	}
	@Override
	public String toString() {
		return "AbscenceDto [id=" + id + ", startString=" + startString + ", endString=" + endString + ", teacher="
				+ teacher + ", element=" + element + ", registration=" + registration + ", session=" + session + "]";
	}
	
	
	
	
}
