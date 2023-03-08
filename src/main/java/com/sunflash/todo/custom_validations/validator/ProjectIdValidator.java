package com.sunflash.todo.custom_validations.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.sunflash.todo.custom_validations.annotation.ProjectId;
import com.sunflash.todo.repository.ProjectRepository;

public class ProjectIdValidator implements ConstraintValidator<ProjectId, Long> {

	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public void initialize(ProjectId projectId) {
	}

	@Override
	public boolean isValid(Long projectId, ConstraintValidatorContext context) {
		if (projectId == null)
			return true;
		else {
			if (projectRepository.existsById(projectId))
				return true;
			else
				return false;
		}
	}

}
