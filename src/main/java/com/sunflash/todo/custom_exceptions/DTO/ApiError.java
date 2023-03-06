package com.sunflash.todo.custom_exceptions.DTO;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;

public class ApiError {
	private HttpStatus status;
	private int code;
//	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	private String message;
	private Object details;

	public ApiError() {
	}

	public ApiError(HttpStatus status) {
		super();
		this.status = status;
	}

	public ApiError(HttpStatus status, int code, String message) {
		super();
		this.status = status;
		this.code = code;
		this.message = message;
	}

	public ApiError(HttpStatus status, int code, String message, Object details) {
		super();
		this.status = status;
		this.code = code;
		this.message = message;
		this.details = details;
	}

	public ApiError(HttpStatus status, int code, String message, Throwable ex) {
		this();
		this.status = status;
		this.code = code;
		this.message = message;
	}

	public ApiError(HttpStatus status, int code, String message, Throwable ex, Object details) {
		this();
		this.status = status;
		this.code = code;
		this.message = message;
		this.details = details;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getDetails() {
		return details;
	}

	public void setDetails(Object details) {
		this.details = details;
	}

}
