package com.example.juegos.naipes;

import com.example.juegos.JuegoException;

public class Naipe48Espa�ol extends NaipeEspa�ol {
	public static final int CARTASxPALO = 12;
	public static final String[] LITERALES = { "Comodin", "As", "Dos", "Tres", 
			"Cuatro", "Cinco", "Seis", "Siete", "Ocho", "Nueve", "Sota", 
			"Caballo", "Rey" };

	public Naipe48Espa�ol(NaipeEspa�ol.Palos palo, byte valor) throws JuegoException {
		super(palo, validaValor(valor));
	}

	private static byte validaValor(byte valor) throws JuegoException {
		if (valor < 0 || valor > 12)
			throw new JuegoException("El valor debe estar 1 y 12");
		return valor;
	}

	@Override
	protected String[] getLiterales() {
		return LITERALES;
	}
}
