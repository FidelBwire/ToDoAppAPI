package com.sunflash.todo.DTO.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SignInRequest {

	@Email(message = "Invalid email format")
	@NotNull(message = "Email must not be null")
	private String email;

	@Size(min = 4, max = 8, message = "Password must be between 6 to 10 characters")
	@NotNull(message = "Password must not be null")
	private String password;

	public SignInRequest() {
		super();
	}

	public SignInRequest(
			@Size(min = 4, max = 8, message = "Username must be between 4 to 8 characters") @NotNull(message = "Username must not be null") String email,
			@Size(min = 4, max = 8, message = "Password must be between 6 to 10 characters") @NotNull(message = "Password must not be null") String password) {
		super();
		this.email = email;
		this.password = password;
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

	@Override
	public String toString() {
		return "SignInRequest [email=" + email + ", password=" + password + "]";
	}

}
