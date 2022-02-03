package com.example.basicos;

import static org.junit.Assume.assumeFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CalculadoraTest {
	Calculadora calc;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		calc = new Calculadora(0);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	@Disabled
	void testGetCurrent() throws Exception {
		String rslt = null;

		// ...
		rslt = "kk123";

		assertNotNull(rslt);
		assertEquals(5, rslt.length());
		assertTrue(true, "Asercion");
		assumeFalse(true);
		if (true)
			throw new Exception();
		// fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testCalculadora() {
		fail("Not yet implemented");
	}

	@Nested
	class Suma {
		@Nested
		class OK {
			@Test
			@Tag("smoke")
			void testSuma_Dos_Positivos() {
				var rslt = calc.suma(2, 2);
				
				assertEquals(4, rslt);
				
				assertEquals(4, calc.suma(2, 2));
			}
			@Test
			void testSuma_Positivos_Negativo() {
				assertEquals(3, calc.suma(-1, 3));
			}

			@Test
			@DisplayName("Suma dos numeros reales")
			void testSumaDoubleDouble() {
				assertEquals(0.3, calc.suma(0.1, 0.2));
			}
			
			@ParameterizedTest(name = "{displayName} => {0} + {1} = {2}")
			@CsvSource(value = {"2,2,4","-1,3,2","5,-1,4","0,0,0","-1,-1,-2","0.1,0.2,0.3"})
			void Validas(double operando1, double operando2, double resultado) {
				assertEquals(resultado, calc.suma(operando1, operando2));
			}
			
			@Test
			@DisplayName("Resta dos numeros reales")
			void testSumaDoubleDouble2() {
				assertEquals(10.11, Calculadora.suma(1, -0.9), 7);
			}
			@Test
			@Tag("smoke")
			@DisplayName("Multiple")
			void testMultiple() {
				assertAll("Persona",
						() -> assertTrue(false, "Primera"),
						() -> assertTrue(true, "Segunda"),
						() -> assertTrue(true, "Tercera"),
						() -> assertTrue(false, "Cuarta")
						);
			}

		}

		@Nested
		@Disabled
		class KO {
			@Test
			void testSumaIntInt() {
				fail("Not yet implemented");
			}

			@Test
			void testSumaDoubleDouble() {
				fail("Not yet implemented");
			}

		}
	}

	@Test
	@Disabled
	void testAvg() {
		fail("Not yet implemented");
	}
	@Nested
	class Resta {
		@Nested
		class OK {
			@ParameterizedTest(name = "{displayName} => {0} + {1} = {2}")
			@CsvSource(value = {"2,2,0","-2,2,-4"})
			void Validas(double operando1, double operando2, double resultado) {
				assertEquals(resultado, Calculadora.resta(operando1, operando2));
			}
		}
	}
	
	@Nested
	class Divide {
		@Nested
		class OK {
			@Test
			void divide_enteros_OK() {
				assertEquals(1.5, Calculadora.divide(3, 2));
			}
			@Test
			void divide_por_0_enteros() {
				//assertEquals(0, Calculadora.divide(3, 0));
				assertThrows(ArithmeticException.class, () -> Calculadora.divide(3, 0));
			}
			@Test
			void divide_con_decimales_OK() {
				assertEquals(1, Calculadora.divide(3.0, 2.0));
			}
			@Test
			void divide_por_0_con_decimales() {
				//assertEquals(Double.POSITIVE_INFINITY, Calculadora.divide(3.0, 0));
				assertThrows(ArithmeticException.class, () -> Calculadora.divide(3.0, 0));
			}
			@Test
			void probando() {
				assertEquals(1, 3.0/3);
			}
		}
	}
	
	@Test
	void excepcion() {
		assertThrows(Exception.class, () -> new Alumno(0, null, null, null));
	}
}
