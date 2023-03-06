package com.sunflash.todo.DTO.request;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SignUpRequest {

	@Size(min = 4, max = 8, message = "Password must be between 6 to 10 characters")
	@NotNull(message = "Password must not be null")
	private String password;

	@NotNull(message = "Email must not be null")
	@Email(message = "Invalid email format")
	private String email;

	@Size(min = 4, max = 8, message = "Username must be between 4 to 8 characters")
	@NotNull(message = "Username must not be null")
	private String username;

	@Size(min = 1, max = 20, message = "First name must be 1-20 characters")
	@NotNull(message = "First name must not be null")
	private String firstName;

	@Size(min = 1, max = 20, message = "Last name must be 1-20 characters")
	private String lastName;

	@Size(min = 10, message = "Phone number must either be null or more than 10 characters")
	private String phoneNumber;

	@NotNull(message = "Roles must not be null")
	private List<String> roles;

	public SignUpRequest() {
		super();
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

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "SignUpRequest [password=" + password + ", email=" + email + ", username=" + username + ", firstName="
				+ firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", roles=" + roles + "]";
	}

}
