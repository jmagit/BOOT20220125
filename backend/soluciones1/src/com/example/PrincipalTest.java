package com.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PrincipalTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		var app = new Principal();
		
		assertEquals("Hola mundo", app.kk("mundo"));
		assertEquals("Hola Pedro", app.kk("Pedro"));
		assertThrows(IllegalArgumentException.class, () -> app.kk(null));
	}

}
