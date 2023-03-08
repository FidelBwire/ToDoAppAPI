package com.sunflash.todo.custom_validations.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.sunflash.todo.custom_validations.validator.TaskStatusValidator;

@Documented
@Constraint(validatedBy = TaskStatusValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TaskStatus {
	String message() default "Invalid task status";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}