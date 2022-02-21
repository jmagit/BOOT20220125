package com.example.juegos.ajedrez;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.juegos.Color;
import com.example.juegos.JuegoException;

class AjedrezTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testInicializar() {
		var j = new Ajedrez(arg -> null);
		assertAll("Inicializa", 
				() -> assertEquals(Color.BLANCO, j.getTurno()), 
				() -> assertEquals(0, j.getJugada()),
				() -> assertNotSame(j.getTablero(), j.getResultado()),
				() -> assertTrue(j.getTablero().equals(j.getResultado())),
				() -> assertFalse(j.getFinalizado())
			);
	}

	@Test
	void testJugada() throws JuegoException {
		var j = new Ajedrez(arg -> null);
		j.jugada("E2E4"); assertEquals(Color.NEGRO, j.getTurno());
		j.jugada("E7E5"); assertEquals(Color.BLANCO, j.getTurno());
		j.jugada("F1C4"); assertEquals(Color.NEGRO, j.getTurno());
		j.jugada("B8C6"); assertEquals(Color.BLANCO, j.getTurno());
		j.jugada("D1H5"); assertEquals(Color.NEGRO, j.getTurno());
		j.jugada("FIN"); // Jaque mate
		assertTrue(j.getFinalizado());
	}

	@Test
	void testSinPiezaInicial() throws JuegoException {
		var j = new Ajedrez(arg -> null);
		assertThrows(JuegoException.class, () -> j.jugada("D4D5"));
	}

	@Test
	void testComerPropia() throws JuegoException {
		var j = new Ajedrez(arg -> null);
		assertThrows(JuegoException.class, () -> j.jugada("A1A2"));
	}

	@Test
	void testMoverContraria() throws JuegoException {
		var j = new Ajedrez(arg -> null);
		assertThrows(JuegoException.class, () -> j.jugada("E7E5"));
	}


	@Test
	void testFinalizar() throws JuegoException {
		var j = new Ajedrez(arg -> null);
		
		j.jugada("tablas"); 
		assertTrue(j.getFinalizado());
		assertThrows(JuegoException.class, () -> j.jugada("E7E5"));
	}

}
