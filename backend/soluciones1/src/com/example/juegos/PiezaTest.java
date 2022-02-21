package com.example.juegos;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PiezaTest {
	public static class PiezaMock extends Pieza {
		public final int id;
		public final String message;

		public PiezaMock(Color color, int id) {
			super(color);
			this.id = id;
			this.message = "Por defecto es invalida";
		}
		public PiezaMock(Color color, int id, String message) {
			super(color);
			this.id = id;
			this.message = message;
		}

		@Override
		public String getError(Movimiento movimiento, Tablero tablero) {
			return message;
		}
	}

	Tablero tableroDummy;
	Movimiento movimientoDummy;

	@BeforeEach
	void setUp() throws Exception {
		tableroDummy = new Tablero();
		movimientoDummy = new Movimiento("A2A4");
	}
	
	@Test
	void testGetColor() {
		assertEquals(Color.BLANCO, (new PiezaMock(Color.BLANCO, 1)).getColor());
		assertEquals(Color.NEGRO, (new PiezaMock(Color.NEGRO, 1)).getColor());
	}
	@Test
	void testGetError() {
		assertEquals("", (new PiezaMock(Color.BLANCO, 1, "")).getError(null, null));
		assertEquals("literal", (new PiezaMock(Color.BLANCO, 1, "literal")).getError(null, null));
	}

	@Test
	void testEsValido() {
		assertTrue((new PiezaMock(Color.BLANCO, 1,"")).esValido(movimientoDummy, tableroDummy));
	}

	@Test
	void testNoEsValido() {
		assertFalse((new PiezaMock(Color.BLANCO, 1, "literal")).esValido(movimientoDummy, tableroDummy));
	}
	
	@Test
	void testEsValidoConNulos() throws JuegoException {
		var p = new PiezaMock(Color.BLANCO, 1,"");
		assertAll("Nulos", 
				() -> assertThrows(IllegalArgumentException.class, ()-> p.esValido(null, tableroDummy), "Movimiento"),
				() -> assertThrows(IllegalArgumentException.class, ()-> p.esValido(movimientoDummy, null), "Tablero")
				);
	}
	
	@Test
	void testPuedeMover() {
		var p = new PiezaMock(Color.BLANCO, 1, "");
		tableroDummy.setPieza(2, 1, p); 
		try {
			p.mover(movimientoDummy, tableroDummy);
		} catch (JuegoException e) {
			fail(e.getMessage());
		}
	}
	@Test
	void testNoPuedeMover() {
		var p = new PiezaMock(Color.BLANCO, 1, "Salta");
		tableroDummy.setPieza(2, 1, p); 
		assertThrows(JuegoException.class, () -> p.mover(movimientoDummy, tableroDummy));
	}
	
	@Test
	void testMoverConNulos() throws JuegoException {
		var p = new PiezaMock(Color.BLANCO, 1,"");
		assertAll("Nulos", 
				() -> assertThrows(IllegalArgumentException.class, ()-> p.mover(null, tableroDummy), "Movimiento"),
				() -> assertThrows(IllegalArgumentException.class, ()-> p.mover(movimientoDummy, null), "Tablero")
				);
	}

	@Test
	void testPuedeMoverConMock() throws JuegoException {
		var p = new PiezaMock(Color.BLANCO, 1, "");
		Tablero tablero = mock(Tablero.class);
		doNothing().when(tablero).mover(movimientoDummy);
		
		try {
			p.mover(movimientoDummy, tablero);
			verify(tablero).mover(movimientoDummy);
		} catch (JuegoException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testNoPuedeMoverConMock() throws JuegoException {
		var p = new PiezaMock(Color.BLANCO, 1, "invalido");
		Tablero tablero = mock(Tablero.class);
//		doNothing().when(tablero).mover(movimientoDummy);

		assertThrows(JuegoException.class, () -> p.mover(movimientoDummy, tablero));
	}

}
