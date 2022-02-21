package com.example.juegos.ajedrez;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.example.juegos.Color;
import com.example.juegos.JuegoException;
import com.example.juegos.Movimiento;
import com.example.juegos.Tablero;

class TorreTest {
	Tablero tablero;

	@BeforeEach
	void setUp() throws Exception {
		tablero = new Tablero();
		tablero.setPieza(4,  3, new Torre(Color.BLANCO));
		tablero.setPieza(5,  5, new Torre(Color.NEGRO));
		tablero.setPieza(7,  5, new Torre(Color.BLANCO));
		tablero.setPieza(5,  2, new Torre(Color.BLANCO));
		tablero.setPieza(5,  7, new Torre(Color.BLANCO));
		tablero.setPieza(3,  5, new Torre(Color.BLANCO));
	}

	@ParameterizedTest(name = "Movimiento: {0}")
	@ValueSource(strings = {"C4C8","C4C1","C4H4","C4A4"})
	void testValido(String cadena) throws JuegoException {
		Movimiento m = new Movimiento(cadena);
		assertEquals("", tablero.getPieza(m.getPosicionInicial()).getError(m, tablero));
	}
	
	@ParameterizedTest(name = "Movimiento: {0}")
	@ValueSource(strings = {"E5E8","E5E1","E5A5","E5H5", "E5H8", "E5B8"})
	void testInvalido(String cadena) throws JuegoException {
		Movimiento m = new Movimiento(cadena);
		assertThrows(JuegoException.class, ()-> tablero.getPieza(m.getPosicionInicial()).mover(m, tablero));
	}
	
	@Test
	void testInvalidoPorNulos() throws JuegoException {
		var p = new Torre(Color.BLANCO);
		assertAll("Nulos", 
				() -> assertThrows(IllegalArgumentException.class, ()-> p.getError(null, tablero), "Movimiento"),
				() -> assertThrows(IllegalArgumentException.class, ()-> p.getError(new Movimiento("A1A2"), null), "Tablero")
				);
	}
}
