package com.project.absenceManagementSystem.entities;

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
public class Module {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String codeModule;
	private String label;
	@ManyToOne
    @JoinColumn(name = "level_id", referencedColumnName = "id")
	private Level level;
	@OneToMany(mappedBy = "module",cascade = CascadeType.ALL)
	private List<Element> elements;
	
	
	public Module(String codeModule, String label, Level level, List<Element> elements) {
		super();
		this.codeModule = codeModule;
		this.label = label;
		this.level = level;
		this.elements = elements;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodeModule() {
		return codeModule;
	}
	public void setCodeModule(String codeModule) {
		this.codeModule = codeModule;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	public List<Element> getElements() {
		return elements;
	}
	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

	
	
	
}
