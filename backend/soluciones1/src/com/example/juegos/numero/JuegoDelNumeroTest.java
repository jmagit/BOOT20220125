package com.example.juegos.numero;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.example.juegos.JuegoException;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class JuegoDelNumeroTest {
	JuegoDelNumero juego;
	
	@BeforeEach
	void setUp() throws Exception {
		juego = new JuegoDelNumero();
		juego.numeroBuscado = 50;
	}

	@Test
	void test_Constructor() {
		assertNotNull(new JuegoDelNumero());
	}

	@Test
	void test_Inicializar() {
		juego.numeroBuscado = -1;
		juego.inicializar();
		assertAll("Inicializar",
				() -> assertEquals("Pendiente de empezar", juego.getResultado()),
				() -> assertEquals(0, juego.getJugada()),
				() -> assertTrue(0 < juego.numeroBuscado && juego.numeroBuscado <= 100, "rango entre 1 y 100")
				);
	}
	
	@Nested
	class jugadas {
		@Test
		void test_es_mayor() throws JuegoException {
			juego.jugada(51);
			assertAll("Jugada",
				() -> assertEquals("Mi número es menor.", juego.getResultado()),
				() -> assertEquals(1, juego.getJugada())
			);
		}
		@Test
		void test_es_menor() throws JuegoException {
			juego.jugada(49);
			assertAll("Jugada",
				() -> assertEquals("Mi número es mayor.", juego.getResultado()),
				() -> assertEquals(1, juego.getJugada())
			);
		}
		@Test
		void test_es_igual() throws JuegoException {
			juego.jugada(50);
			assertAll("Jugada",
				() -> assertEquals("Bieeen!!! Acertaste.", juego.getResultado()),
				() -> assertEquals(1, juego.getJugada())
			);
		}
	}

//	@Test
//	void testJugadaString() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testJugadaInt() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetResultado() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetFinalizado() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetJugada() {
//		fail("Not yet implemented");
//	}
//
}
