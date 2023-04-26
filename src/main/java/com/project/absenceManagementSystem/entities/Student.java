package com.project.absenceManagementSystem.entities;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
@DiscriminatorValue(value = "Student")

public class Student extends User{
	
	private String cne;
    private String cin;
    private String picture;
    private Date birthDate;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "student")
	private List<Absence> absences;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "student")
	private List<AuthorizationRequest> authorizationRequests;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "student")
   	private List<Registration> registrations;
       
       
    
    
    public Student() {
		super();
	}

	public Student(String firstName, String lastName, String firstNameAr, String lastNameAr, String phone,
			String email, Account account, String cne, String cin, String picture, Date birthDate, Collection<Role> roles) {
		super(firstName, lastName, firstNameAr, lastNameAr, phone, email, account, roles);
		this.cne = cne;
		this.cin = cin;
		this.picture = picture;
		this.birthDate = birthDate;
	}

	public String getCne() {
		return cne;
	}

	public void setCne(String cne) {
		this.cne = cne;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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

	public List<Registration> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(List<Registration> registrations) {
		this.registrations = registrations;
	}
    
    

}