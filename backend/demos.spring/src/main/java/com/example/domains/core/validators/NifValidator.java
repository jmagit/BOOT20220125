package com.example.domains.core.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NifValidator implements ConstraintValidator<NIF, String>  {
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null) return true;
		value = value.toUpperCase();
		if(!value.matches("^\\d{1,8}[A-Z]$")) return false;
		return "TRWAGMYFPDXBNJZSQVHLCKE".charAt(Integer.parseInt( value.substring(0, value.length() - 1)) % 23) 
				== value.charAt(value.length() - 1);
	}
}
