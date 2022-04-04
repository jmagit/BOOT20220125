package com.example.domains.core.validators;

import static org.junit.jupiter.api.Assertions.*;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Pattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class PasswordTest {
	class EntityMock {
		@Pattern(regexp =  "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{8,32}$")
		String password;

		public EntityMock(String password) {
			super();
			this.password = password;
		}
	}

	@ParameterizedTest(name = "Caso: {0}")
	@ValueSource(strings = {"A dele 1","2+2=Cuatro","1234@aAAa","(Passw0rd)", "Pa$$w0rd"})
	@NullSource
	void casosAnotadosValidos(String caso) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		assertEquals(0, validator.validate(new EntityMock(caso)).size());
	}

	@ParameterizedTest(name = "Caso: {0}")
	@ValueSource(strings = {"12345678","12345aAAa","abcdefghi", "ABCDEFGHIJK","|@#$%&/()="})
	void casosAnotadosInvalidos(String caso) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		assertEquals(1, validator.validate(new EntityMock(caso)).size());
		validator.validate(new EntityMock(caso)).forEach(item -> assertTrue(item.getMessage().startsWith("debe coincidir con ")));
	}

}
