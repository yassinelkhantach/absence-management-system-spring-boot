package com.project.absenceManagementSystem.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "teachers")
@Data
@NoArgsConstructor
@DiscriminatorValue(value = "Teacher")
public class Teacher extends User{
	
	private String cin;
	
	public Teacher(String firstName, String lastName, String firstNameAr, String lastNameAr, String phone, String email,
			Account account, String cin) {
		super(firstName, lastName, firstNameAr, lastNameAr, phone, email, account);
		this.setCin(cin);
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

}
