package com.example.juegos.naipes;

import com.example.juegos.JuegoException;

public abstract class NaipeEspaņol extends Naipe<NaipeEspaņol.Palos> {
	public static enum Palos {
		OROS, COPAS, ESPADAS, BASTOS
	}

	public NaipeEspaņol(Palos palo, byte valor) throws JuegoException {
		super(palo, valor);
	}
}