package com.sunflash.todo.custom_validations.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.sunflash.todo.custom_validations.annotation.TaskStatus;

public class TaskStatusValidator implements ConstraintValidator<TaskStatus, String> {

	@Override
	public void initialize(TaskStatus taskStatus) {
	}

	@Override
	public boolean isValid(String taskStatus, ConstraintValidatorContext context) {
		if (taskStatus == null)
			return false;
		if (taskStatus.equalsIgnoreCase("NOT_STARTED") || taskStatus.equalsIgnoreCase("STARTED")
				|| taskStatus.equalsIgnoreCase("IN_PROGRESS") || taskStatus.equalsIgnoreCase("PAUSED")
				|| taskStatus.equalsIgnoreCase("RESUMED") || taskStatus.equalsIgnoreCase("ABORTED")
				|| taskStatus.equalsIgnoreCase("COMPLETED") || taskStatus.equalsIgnoreCase("FAILED"))
			return true;
		else
			return false;
	}

}
