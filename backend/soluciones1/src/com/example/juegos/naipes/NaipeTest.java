package com.example.juegos.naipes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.example.juegos.JuegoException;

class NaipeTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Nested
	class Naipe40EspañolTest {
		@ParameterizedTest(name = "{displayName} => {0} de {1}")
		@CsvSource(value = {"OROS,1,As","COPAS,8,Sota"/*,"PICAS,2,Dos"*/})
		void Validas(NaipeEspañol.Palos palo, byte valor, String literal) throws JuegoException {
			var naipe = new Naipe40Español(palo, valor);
			assertNotNull(naipe);
			assertAll("naipe",
					() -> assertEquals(palo, naipe.getPalo()),
					() -> assertEquals(valor, naipe.getValor()),
					() -> assertEquals(literal, naipe.getLiteral())
					);
		}
		@ParameterizedTest(name = "{displayName} => {0}")
		@ValueSource(bytes = {-1, 11/*, 0*/})
		void Invalidas(byte valor) throws JuegoException {
			assertThrows(JuegoException.class, () -> new Naipe40Español(NaipeEspañol.Palos.OROS, valor));
		}

	}

}
