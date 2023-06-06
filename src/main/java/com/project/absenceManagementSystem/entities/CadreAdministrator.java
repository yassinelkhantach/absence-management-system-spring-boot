package com.project.absenceManagementSystem.entities;

import java.util.Collection;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "cadreAdministrators")
@DiscriminatorValue(value = "CadreAdministrator")
public class CadreAdministrator extends User{
	
    private String grade;
    public CadreAdministrator() {
    	
    }
    
	public CadreAdministrator(String firstName, String lastName, String firstNameAr, String lastNameAr, String phone,
			String email, Account account, String grade,  Collection<Role> roles) {
		super(firstName, lastName, firstNameAr, lastNameAr, phone, email, account,roles);
		this.grade = grade;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

}
