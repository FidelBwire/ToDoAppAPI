package com.sunflash.todo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunflash.todo.DTO.request.SignUpRequest;
import com.sunflash.todo.config.JwtUtil;
import com.sunflash.todo.custom_exceptions.InvalidUserCredentialsException;
import com.sunflash.todo.enums.UserRole;
import com.sunflash.todo.model.User;
import com.sunflash.todo.model.UserRoles;
import com.sunflash.todo.repository.UserRepository;

@Service
public class UserAuthService implements UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user;
		try {
			user = userRepository.findByEmail(username).get();
		} catch (NoSuchElementException e) {
			throw new InvalidUserCredentialsException("Invalid user credentials");
		}

		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		user.getRoles().forEach(role -> {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole().toString()));
		});

		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), grantedAuthorities);
	}

	public void saveUser(SignUpRequest request) {
		User user = new User();
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setEmail(request.getEmail());
		user.setUsername(request.getUsername());
		user.setFirstName(request.getFirstName());
		if (request.getLastName() != null) {
			user.setLastName(request.getLastName());
		}
		if (request.getPhoneNumber() != null) {
			user.setPhoneNumber(request.getPhoneNumber());
		}

		Set<UserRoles> userRoles = new HashSet<>(Arrays.asList(new UserRoles(UserRole.ROLE_USER)));
		user.setRoles(userRoles);

		userRepository.save(user);
	}

	public User parseUserFromRequest(HttpServletRequest httpServletRequest) {
		String header = httpServletRequest.getHeader("Authorization");
		String token = header.substring("HTTP_TOKEN".length() + 1);
		String username = jwtUtil.getUserName(token);
		User user = userRepository.findByEmail(username).orElse(null);
		return user;
	}

//	public boolean checkforDuplication(String username, String phoneNumber, String email) {
//		boolean duplicateUsername = userRepository.findByUsername(username).isPresent();
//		boolean duplicatePhone = userProfileRepository.findByPhoneNumber(phoneNumber).isPresent();
//		boolean duplicateEmail = userRepository.findByEmail(email).isPresent();
//		String message = " ";
//
//		if (duplicateUsername)
//			message += " username";
//		if (duplicatePhone) {
//			if (duplicateUsername)
//				message += ", phone number";
//			else
//				message += "phone number";
//		}
//
//		if (duplicateEmail) {
//			if (duplicateUsername || duplicatePhone)
//				message += ", email";
//			else
//				message += "email";
//		}
//
//		if (duplicateUsername || duplicatePhone || duplicateEmail) {
//			message = (message != null) ? "Duplicate " + message.trim() + " not allowed" : "";
//			throw new DuplicationException(message);
//		} else {
//			return false;
//		}
//	}
}
