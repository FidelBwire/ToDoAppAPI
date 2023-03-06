package com.sunflash.todo.auth_entry_point;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunflash.todo.custom_exceptions.DTO.ApiError;

@Component
public class ApiAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AuthenticationException e) throws IOException, ServletException {
		ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value(),
				"Missing JWT authentication token");
		httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
		OutputStream out = httpServletResponse.getOutputStream();
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(out, apiError);
		out.flush();
	}

}
