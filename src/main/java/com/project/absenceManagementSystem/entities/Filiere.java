package com.project.absenceManagementSystem.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "filieres")
public class Filiere {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String label;
	private String alias;
	private Date accreditationStart;
	private Date accreditationEnd;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "filiere")
	private List<Level> levels;
	
	
	
	public Filiere(String label, String alias, Date accreditationStart, Date accreditationEnd, List<Level> levels) {
		super();
		this.label = label;
		this.alias = alias;
		this.accreditationStart = accreditationStart;
		this.accreditationEnd = accreditationEnd;
		this.levels = levels;
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
	public Date getAccreditationStart() {
		return accreditationStart;
	}
	public void setAccreditationStart(Date accreditationStart) {
		this.accreditationStart = accreditationStart;
	}
	public Date getAccreditationEnd() {
		return accreditationEnd;
	}
	public void setAccreditationEnd(Date accreditationEnd) {
		this.accreditationEnd = accreditationEnd;
	}
	public List<Level> getLevels() {
		return levels;
	}
	public void setLevels(List<Level> levels) {
		this.levels = levels;
	}
	
	
	
	
	
}
