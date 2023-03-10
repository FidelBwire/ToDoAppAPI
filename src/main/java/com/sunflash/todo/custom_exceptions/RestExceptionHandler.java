package com.sunflash.todo.custom_exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sunflash.todo.custom_exceptions.DTO.ApiError;
import com.sunflash.todo.custom_exceptions.DTO.ApiValidationError;
import com.sunflash.todo.enums.ApiResponseMessage;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	ApiResponseMessage apiResponseMessage = ApiResponseMessage.failure;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<ApiValidationError> validationErrors = new ArrayList<>();
		List<FieldError> errors = ex.getFieldErrors();
		errors.forEach(error -> {
			String object = error.getObjectName();
			String field = error.getField();
			Object rejectedValue = error.getRejectedValue();
			String message = error.getDefaultMessage();

			validationErrors.add(new ApiValidationError(object, field, rejectedValue, message));
		});

		ApiError apiError = new ApiError(status, status.value(), "Request body validation failure", ex,
				validationErrors);

		return new ResponseEntity<Object>(apiError, status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return buildResponseEntity(
				new ApiError(HttpStatus.BAD_REQUEST, status.value(), "Missing or malformed JSON request body", ex));
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiError apiError = new ApiError(status, status.value(), ex.getLocalizedMessage());
		return buildResponseEntity(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ApiError apiError = new ApiError(status, status.value(), ex.getLocalizedMessage());
		return buildResponseEntity(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiError apiError = new ApiError(status, status.value(), ex.getLocalizedMessage());
		return buildResponseEntity(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiError apiError = new ApiError(status, status.value(), ex.getLocalizedMessage());
		return buildResponseEntity(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ApiError apiError = new ApiError(status, status.value(), ex.getLocalizedMessage());
		return buildResponseEntity(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiError apiError = new ApiError(status, status.value(), ex.getLocalizedMessage());
		return buildResponseEntity(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiError apiError = new ApiError(status, status.value(), ex.getLocalizedMessage());
		return buildResponseEntity(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiError apiError = new ApiError(status, status.value(), ex.getLocalizedMessage());
		return buildResponseEntity(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiError apiError = new ApiError(status, status.value(), ex.getLocalizedMessage());
		return buildResponseEntity(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiError apiError = new ApiError(status, status.value(), ex.getLocalizedMessage());
		return buildResponseEntity(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		ApiError apiError = new ApiError(status, status.value(), ex.getLocalizedMessage());
		return buildResponseEntity(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ApiError apiError = new ApiError(status, status.value(), ex.getLocalizedMessage());
		return buildResponseEntity(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex,
			HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
		ApiError apiError = new ApiError(status, status.value(), ex.getLocalizedMessage());
		return buildResponseEntity(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ApiError apiError = new ApiError(status, status.value(), ex.getLocalizedMessage());
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(value = JwtTokenMissingException.class)
	public ResponseEntity<Object> handleJwtTokenMissingException(JwtTokenMissingException exception) {
		ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value(),
				"Missing JWT authentication token");
		return new ResponseEntity<Object>(apiError, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException exception) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
		apiError.setMessage(exception.getMessage());
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(value = InvalidUserCredentialsException.class)
	public ResponseEntity<Object> handleInvalidUserCredentialsException(InvalidUserCredentialsException exception) {
		HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
		ApiError apiError = new ApiError(httpStatus, httpStatus.value(), exception.getLocalizedMessage());
		return buildResponseEntity(apiError);
	}

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

}
