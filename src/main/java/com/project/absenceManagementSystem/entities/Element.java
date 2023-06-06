package com.project.absenceManagementSystem.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "elements")
@NoArgsConstructor
public class Element {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String label;
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "module_id", referencedColumnName = "id")
	private Module module;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "element")
	private List<Absence> absences;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
	private Teacher teacher;
	
	public Element() {
		
	}
	public Element(String label, Module module) {
		super();
		this.label = label;
		this.module = module;
	}
	 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	public List<Absence> getAbsences() {
		return absences;
	}
	public void setAbsences(List<Absence> absences) {
		this.absences = absences;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	@Override
	public String toString() {
		return "Element [id=" + id + ", label=" + label + ", module=" + module + ", absences=" + absences + ", teacher="
				+ teacher + "]";
	}
	
	

}
