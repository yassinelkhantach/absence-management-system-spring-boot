package com.project.absenceManagementSystem.entities;

import java.util.Collection;
import java.util.Date;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@Entity
@Table(name = "users",uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type",discriminatorType = DiscriminatorType.STRING)
public class User {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
    private String firstNameAr;
	private String lastNameAr;
    private String phone;
    private String email;
    private String picture;
    private Date deletedAt = null;
    private Date createdAt = new Date();
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
	private Account account;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(
		            name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
				            name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles;
    
    public User() {
		super();
	}

	public User(String firstName, String lastName, String firstNameAr, String lastNameAr, String phone, String email,
			Account account, Collection<Role> roles) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.firstNameAr = firstNameAr;
		this.lastNameAr = lastNameAr;
		this.phone = phone;
		this.email = email;
		this.account = account;
		this.roles = roles;
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
	public Date getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", deletedAt=" + deletedAt + "]";
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public Role getRole() {
		return roles.iterator().next();
	}
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
	public String getFullName() {
		return this.firstName+" "+this.getLastName();
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
	
}
