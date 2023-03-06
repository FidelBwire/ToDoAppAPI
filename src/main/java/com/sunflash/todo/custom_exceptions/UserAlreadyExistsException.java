package com.sunflash.todo.custom_exceptions;

public class UserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException(String message) {
		super(message);
	}
}
