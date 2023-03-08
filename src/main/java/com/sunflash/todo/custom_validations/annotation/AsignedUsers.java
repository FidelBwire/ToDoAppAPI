package com.sunflash.todo.custom_validations.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.sunflash.todo.custom_validations.validator.AsignedUsersValidator;

@Documented
@Constraint(validatedBy = AsignedUsersValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AsignedUsers {
	String message() default "Invalid project Id";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}