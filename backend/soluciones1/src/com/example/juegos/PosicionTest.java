package com.example.juegos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PosicionTest {

	@ParameterizedTest(name = "{displayName} => ({0}, {1})")
	@CsvSource(value = {"1,8","8,1"})
	void testPosicionIntValida(int fila, int columna) throws JuegoException {
		var posicion = new Posicion(fila, columna);
		assertNotNull(posicion);
		assertAll("Posicion", 
				() -> assertEquals(fila, posicion.getFila(), "fila"),
				() -> assertEquals(columna, posicion.getColumna(), "columna")
				);
	}
	@ParameterizedTest(name = "{displayName} => ({0}, {1})")
	@CsvSource(value = {"0,8","9,1","1,0","8,9"})
	void testPosicionIntInvalida(int fila, int columna) throws JuegoException {
		assertThrows(JuegoException.class, () -> new Posicion(fila, columna));
	}
	@ParameterizedTest(name = "{displayName} => ({0}, {1})")
	@CsvSource(value = {"1,A,1,1","8,H,8,8"})
	void testPosicionCharValida(char fila, char columna, int filaRslt, int columnaRslt) throws JuegoException {
		var posicion = new Posicion(fila, columna);
		assertNotNull(posicion);
		assertAll("Posicion", 
				() -> assertEquals(filaRslt, posicion.getFila(), "fila"),
				() -> assertEquals(columnaRslt, posicion.getColumna(), "columna")
				);
	}
	@ParameterizedTest(name = "{displayName} => ({0}, {1})")
	@CsvSource(value = {"0,A","9,H","1,a","8,M","2, ' '"})
	void testPosicionCharInvalida(char fila, char columna) throws JuegoException {
		assertThrows(JuegoException.class, () -> new Posicion(fila, columna));
	}


}
