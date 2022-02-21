package com.example.juegos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.example.juegos.PiezaTest.PiezaMock;
import com.example.juegos.ajedrez.Alfil;
import com.example.juegos.ajedrez.Caballo;
import com.example.juegos.ajedrez.Dama;
import com.example.juegos.ajedrez.Peon;
import com.example.juegos.ajedrez.Rey;
import com.example.juegos.ajedrez.Torre;

class TableroTest {
	Tablero tablero;

	@BeforeEach
	void setUp() throws Exception {
		tablero = new Tablero();

		tablero.setPieza(1, 1, new PiezaMock(Color.BLANCO, 11));
		tablero.setPieza(1, 2, new PiezaMock(Color.BLANCO, 12));
		tablero.setPieza(1, 3, new PiezaMock(Color.BLANCO, 13));
		tablero.setPieza(1, 4, new PiezaMock(Color.BLANCO, 14));
		tablero.setPieza(1, 5, new PiezaMock(Color.BLANCO, 15));
		tablero.setPieza(1, 6, new PiezaMock(Color.BLANCO, 16));
		tablero.setPieza(1, 7, new PiezaMock(Color.BLANCO, 17));
		tablero.setPieza(1, 8, new PiezaMock(Color.BLANCO, 18));

		tablero.setPieza(8, 1, new PiezaMock(Color.NEGRO, 81));
		tablero.setPieza(8, 2, new PiezaMock(Color.NEGRO, 82));
		tablero.setPieza(8, 3, new PiezaMock(Color.NEGRO, 83));
		tablero.setPieza(8, 4, new PiezaMock(Color.NEGRO, 84));
		tablero.setPieza(8, 5, new PiezaMock(Color.NEGRO, 85));
		tablero.setPieza(8, 6, new PiezaMock(Color.NEGRO, 86));
		tablero.setPieza(8, 7, new PiezaMock(Color.NEGRO, 87));
		tablero.setPieza(8, 8, new PiezaMock(Color.NEGRO, 88));

		for (byte c = 1; c <= 8; c++) {
			tablero.setPieza(2, c, new PiezaMock(Color.BLANCO, 20 + c));
			tablero.setPieza(7, c, new PiezaMock(Color.NEGRO, 70 + c));
		}
//		tablero.setPieza(3, 3, new PiezaMock(Color.BLANCO, 33));
	}

	@Test
	void testConstructor() {
		assertNotNull(new Tablero());
	}

	@Nested
	class HayPieza {
		@Test
		void testFilaColumnaDevuelveTrue() throws JuegoException {
			for (byte c = 1; c <= 8; c++) {
				assertTrue(tablero.hayPieza(1, c), "Fila: 1 Columna: " + c);
				assertTrue(tablero.hayPieza(2, c), "Fila: 2 Columna: " + c);
				assertTrue(tablero.hayPieza(7, c), "Fila: 3 Columna: " + c);
				assertTrue(tablero.hayPieza(8, c), "Fila: 4 Columna: " + c);
			}
		}

		@Test
		void testFilaColumnaDevuelveFalse() throws JuegoException {
			for (byte c = 1; c <= 8; c++)
				for (byte f = 3; f <= 6; f++)
					assertFalse(tablero.hayPieza(f, c), "Fila: " + f + " Columna: " + c);
		}

		@ParameterizedTest(name = "({0}, {1})")
		@CsvSource(value = { "0,8", "9,1", "1,0", "8,9" })
		void testFueraDelTablero(int fila, int columna) throws JuegoException {
			assertThrows(IndexOutOfBoundsException.class, () -> tablero.hayPieza(fila, columna));
		}

		@Test
		void testPosicionDevuelveTrue() throws JuegoException {
			for (byte c = 1; c <= 8; c++) {
				assertTrue(tablero.hayPieza(new Posicion(1, c)), "Fila: 1 Columna: " + c);
				assertTrue(tablero.hayPieza(new Posicion(2, c)), "Fila: 2 Columna: " + c);
				assertTrue(tablero.hayPieza(new Posicion(7, c)), "Fila: 3 Columna: " + c);
				assertTrue(tablero.hayPieza(new Posicion(8, c)), "Fila: 4 Columna: " + c);
			}
		}

		@Test
		void testPosicionDevuelveFalse() throws JuegoException {
			for (byte c = 1; c <= 8; c++)
				for (byte f = 3; f <= 6; f++)
					assertFalse(tablero.hayPieza(new Posicion(f, c)), "Fila: " + f + " Columna: " + c);
		}

		@Test
		void testPosicionNula() throws JuegoException {
			assertThrows(IllegalArgumentException.class, () -> tablero.hayPieza(null));
		}
	}

	@Nested
	class GetPieza {
		@Test
		void testFilaColumnaValido() throws JuegoException {
			for (c = 1; c <= 8; c++) {
				assertEquals(10 + c, ((PiezaMock) tablero.getPieza(1, c)).id, "Fila: 1 Columna: " + c);
				assertEquals(20 + c, ((PiezaMock) tablero.getPieza(2, c)).id, "Fila: 2 Columna: " + c);
				assertEquals(70 + c, ((PiezaMock) tablero.getPieza(7, c)).id, "Fila: 7 Columna: " + c);
				assertEquals(80 + c, ((PiezaMock) tablero.getPieza(8, c)).id, "Fila: 8 Columna: " + c);
			}
		}

		byte c, f;

		@Test
		void testFilaColumnaInvalido() throws JuegoException {
			for (c = 1; c <= 8; c++)
				for (f = 3; f <= 6; f++)
					assertThrows(NoSuchElementException.class, () -> tablero.getPieza(f, c),
							"Fila: " + f + " Columna: " + c);
		}

		@ParameterizedTest(name = "({0}, {1})")
		@CsvSource(value = { "0,8", "9,1", "1,0", "8,9" })
		void testFueraDelTablero(int fila, int columna) throws JuegoException {
			assertThrows(IndexOutOfBoundsException.class, () -> tablero.getPieza(fila, columna));
			;
		}

		@Test
		void testPosicionValido() throws JuegoException {
			for (c = 1; c <= 8; c++) {
				assertEquals(10 + c, ((PiezaMock) tablero.getPieza(new Posicion(1, c))).id, "Fila: 1 Columna: " + c);
				assertEquals(20 + c, ((PiezaMock) tablero.getPieza(new Posicion(2, c))).id, "Fila: 2 Columna: " + c);
				assertEquals(70 + c, ((PiezaMock) tablero.getPieza(new Posicion(7, c))).id, "Fila: 7 Columna: " + c);
				assertEquals(80 + c, ((PiezaMock) tablero.getPieza(new Posicion(8, c))).id, "Fila: 8 Columna: " + c);
			}
		}

		@Test
		void testPosicionInvalido() throws JuegoException {
			for (c = 1; c <= 8; c++)
				for (f = 3; f <= 6; f++)
					assertThrows(NoSuchElementException.class, () -> tablero.getPieza(new Posicion(f, c)),
							"Fila: " + f + " Columna: " + c);
		}

		@Test
		void testPosicionNula() throws JuegoException {
			assertThrows(IllegalArgumentException.class, () -> tablero.getPieza(null));
		}
	}

	@Nested
	class SetPieza {
		byte c, f;

		@Test
		void testFilaColumnaValido() throws JuegoException {
			for (byte c = 1; c <= 8; c++)
				for (byte f = 1; f <= 8; f++) {
					var p = new PiezaMock(Color.BLANCO, c * 10 + f);
					tablero.setPieza(f, c, p);
					assertSame(p, tablero.getPieza(f, c));
				}
		}

		@Test
		void testFilaColumnaNulo() throws JuegoException {
			assertThrows(AssertionError.class, () -> tablero.setPieza(1, 1, null));
		}

		@ParameterizedTest(name = "({0}, {1})")
		@CsvSource(value = { "0,8", "9,1", "1,0", "8,9" })
		void testFueraDelTablero(int fila, int columna) throws JuegoException {
			assertThrows(IndexOutOfBoundsException.class,
					() -> tablero.setPieza(fila, columna, new PiezaMock(Color.NEGRO, 0)));
			;
		}

		@Test
		void testPosicionValido() throws JuegoException {
			for (byte c = 1; c <= 8; c++)
				for (byte f = 1; f <= 8; f++) {
					var p = new PiezaMock(Color.BLANCO, c * 10 + f);
					tablero.setPieza(new Posicion(f, c), p);
					assertSame(p, tablero.getPieza(f, c));
				}
		}

		@Test
		void testPosicionInvalido() throws JuegoException {
			assertThrows(JuegoException.class, () -> tablero.setPieza(new Posicion(0, 0), null));
		}

		@Test
		void testPosicionNula() throws JuegoException {
			assertThrows(IllegalArgumentException.class, () -> tablero.setPieza(null, new PiezaMock(Color.NEGRO, 0)));
		}
	}

	@Nested
	class QuitaPieza {
		byte c, f;

		@Test
		void testFilaColumnaValido() throws JuegoException {
			for (c = 1; c <= 8; c++) {
				tablero.quitaPieza(1, c);
				assertFalse(tablero.hayPieza(1, c), "Fila: 1 Columna: " + c);
				tablero.quitaPieza(2, c);
				assertFalse(tablero.hayPieza(2, c), "Fila: 2 Columna: " + c);
				tablero.quitaPieza(7, c);
				assertFalse(tablero.hayPieza(7, c), "Fila: 7 Columna: " + c);
				tablero.quitaPieza(8, c);
				assertFalse(tablero.hayPieza(8, c), "Fila: 8 Columna: " + c);
			}
		}

		@Test
		void testFilaColumnaInvalido() throws JuegoException {
			for (c = 1; c <= 8; c++)
				for (f = 3; f <= 6; f++)
					assertThrows(NoSuchElementException.class, () -> tablero.quitaPieza(f, c),
							"Fila: " + f + " Columna: " + c);
		}

		@ParameterizedTest(name = "({0}, {1})")
		@CsvSource(value = { "0,8", "9,1", "1,0", "8,9" })
		void testFueraDelTablero(int fila, int columna) throws JuegoException {
			assertThrows(IndexOutOfBoundsException.class, () -> tablero.quitaPieza(fila, columna));
		}

		@Test
		void testPosicionValido() throws JuegoException {
			for (c = 1; c <= 8; c++) {
				tablero.quitaPieza(new Posicion(1, c));
				assertFalse(tablero.hayPieza(1, c), "Fila: 1 Columna: " + c);
				tablero.quitaPieza(new Posicion(2, c));
				assertFalse(tablero.hayPieza(2, c), "Fila: 2 Columna: " + c);
				tablero.quitaPieza(new Posicion(7, c));
				assertFalse(tablero.hayPieza(7, c), "Fila: 7 Columna: " + c);
				tablero.quitaPieza(new Posicion(8, c));
				assertFalse(tablero.hayPieza(8, c), "Fila: 8 Columna: " + c);
			}
		}

		@Test
		void testPosicionInvalido() throws JuegoException {
			for (c = 1; c <= 8; c++)
				for (f = 3; f <= 6; f++)
					assertThrows(NoSuchElementException.class, () -> tablero.quitaPieza(new Posicion(f, c)),
							"Fila: " + f + " Columna: " + c);
		}

		@Test
		void testPosicionNula() throws JuegoException {
			assertThrows(IllegalArgumentException.class, () -> tablero.quitaPieza(null));
		}
	}

	@Nested
	class Mover {
		@ParameterizedTest(name = "Movimiento: ({0}, {1}) -> ({2}, {3})")
		@CsvSource(value = { "1,1,8,8", "8,1,1,8", "2,1,2,2","7,4,4,4" })
		void testValido(int filaIni, int columnaIni, int filaFin, int columnaFin) throws JuegoException {
			var posicionIni = new Posicion(filaIni, columnaIni);
			var posicionFin = new Posicion(filaFin, columnaFin);
			var pieza = tablero.getPieza(posicionIni);
			
			tablero.mover(new Movimiento(posicionIni, posicionFin));
			
			assertFalse(tablero.hayPieza(posicionIni));
			assertTrue(tablero.hayPieza(posicionFin));
			assertSame(pieza, tablero.getPieza(posicionFin));
		}

		@ParameterizedTest(name = "Movimiento: ({0}, {1}) -> ({2}, {3})")
		@CsvSource(value = { "3,3,8,8" })
		void testInvalido(int filaIni, int columnaIni, int filaFin, int columnaFin) throws JuegoException {
			assertThrows(JuegoException.class, () -> tablero.mover(new Movimiento(new Posicion(filaIni, columnaIni), new Posicion(filaFin, columnaFin))));
		}
		@Test
		void testMovimientoNulo() throws JuegoException {
			assertThrows(IllegalArgumentException.class, () -> tablero.mover(null));
		}
	}

	@Nested
	class HayPiezaEntre {
		@BeforeEach
		void setUp() throws Exception {
			tablero.quitaPieza(8, 5);
			tablero.quitaPieza(8, 6);
		}
		@ParameterizedTest(name = "Movimiento: ({0}, {1}) -> ({2}, {3})")
		@CsvSource(value = { "1,1,8,8", "8,1,1,8", "2,2,8,2", "2,7,8,1", "2,1,2,3","7,8,7,6" })
		void testDevuelveTrue(int filaIni, int columnaIni, int filaFin, int columnaFin) throws JuegoException {
			assertTrue(tablero.hayPiezaEntre(new Movimiento(new Posicion(filaIni, columnaIni), new Posicion(filaFin, columnaFin))));
		}

		@ParameterizedTest(name = "Movimiento: ({0}, {1}) -> ({2}, {3})")
		@CsvSource(value = { "2,2,7,7","7,2,2,7","2,4,7,4","8,4,8,7","8,7,8,5","2,2,3,3","2,1,2,2","2,2,2,1" })
		void testDevuelveFalse(int filaIni, int columnaIni, int filaFin, int columnaFin) throws JuegoException {
			assertFalse(tablero.hayPiezaEntre(new Movimiento(new Posicion(filaIni, columnaIni), new Posicion(filaFin, columnaFin))));
		}
		@ParameterizedTest(name = "Movimiento: ({0}, {1}) -> ({2}, {3})")
		@CsvSource(value = { "2,2,7,4" })
		void testInvalido(int filaIni, int columnaIni, int filaFin, int columnaFin) throws JuegoException {
			assertThrows(JuegoException.class, () -> tablero.hayPiezaEntre(new Movimiento(new Posicion(filaIni, columnaIni), new Posicion(filaFin, columnaFin))));
		}
		@Test
		void testMovimientoNulo() throws JuegoException {
			assertThrows(IllegalArgumentException.class, () -> tablero.hayPiezaEntre(null));
		}
	}

	@Test
	void testColorEscaque() {
		Color colorFila = Color.NEGRO;
		for (byte c = 1; c <= 8; c++) {
			Color color = colorFila;
			for (byte f = 1; f <= 8; f++) {
				assertEquals(color, Tablero.colorEscaque(f, c), "Fila: " + f + " Columna: " + c);
				color = color == Color.BLANCO ? Color.NEGRO : Color.BLANCO;
			}
			colorFila = colorFila == Color.BLANCO ? Color.NEGRO : Color.BLANCO;
		}
	}

	@Test
	void testClone() {
		var copia = tablero.clone();
		assertNotSame(tablero, copia);
		copia.quitaPieza(1, 1);
		assertTrue(tablero.hayPieza(1, 1));
		copia.setPieza(3, 3, new PiezaMock(Color.NEGRO, 0));
		assertFalse(tablero.hayPieza(3, 3));
	}

	@Nested
	class Buscar {
		@BeforeEach
		void setUp() throws Exception {
			tablero = new Tablero();

			tablero.setPieza(1, 1, new Torre(Color.BLANCO));
			tablero.setPieza(1, 2, new Caballo(Color.BLANCO));
			tablero.setPieza(1, 3, new Alfil(Color.BLANCO));
			tablero.setPieza(1, 4, new Dama(Color.BLANCO));
			tablero.setPieza(1, 5, new Rey(Color.BLANCO));
			tablero.setPieza(1, 6, new Alfil(Color.BLANCO));
			tablero.setPieza(1, 7, new Caballo(Color.BLANCO));
			tablero.setPieza(1, 8, new Torre(Color.BLANCO));

			tablero.setPieza(8, 1, new Torre(Color.NEGRO));
			tablero.setPieza(8, 2, new Caballo(Color.NEGRO));
			tablero.setPieza(8, 3, new Alfil(Color.NEGRO));
			tablero.setPieza(8, 4, new Dama(Color.NEGRO));
			tablero.setPieza(8, 5, new Rey(Color.NEGRO));
			tablero.setPieza(8, 6, new Alfil(Color.NEGRO));
			tablero.setPieza(8, 7, new Caballo(Color.NEGRO));
			tablero.setPieza(8, 8, new Torre(Color.NEGRO));

			for (byte c = 1; c <= 8; c++) {
				tablero.setPieza(2, c, new Peon(Color.BLANCO, arg -> null));
				tablero.setPieza(7, c, new Peon(Color.NEGRO, arg -> null));
			}
//			tablero.setPieza(3, 3, new PiezaMock(Color.BLANCO), 33);
		}

		@Test
		void testBuscarPeonesNegros() {
			var rslt = tablero.buscar(e -> e.getPieza() instanceof Peon && e.getPieza().getColor() == Color.NEGRO);
			assertEquals(8, rslt.size());
			rslt.forEach(e -> assertEquals(7, e.getFila()));
		}

		@Test
		void testBuscarReyBlanco() {
			var rslt = tablero.buscar(e -> e.getPieza() instanceof Rey && e.getPieza().getColor() == Color.BLANCO);
			assertEquals(1, rslt.size());
			assertEquals(1, rslt.get(0).getFila());
			assertEquals(5, rslt.get(0).getColumna());
			assertEquals(Color.NEGRO, Tablero.colorEscaque(1, 5));
		}
	}

}
