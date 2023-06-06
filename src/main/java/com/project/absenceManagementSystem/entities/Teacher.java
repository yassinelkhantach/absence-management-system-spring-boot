package com.project.absenceManagementSystem.entities;

import java.util.Collection;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "teachers")
@DiscriminatorValue(value = "Teacher")
public class Teacher extends User{
	
	private String cin;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "teacher")
	private List<Absence> absences;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "teacher")
	private List<AuthorizationRequest> authorizationRequests;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "teacher")
	private List<Coordination> coordinations;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "teacher")
	private List<Element> elements;

    
	
	
	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Teacher(String firstName, String lastName, String firstNameAr, String lastNameAr, String phone, String email,
			Account account,  Collection<Role> roles) {
		super(firstName, lastName, firstNameAr, lastNameAr, phone, email, account,roles);
		// TODO Auto-generated constructor stub
	}

	public Teacher(String firstName, String lastName, String firstNameAr, String lastNameAr, String phone, String email,
			Account account, String cin,  Collection<Role> roles) {
		super(firstName, lastName, firstNameAr, lastNameAr, phone, email, account, roles);
		this.setCin(cin);
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public List<Absence> getAbsences() {
		return absences;
	}

	public void setAbsences(List<Absence> absences) {
		this.absences = absences;
	}

	public List<AuthorizationRequest> getAuthorizationRequests() {
		return authorizationRequests;
	}

	public void setAuthorizationRequests(List<AuthorizationRequest> authorizationRequests) {
		this.authorizationRequests = authorizationRequests;
	}

	public List<Coordination> getCoordinations() {
		return coordinations;
	}

	public void setCoordinations(List<Coordination> coordinations) {
		this.coordinations = coordinations;
	}

	public List<Element> getElements() {
		return elements;
	}

	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

	
	@Override
	public String toString() {
		return "Teacher [cin=" + cin + ", absences=" + absences + ", authorizationRequests=" + authorizationRequests
				+ ", coordinations=" + coordinations + ", elements=" + elements + "]";
	}

	
	
	

	
}
