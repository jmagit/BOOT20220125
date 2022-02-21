package com.example.juegos.ajedrez;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.example.juegos.Color;
import com.example.juegos.JuegoException;
import com.example.juegos.Movimiento;
import com.example.juegos.Tablero;

class ReyTest {
	Tablero tablero;

	@BeforeEach
	void setUp() throws Exception {
		tablero = new Tablero();
		tablero.setPieza(5,  4, new Rey(Color.BLANCO));
	}
	
	private static  Stream<? extends Arguments> generaCasosValidos() {
    	var movimientos = new ArrayList<Arguments>();
		for (char f = '4'; f <= '6'; f++) 
			for (char c = 'C'; c <= 'E'; c++)
				if(!"D5".equals("" + c + f))
					movimientos.add(Arguments.of("D5" + c + f));
        return movimientos.stream();
    }
	
	@ParameterizedTest(name = "Movimiento: {0}")
	@MethodSource("generaCasosValidos")
	void testValido(String cadena) throws JuegoException {
		Movimiento m = new Movimiento(cadena);
		assertEquals("", tablero.getPieza(m.getPosicionInicial()).getError(m, tablero));
	}
	
	private static  Stream<? extends Arguments> generaCasosInvalidos() {
    	var movimientos = new ArrayList<Arguments>();
		for (char c = 'B'; c <= 'F'; c++) {
			movimientos.add(Arguments.of("D5" + c + '7'));
			movimientos.add(Arguments.of("D5" + c + '3'));
		}
		for (char f = '4'; f <= '6'; f++) {
			movimientos.add(Arguments.of("D5B" + f));
			movimientos.add(Arguments.of("D5F" + f));
		}
        return movimientos.stream();
    }
	@ParameterizedTest(name = "Movimiento: {0}")
	@MethodSource("generaCasosInvalidos")
	void testInvalido(String cadena) throws JuegoException {
		Movimiento m = new Movimiento(cadena);
		assertThrows(JuegoException.class, ()-> tablero.getPieza(m.getPosicionInicial()).mover(m, tablero));
	}
	
	@Test
	void testInvalidoPorNulos() throws JuegoException {
		var p = new Rey(Color.BLANCO);
		assertAll("Nulos", 
				() -> assertThrows(IllegalArgumentException.class, ()-> p.getError(null, tablero), "Movimiento"),
				() -> assertThrows(IllegalArgumentException.class, ()-> p.getError(new Movimiento("A1A2"), null), "Tablero")
				);
	}
	
	
}
