package com.project.absenceManagementSystem.entities;

import java.util.Date;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
@DiscriminatorValue(value = "Student")
public class Student extends User{
	
	private String cne;
    private String cin;
    private String picture;
    private Date birthDate;
    
    public Student(String firstName, String lastName, String firstNameAr, String lastNameAr, String phone,
			String email, Account account, String cne, String cin, String picture, Date birthDate) {
		super(firstName, lastName, firstNameAr, lastNameAr, phone, email, account);
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
    
    

}