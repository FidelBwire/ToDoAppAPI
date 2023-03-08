package com.sunflash.todo.controller;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunflash.todo.DTO.request.SignInRequest;
import com.sunflash.todo.DTO.request.SignUpRequest;
import com.sunflash.todo.DTO.response.SignInResponse;
import com.sunflash.todo.config.JwtUtil;
import com.sunflash.todo.custom_exceptions.DisabledUserException;
import com.sunflash.todo.custom_exceptions.InvalidUserCredentialsException;
import com.sunflash.todo.service.UserAuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserAuthService userAuthService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/signin")
	public ResponseEntity<SignInResponse> signin(@Valid @RequestBody SignInRequest request) {
		Authentication authentication = null;
		try {
			authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		} catch (DisabledException e) {
			throw new DisabledUserException("User Inactive");
		} catch (BadCredentialsException e) {
			throw new InvalidUserCredentialsException("Invalid Credentials");
		}

		User user = (User) authentication.getPrincipal();
		Set<String> roles = user.getAuthorities().stream().map(r -> r.getAuthority()).collect(Collectors.toSet());

		String token = jwtUtil.generateToken(authentication);

		SignInResponse response = new SignInResponse();
		response.setToken(token);
		response.setRoles(roles.stream().collect(Collectors.toList()));

		return new ResponseEntity<SignInResponse>(response, HttpStatus.OK);
	}

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@Valid @RequestBody SignUpRequest request) {
		userAuthService.saveUser(request);
		return new ResponseEntity("User successfully registered", HttpStatus.OK);
	}

}