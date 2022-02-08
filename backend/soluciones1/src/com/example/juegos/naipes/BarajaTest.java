package com.example.juegos.naipes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import com.example.juegos.JuegoException;

class BarajaTest {
	byte indice;
	@Nested
	class BarajaFrancesaTest {
		@Test
		void create() {
			var baraja = new BarajaFrancesa();
			assertNotNull(baraja);
			var cartas = baraja.getCartas();
			assertEquals(52, cartas.length);
			int delta = 0;
			for (NaipeFrances.Palos palo : NaipeFrances.Palos.values()) {
				for (indice = 0; indice < NaipeFrances.CARTASxPALO; indice++) {
					var carta = cartas[delta * NaipeFrances.CARTASxPALO + indice];
					assertNotNull(carta);
					assertAll("naipe",
							() -> assertEquals(palo, carta.getPalo()),
							() -> assertEquals(indice + 1, carta.getValor())
							);
				}
				delta++;
			}
		}

		@RepeatedTest(value = 5, name = "{displayName} {currentRepetition}/{totalRepetitions}")
		void barajarPorPorcentaje() throws JuegoException {
			barajarPorPorcentajeTest(new BarajaFrancesa());
		}

	}
	@Nested
	class Baraja40Espa�olaTest {

		@Test
		void create() {
			var baraja = new Baraja40Espa�ola();
			assertNotNull(baraja);
			var cartas = baraja.getCartas();
			assertEquals(40, cartas.length);
			int delta = 0;
			for (NaipeEspa�ol.Palos palo : NaipeEspa�ol.Palos.values()) {
				for (indice = 0; indice < Naipe40Espa�ol.CARTASxPALO; indice++) {
					var carta = cartas[delta * Naipe40Espa�ol.CARTASxPALO + indice];
					assertNotNull(carta);
					assertAll("naipe",
							() -> assertEquals(palo, carta.getPalo()),
							() -> assertEquals(indice + 1, carta.getValor())
							);
				}
				delta++;
			}
		}

		@RepeatedTest(value = 5, name = "{displayName} {currentRepetition}/{totalRepetitions}")
		void barajarPorPorcentaje() throws JuegoException {
			barajarPorPorcentajeTest(new Baraja40Espa�ola());
		}

	}
	@Nested
	class Baraja48Espa�olaTest {
		@Test
		void create() {
			var baraja = new Baraja48Espa�ola();
			assertNotNull(baraja);
			var cartas = baraja.getCartas();
			assertEquals(48, cartas.length);
			int delta = 0;
			for (NaipeEspa�ol.Palos palo : NaipeEspa�ol.Palos.values()) {
				for (indice = 0; indice < Naipe48Espa�ol.CARTASxPALO; indice++) {
					var carta = cartas[delta * Naipe48Espa�ol.CARTASxPALO + indice];
					assertNotNull(carta);
					assertAll("naipe",
							() -> assertEquals(palo, carta.getPalo()),
							() -> assertEquals(indice + 1, carta.getValor())
							);
				}
				delta++;
			}
		}

		@RepeatedTest(value = 5, name = "{displayName} {currentRepetition}/{totalRepetitions}")
		void barajarPorPorcentaje() throws JuegoException {
			barajarPorPorcentajeTest(new Baraja48Espa�ola());
		}
	}
	
	private void barajarPorPorcentajeTest(Baraja baraja) throws JuegoException {
		final int MAX_COINCIDENCIAS = 5;
		var cartas = baraja.getCartas();
		baraja.barajar();
		var mazo = baraja.getMazo();
		int coincidencias = 0;
		for (indice = 0; indice < cartas.length; indice++) {
			if(cartas[indice].equals(mazo.get(indice)))
				coincidencias++;
		}
		assertTrue(coincidencias <= MAX_COINCIDENCIAS, coincidencias + " coincidencias superan el m�ximo de " + MAX_COINCIDENCIAS );
	}

	//	@Test
//	void testGetMazo() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testIsQuedanCartas() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testReparte() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testApilar() {
//		fail("Not yet implemented");
//	}

}
