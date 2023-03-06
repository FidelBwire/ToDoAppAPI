package com.sunflash.todo.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.sunflash.todo.config.JwtUtil;
import com.sunflash.todo.custom_exceptions.JwtTokenMissingException;
import com.sunflash.todo.service.UserAuthService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserAuthService userAuthService;

	@Autowired
	@Qualifier("handlerExceptionResolver")
	private HandlerExceptionResolver resolver;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader("Authorization");

		if (header == null || !header.startsWith("HTTP_TOKEN")) {
			throw new JwtTokenMissingException("No JWT token found in the request headers");
		}
		String token = header.substring("HTTP_TOKEN".length() + 1);
		jwtUtil.validateToken(token);
		String userName = jwtUtil.getUserName(token);

		UserDetails userDetails = userAuthService.loadUserByUsername(userName);
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				userDetails, null, userDetails.getAuthorities());
		usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}

		try {
			filterChain.doFilter(request, response);
		} catch (Exception e) {
			resolver.resolveException(request, response, null, e);
		}
	}
}
