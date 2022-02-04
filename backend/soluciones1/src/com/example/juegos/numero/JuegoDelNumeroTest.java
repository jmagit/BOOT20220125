package com.example.juegos.numero;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import com.example.juegos.JuegoException;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class JuegoDelNumeroTest {
	JuegoDelNumero juego;
	
	@BeforeEach
	void setUp() throws Exception {
		juego = new JuegoDelNumero();
		// juego.numeroBuscado = 50;
		ponNumeroBuscado(50);

	}

	private void ponNumeroBuscado(int value) throws NoSuchFieldException, IllegalAccessException {
		Field cmd = juego.getClass().getDeclaredField("numeroBuscado");
		cmd.setAccessible(true);
		cmd.set(juego, value);
	}
	private int dameNumeroBuscado() throws NoSuchFieldException, IllegalAccessException {
		Field cmd = juego.getClass().getDeclaredField("numeroBuscado");
		cmd.setAccessible(true);
		return (int)cmd.get(juego);
	}

	@Test
	void test_Constructor() {
		assertNotNull(new JuegoDelNumero());
	}

	@Test
	void test_Inicializar() throws NoSuchFieldException, IllegalAccessException {
		//juego.numeroBuscado = -1;
		ponNumeroBuscado(-1);
		juego.inicializar();
		assertAll("Inicializar",
				() -> assertEquals("Pendiente de empezar", juego.getResultado()),
				() -> assertEquals(0, juego.getJugada()),
				() -> assertTrue(0 < dameNumeroBuscado() && dameNumeroBuscado() <= 100, "rango entre 1 y 100")
				);
	}
	
	@Nested
	class jugadas {
		@Test
		void test_es_mayor() throws JuegoException {
			juego.jugada(51);
			assertAll("Jugada",
				() -> assertEquals("Mi número es menor.", juego.getResultado()),
				() -> assertFalse(juego.getFinalizado()),
				() -> assertEquals(1, juego.getJugada())
			);
		}
		@Test
		void test_es_menor() throws JuegoException {
			juego.jugada(49);
			assertAll("Jugada",
				() -> assertEquals("Mi número es mayor.", juego.getResultado()),
				() -> assertFalse(juego.getFinalizado()),
				() -> assertEquals(1, juego.getJugada())
			);
		}
		@Test
		void test_es_igual() throws JuegoException {
			juego.jugada(50);
			assertAll("Jugada",
				() -> assertEquals("Bieeen!!! Acertaste.", juego.getResultado()),
				() -> assertTrue(juego.getFinalizado()),
				() -> assertEquals(1, juego.getJugada())
			);
		}
		
		private int intento;
		@Test
		void test_no_acierta() throws JuegoException {
			for(intento = 1; intento < 10; intento++) {
				juego.jugada(1);
				assertAll("Intentos",
						() -> assertFalse(juego.getFinalizado()),
						() -> assertEquals(intento, juego.getJugada())
					);
			}
			juego.jugada(1);
			assertAll("Ultima",
				() -> assertEquals("Upsss! Se acabaron los intentos, el número era el 50", juego.getResultado()),
				() -> assertTrue(juego.getFinalizado()),
				() -> assertEquals(10, juego.getJugada())
			);
		}
		
		@Test
		void test_ha_terminado() throws JuegoException {
			juego.jugada(50);
			assertTrue(juego.getFinalizado());
			assertThrows(JuegoException.class, () -> juego.jugada(50));
//			try {
//				juego.jugada(50);
//				fail("No lanza la exepcion");
//			} catch (JuegoException e) {
//				// Bien, ha lanzado la excepcion
//			} catch (Exception e) {
//				// Mal: ha lanzado otra excepción
//				fail("No lanza la exepcion");
//			}
		}
	}

	@Nested
	class jugada_como_cadena {
		@ParameterizedTest(name = "{displayName} => {0}")
		@ValueSource(strings = {"1", "100"})
		void Validas(String numero) {
			assertDoesNotThrow(() -> juego.jugada(numero));
		}
		@ParameterizedTest(name = "{displayName} => {0}")
		@ValueSource(strings = {"k","1O", "l0"})
		@NullAndEmptySource
		void Invalidas(String numero) {
			assertThrows(JuegoException.class, () -> juego.jugada(numero));
		}
	}
}
