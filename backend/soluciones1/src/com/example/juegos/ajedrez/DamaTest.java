package com.example.juegos.ajedrez;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.example.juegos.Color;
import com.example.juegos.JuegoException;
import com.example.juegos.Movimiento;
import com.example.juegos.Tablero;

class DamaTest {
	Tablero tablero;

	@BeforeEach
	void setUp() throws Exception {
		tablero = new Tablero();
		tablero.setPieza(5,  4, new Dama(Color.BLANCO));
		tablero.setPieza(5,  5, new Dama(Color.NEGRO));
		tablero.setPieza(7,  3, new Dama(Color.BLANCO));
		tablero.setPieza(7,  7, new Dama(Color.BLANCO));
		tablero.setPieza(2,  2, new Dama(Color.BLANCO));
		tablero.setPieza(3,  7, new Dama(Color.BLANCO));
	}

	@ParameterizedTest(name = "Movimiento: {0}")
	@ValueSource(strings = {"D5A8","D5G8","D5H1","D5A2", "E5C7", "E5G7", "E5B2", "E5G3", "D5D8","D5D1","D5E5","D5A5", })
	void testValido(String cadena) throws JuegoException {
		Movimiento m = new Movimiento(cadena);
		assertEquals("", tablero.getPieza(m.getPosicionInicial()).getError(m, tablero));
	}
	
	@ParameterizedTest(name = "Movimiento: {0}")
	@ValueSource(strings = {"D5F2", "E5B8", "E5H8", "E5A1", "E5H2","D5H5"})
	void testInvalido(String cadena) throws JuegoException {
		Movimiento m = new Movimiento(cadena);
		assertThrows(JuegoException.class, ()-> tablero.getPieza(m.getPosicionInicial()).mover(m, tablero));
	}
	
	@Test
	void testInvalidoPorNulos() throws JuegoException {
		var p = new Dama(Color.BLANCO);
		assertAll("Nulos", 
				() -> assertThrows(IllegalArgumentException.class, ()-> p.getError(null, tablero), "Movimiento"),
				() -> assertThrows(IllegalArgumentException.class, ()-> p.getError(new Movimiento("A1A2"), null), "Tablero")
				);
	}
}
