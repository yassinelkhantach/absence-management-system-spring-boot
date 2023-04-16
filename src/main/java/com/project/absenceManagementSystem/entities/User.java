package com.project.absenceManagementSystem.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type",discriminatorType = DiscriminatorType.STRING)
public abstract class User {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
    private String firstNameAr;
    private String lastNameAr;
    private String phone;
    private String email;
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
	private Account account;
    
    public User(String firstName, String lastName, String firstNameAr, String lastNameAr, String phone, String email,
			Account account) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.firstNameAr = firstNameAr;
		this.lastNameAr = lastNameAr;
		this.phone = phone;
		this.email = email;
		this.account = account;
	}
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
}
