package com.project.absenceManagementSystem.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cadre_administrator")
@Data
@NoArgsConstructor
@DiscriminatorValue(value = "CadreAdministrator")
public class CadreAdministrator extends User{
	
    private String grade;

	public CadreAdministrator(String firstName, String lastName, String firstNameAr, String lastNameAr, String phone,
			String email, Account account, String grade) {
		super(firstName, lastName, firstNameAr, lastNameAr, phone, email, account);
		this.grade = grade;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	
    
    
    
	
}
