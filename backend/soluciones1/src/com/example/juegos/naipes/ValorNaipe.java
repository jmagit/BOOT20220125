package com.example.juegos.naipes;

public enum ValorNaipe {
	COMODIN (0), AS (1), DOS (2), TRES (3), CUATRO (4), CINCO (5), SEIS (6), 
	SIETE (7), OCHO (8), NUEVE (9), DIEZ (10), JOTA (11), REINA (12), REY (13);

	public final int valorNumerico;
	ValorNaipe(int valorAsociado) {
		valorNumerico = valorAsociado;
	}
	public static ValorNaipe toEnum(int valor) {
		switch (valor) {
		case 0: return COMODIN;
		case 1: return AS;
		case 2: return DOS;
		case 3: return TRES;
		case 4: return CUATRO;
		case 5: return CINCO;
		case 6: return SEIS;
		case 7: return SIETE;
		case 8: return OCHO;
		case 9: return NUEVE;
		case 10: return DIEZ;
		case 11: return JOTA;
		case 12: return REINA;
		case 13: return REY;
		default:
			throw new IllegalArgumentException("No es un valor de la enumeración.");
		}
	}
}
