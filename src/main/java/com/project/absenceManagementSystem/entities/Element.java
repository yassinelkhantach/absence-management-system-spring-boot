package com.project.absenceManagementSystem.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "elements")
public class Element {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String label;
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "module_id", referencedColumnName = "id")
	private Module module;
	
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
	

}
