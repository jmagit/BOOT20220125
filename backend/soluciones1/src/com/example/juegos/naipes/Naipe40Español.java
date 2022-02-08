package com.example.juegos.naipes;

import com.example.juegos.JuegoException;

public class Naipe40Español extends NaipeEspañol {
	public static final int CARTASxPALO = 10;
	public static final String[] LITERALES = { "Comodin", "As", "Dos", "Tres", 
			"Cuatro", "Cinco", "Seis", "Siete", "Sota", "Caballo", "Rey" };

	public Naipe40Español(NaipeEspañol.Palos palo, byte valor) throws JuegoException {
		super(palo, validaValor(valor));
	}

	private static byte validaValor(byte valor) throws JuegoException {
		if (valor < 0 || valor > 10)
			throw new JuegoException("El valor debe estar 1 y 10");
		return valor;
	}

	@Override
	protected String[] getLiterales() {
		return LITERALES;
	}
}
