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
import jakarta.persistence.Table;

@Entity
@Table(name = "levels")
public class Level {
	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String label;
	private String alias;
	@ManyToOne
    @JoinColumn(name = "filiere_id", referencedColumnName = "id")
    private Filiere filiere;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "level")
	private List<Module> modules;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "level")
	private List<Registration> registrations;

	
	
	
	public Level(String label, String alias, Filiere filiere, List<Module> modules) {
		super();
		this.label = label;
		this.alias = alias;
		this.filiere = filiere;
		this.modules = modules;
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
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public List<Registration> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(List<Registration> registrations) {
		this.registrations = registrations;
	}
	
	
	
}
