package com.example.domains.core.validators;

import static org.junit.jupiter.api.Assertions.*;

import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class NifValidatorTest {
	NifValidator nif;
	@BeforeEach
	void setUp() throws Exception {
		nif = new NifValidator();
	}

	@ParameterizedTest(name = "Caso: {0}")
	@ValueSource(strings = {"12345678z","12345678Z","1234S"})
	@NullSource
	void casosValidos(String caso) {
		assertTrue(nif.isValid(caso, null));
	}

	@ParameterizedTest(name = "Caso: {0}")
	@ValueSource(strings = {"12345678","Z12345678","kk"})
	@EmptySource
	void casosInvalidos(String caso) {
		assertFalse(nif.isValid(caso, null));
	}
	
	class EntityMock {
		@NIF
		String nif;

		public EntityMock(String nif) {
			super();
			this.nif = nif;
		}
	}

	@ParameterizedTest(name = "Caso: {0}")
	@ValueSource(strings = {"12345678z","12345678Z","1234S"})
	@NullSource
	void casosAnotadosValidos(String caso) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		assertEquals(0, validator.validate(new EntityMock(caso)).size());
	}

	@ParameterizedTest(name = "Caso: {0}")
	@ValueSource(strings = {"12345678"})
	void casosAnotadosInvalidos(String caso) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		assertEquals(1, validator.validate(new EntityMock(caso)).size());
		validator.validate(new EntityMock(caso)).forEach(item -> assertEquals(caso + " no es un NIF válido.", item.getMessage()));
	}

}
