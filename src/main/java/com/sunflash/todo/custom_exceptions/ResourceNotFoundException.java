package com.sunflash.todo.custom_exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {
		this("Resource not found");
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
