package com.sunflash.todo.custom_validations.validator;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.sunflash.todo.custom_validations.annotation.AsignedUsers;
import com.sunflash.todo.repository.UserRepository;

public class AsignedUsersValidator implements ConstraintValidator<AsignedUsers, Set<String>> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void initialize(AsignedUsers asignedUsers) {
	}

	@Override
	public boolean isValid(Set<String> asignedUsers, ConstraintValidatorContext context) {
		Set<String> invalidUsernames = new HashSet<>();
		asignedUsers.forEach(user -> {
			if (!userRepository.existsByUsername(user)) {
				invalidUsernames.add(user);
			}
		});

		return invalidUsernames.size() == 0;
	}

}
