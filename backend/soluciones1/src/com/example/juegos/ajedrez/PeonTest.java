package com.example.juegos.ajedrez;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.example.juegos.Color;
import com.example.juegos.JuegoException;
import com.example.juegos.Movimiento;
import com.example.juegos.PiezaTest.PiezaMock;
import com.example.juegos.Tablero;

class PeonTest {
	Tablero tablero;
	PromocionEvent promociona;

	@BeforeEach
	void setUp() throws Exception {
		tablero = new Tablero();
		promociona = arg -> new PiezaMock(arg.getColor(), 1, "");
		
		tablero.setPieza(3,  1, new Peon(Color.BLANCO, promociona));
		tablero.setPieza(3,  2, new Peon(Color.NEGRO, promociona));
		tablero.setPieza(2,  2, new Peon(Color.BLANCO, promociona));
		tablero.setPieza(2,  3, new Peon(Color.BLANCO, promociona));
		
		tablero.setPieza(6,  8, new Peon(Color.NEGRO, promociona));
		tablero.setPieza(6,  7, new Peon(Color.BLANCO, promociona));
		tablero.setPieza(7,  7, new Peon(Color.NEGRO, promociona));
		tablero.setPieza(7,  6, new Peon(Color.NEGRO, promociona));
	}

	@Test
	void testConstructorInvalido() {
		assertThrows(AssertionError.class, ()-> new Peon(Color.BLANCO, null));
	}

	@ParameterizedTest(name = "Movimiento: {0}")
	@ValueSource(strings = {"A3A4","C2B3","C2C3","C2C4","H6H5","F7G6","F7F6","F7F5"})
	void testValido(String cadena) throws JuegoException {
		Movimiento m = new Movimiento(cadena);
		assertEquals("", tablero.getPieza(m.getPosicionInicial()).getError(m, tablero));
	}
	
	@ParameterizedTest(name = "Movimiento: {0}")
	@ValueSource(strings = {"A3A5","A3B4","B2B3","B2B4","A3A2","C2C5","C2E5"})
	void testInvalido(String cadena) throws JuegoException {
		Movimiento m = new Movimiento(cadena);
		assertThrows(JuegoException.class, ()-> tablero.getPieza(m.getPosicionInicial()).mover(m, tablero));
	}
	
	@Nested
	class Promocionar {
		@Test
		void EnVertical() throws JuegoException {
			var tablero = new Tablero();
			var pieza = new Torre(Color.BLANCO);		
			var peon = new Peon(Color.BLANCO, arg -> pieza);
			tablero.setPieza(7,  2, peon);
			
			peon.mover(new Movimiento("B7B8"), tablero);
			
			assertFalse(tablero.hayPieza(7, 2));
			assertSame(pieza, tablero.getPieza(8, 2));
		}
		@Test
		void Comiendo() throws JuegoException {
			var tablero = new Tablero();
			var pieza = new Torre(Color.BLANCO);		
			var peon = new Peon(Color.BLANCO, arg -> pieza);
			tablero.setPieza(7,  2, peon);
			tablero.setPieza(8,  1, new Peon(Color.NEGRO, arg -> pieza));
			
			peon.mover(new Movimiento("B7A8"), tablero);
			
			assertFalse(tablero.hayPieza(7, 2));
			assertSame(pieza, tablero.getPieza(8, 1));
		}
		@Test
		void Cancelar() throws JuegoException {
			var tablero = new Tablero();
			var pieza = new Torre(Color.BLANCO);		
			var peon = new Peon(Color.BLANCO, arg -> { arg.setCancel(true); return pieza; });
			tablero.setPieza(7,  2, peon);
			
			assertThrows(JuegoException.class, ()-> peon.mover(new Movimiento("B7B8"), tablero));
		}
		@Test
		void SinPieza() throws JuegoException {
			var tablero = new Tablero();
			var peon = new Peon(Color.BLANCO, arg -> null);
			tablero.setPieza(7,  2, peon);
			
			assertThrows(JuegoException.class, ()-> peon.mover(new Movimiento("B7B8"), tablero));
		}
		@Test
		void OtroColor() throws JuegoException {
			var tablero = new Tablero();
			var pieza = new Torre(Color.NEGRO);		
			var peon = new Peon(Color.BLANCO, arg -> pieza);
			tablero.setPieza(7,  2, peon);
			
			assertThrows(JuegoException.class, ()-> peon.mover(new Movimiento("B7B8"), tablero));
		}
		@Test
		void PiezaInvalida() throws JuegoException {
			var tablero = new Tablero();
			var pieza = new Rey(Color.BLANCO);		
			var peon = new Peon(Color.BLANCO, arg -> pieza);
			tablero.setPieza(7,  2, peon);
			
			assertThrows(JuegoException.class, ()-> peon.mover(new Movimiento("B7B8"), tablero));
		}
	}
	
	@Test
	void testInvalidoPorNulos() throws JuegoException {
		var p = new Peon(Color.BLANCO, promociona);
		assertAll("Nulos", 
				() -> assertThrows(IllegalArgumentException.class, ()-> p.getError(null, tablero), "Movimiento"),
				() -> assertThrows(IllegalArgumentException.class, ()-> p.getError(new Movimiento("A1A2"), null), "Tablero")
				);
	}
	
	@Test
	void testMoverConNulos() throws JuegoException {
		var p = new Peon(Color.BLANCO, promociona);
		assertAll("Nulos", 
				() -> assertThrows(IllegalArgumentException.class, ()-> p.mover(null, tablero), "Movimiento"),
				() -> assertThrows(IllegalArgumentException.class, ()-> p.mover(new Movimiento("A1H8"), null), "Tablero")
				);
	}

}
