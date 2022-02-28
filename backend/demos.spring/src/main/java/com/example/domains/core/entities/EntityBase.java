package com.example.domains.core.entities;

import java.util.Set;

import javax.persistence.Transient;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class EntityBase<E> {
	
	@Transient
	@JsonIgnore
	public Set<ConstraintViolation<E>> getErrors() {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		return validator.validate((E)this);
	}
	
	@Transient
	@JsonIgnore
	public String getErrorsMessage() {
		if(isValid()) return "";
		StringBuilder sb = new StringBuilder("ERRORES: ");
		getErrors().forEach(item -> sb.append(item.getPropertyPath() + ": " + item.getMessage() + ". "));
		return sb.toString().trim();
	}
	
	@Transient
	@JsonIgnore
	public boolean isValid() {
		return getErrors().size() == 0;
	}

	@Transient
	@JsonIgnore
	public boolean isInvalid() {
		return !isValid();
	}

}
