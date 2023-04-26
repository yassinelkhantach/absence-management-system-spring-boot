package com.project.absenceManagementSystem.dto;

public class UserRegistrationDto {
	private String firstName;
	private String lastName;
	private String firstNameAr;
	private String lastNameAr;
	private String phone;
	private String email;
	private String password;
	private String role;
	private String cne;
	private String cin;
	private String birthdate;
	private String grade;
	
	public UserRegistrationDto(){
		
	}
	
	public UserRegistrationDto(String firstName, String lastName, String firstNameAr, String lastNameAr, String phone,
			String email, String password, String role, String cne, String cin, String birthdate, String grade) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.firstNameAr = firstNameAr;
		this.lastNameAr = lastNameAr;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.role = role;
		this.cne = cne;
		this.cin = cin;
		this.birthdate = birthdate;
		this.grade = grade;
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
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstNameAr() {
		return firstNameAr;
	}

	public void setFirstNameAr(String firstNameAr) {
		this.firstNameAr = firstNameAr;
	}

	public String getLastNameAr() {
		return lastNameAr;
	}

	public void setLastNameAr(String lastNameAr) {
		this.lastNameAr = lastNameAr;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	
	
	
}