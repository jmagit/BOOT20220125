package com.example.juegos.naipes;

import com.example.juegos.JuegoException;

public abstract class NaipeEspa�ol extends Naipe<NaipeEspa�ol.Palos> {
	public static enum Palos {
		OROS, COPAS, ESPADAS, BASTOS
	}

	public NaipeEspa�ol(Palos palo, byte valor) throws JuegoException {
		super(palo, valor);
	}
}