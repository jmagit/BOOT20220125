package com.example.juegos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class MovimientoTest {

	@Nested
	class Constructores {

		@ParameterizedTest(name = "({0}, {1}) -> ({2}, {3})")
		@CsvSource(value = { "1,8,8,1", "2,2,2,1" })
		void testMovimientoPorPosicionValida(int filaIni, int columnaIni, int filaFin, int columnaFin)
				throws JuegoException {
			var posicionIni = new Posicion(filaIni, columnaIni);
			var posicionFin = new Posicion(filaFin, columnaFin);
			var movimiento = new Movimiento(posicionIni, posicionFin);
			assertNotNull(movimiento);
			assertAll("Movimiento", () -> assertEquals(posicionIni, movimiento.getPosicionInicial(), "inicial"),
					() -> assertEquals(posicionFin, movimiento.getPosicionFinal(), "final"));
		}

		@ParameterizedTest(name = "({0}, {1}) -> ({2}, {3})")
		@CsvSource(value = { "1,1,1,1" })
		void testMovimientoPorPosicionInvalida(int filaIni, int columnaIni, int filaFin, int columnaFin)
				throws JuegoException {
			var posicionIni = new Posicion(filaIni, columnaIni);
			var posicionFin = new Posicion(filaFin, columnaFin);
			assertThrows(JuegoException.class, () -> new Movimiento(posicionIni, posicionFin));
		}

		@Test
		void testMovimientoPorPosicionNula() throws JuegoException {
			assertAll("Movimiento",
					() -> assertThrows(JuegoException.class, () -> new Movimiento(null, new Posicion(1, 1)),
							"Inicial nula"),
					() -> assertThrows(JuegoException.class, () -> new Movimiento(new Posicion(1, 1), null),
							"Final nula"));
		}

		@ParameterizedTest(name = "Valor: {0}")
		@CsvSource(value = { "A1H8,1,1,8,8", "C5C6,5,3,6,3", "D7E7,7,4,7,5" })
		void testMovimientoPorCadenaValida(String cadena, int filaIni, int columnaIni, int filaFin, int columnaFin)
				throws JuegoException {
			var posicionIni = new Posicion(filaIni, columnaIni);
			var posicionFin = new Posicion(filaFin, columnaFin);
			var movimiento = new Movimiento(cadena);
			assertNotNull(movimiento);
			assertAll("Movimiento", () -> assertEquals(posicionIni, movimiento.getPosicionInicial(), "inicial"),
					() -> assertEquals(posicionFin, movimiento.getPosicionFinal(), "final"));
		}

		@ParameterizedTest(name = "Valor: {0}")
		@ValueSource(strings = { "HOLA", "1A8H", "1a8h", "A1H", "C5C6A", "C5C5" })
		@NullAndEmptySource
		void testMovimientoPorCadenaInvalida(String cadena) {
			assertThrows(JuegoException.class, () -> new Movimiento(cadena));
		}
	}

	@Nested
	class EsVertical {
		@ParameterizedTest(name = "Movimiento: {0}")
		@ValueSource(strings = { "A1A8","D5D4" })
		void testDevuelveTrue(String cadena) throws JuegoException {
			assertTrue((new Movimiento(cadena)).esVertical());
		}
		@ParameterizedTest(name = "Movimiento: {0}")
		@ValueSource(strings = { "A1H8","C7D6" })
		void testDevuelveFalse(String cadena) throws JuegoException {
			assertFalse((new Movimiento(cadena)).esVertical());
		}
	}

	@Nested
	class EsHorizontal {
		@ParameterizedTest(name = "Movimiento: {0}")
		@ValueSource(strings = { "A1H1","D8C8" })
		void testDevuelveTrue(String cadena) throws JuegoException {
			assertTrue((new Movimiento(cadena)).esHorizontal());
		}
		@ParameterizedTest(name = "Movimiento: {0}")
		@ValueSource(strings = { "A1H8","D5D4" })
		void testDevuelveFalse(String cadena) throws JuegoException {
			assertFalse((new Movimiento(cadena)).esHorizontal());
		}
	}

	@Nested
	class EsDiagonal {
		@ParameterizedTest(name = "Movimiento: {0}")
		@ValueSource(strings = { "A1H8","D5C4" })
		void testDevuelveTrue(String cadena) throws JuegoException {
			assertTrue((new Movimiento(cadena)).esDiagonal());
		}
		@ParameterizedTest(name = "Movimiento: {0}")
		@ValueSource(strings = { "A1A8","B7D6" })
		void testDevuelveFalse(String cadena) throws JuegoException {
			assertFalse((new Movimiento(cadena)).esDiagonal());
		}
	}

	@ParameterizedTest(name = "Movimiento: {0} -> {1}")
	@CsvSource(value = { "A1H8,7", "C6C5,1", "D7E7,0" })
	void testSaltoVertical(String movimiento, int resultado) throws JuegoException {
		assertEquals(resultado, (new Movimiento(movimiento)).saltoVertical());
	}

	@ParameterizedTest(name = "Movimiento: {0} -> {1}")
	@CsvSource(value = { "A1H8,7", "C6C5,0", "E7D7,1" })
	void testSaltoHorizontal(String movimiento, int resultado) throws JuegoException {
		assertEquals(resultado, (new Movimiento(movimiento)).saltoHorizontal());
	}
	@ParameterizedTest(name = "Movimiento: {0} -> {1}")
	@CsvSource(value = { "A1H8,1", "C6C5,-1", "D7E7,0" })
	void testDeltaVertical(String movimiento, int resultado) throws JuegoException {
		assertEquals(resultado, (new Movimiento(movimiento)).deltaVertical());
	}
	@ParameterizedTest(name = "Movimiento: {0} -> {1}")
	@CsvSource(value = { "A1H8,1", "C6C5,0", "E7D7,-1" })
	void testDeltaHorizontal(String movimiento, int resultado) throws JuegoException {
		assertEquals(resultado, (new Movimiento(movimiento)).deltaHorizontal());
	}

}
