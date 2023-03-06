package com.sunflash.todo.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "Users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String firstName;

	@Column
	private String lastName;

	@Column
	private String phoneNumber;

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private Timestamp registeredAt;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<UserRoles> roles;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "createdBy")
	private List<Project> projects;

	public User() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Timestamp getRegisteredAt() {
		return registeredAt;
	}

	public void setRegisteredAt(Timestamp registeredAt) {
		this.registeredAt = registeredAt;
	}

	public Set<UserRoles> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRoles> roles) {
		this.roles = roles;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", email=" + email + ", username=" + username
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber
				+ ", registeredAt=" + registeredAt + ", roles=" + roles + ", projects=" + projects + "]";
	}

}
