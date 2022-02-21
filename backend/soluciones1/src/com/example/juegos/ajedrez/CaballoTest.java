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

class CaballoTest {
	Tablero tablero;

	@BeforeEach
	void setUp() throws Exception {
		tablero = new Tablero();
		tablero.setPieza(5,  4, new Caballo(Color.BLANCO));
		tablero.setPieza(3,  7, new Caballo(Color.NEGRO));
	}

	@ParameterizedTest(name = "Movimiento: {0}")
	@ValueSource(strings = {"D5B6","D5C7","D5E7","D5F6", "D5F4","D5E3","D5C3","D5B4"})
	void testValido(String cadena) throws JuegoException {
		Movimiento m = new Movimiento(cadena);
		assertEquals("", tablero.getPieza(m.getPosicionInicial()).getError(m, tablero));
	}
	
	@ParameterizedTest(name = "Movimiento: {0}")
	@ValueSource(strings = {"D5A5","D5D8","D5F2", "D5B8"})
	void testInvalido(String cadena) throws JuegoException {
		Movimiento m = new Movimiento(cadena);
		assertThrows(JuegoException.class, ()-> tablero.getPieza(m.getPosicionInicial()).mover(m, tablero));
	}
	
	@Test
	void testInvalidoPorNulos() throws JuegoException {
		var p = new Caballo(Color.BLANCO);
		assertAll("Nulos", 
				() -> assertThrows(IllegalArgumentException.class, ()-> p.getError(null, tablero), "Movimiento"),
				() -> assertThrows(IllegalArgumentException.class, ()-> p.getError(new Movimiento("A1A2"), null), "Tablero")
				);
	}
}
