package com.example.juegos.naipes;

import com.example.juegos.JuegoException;

public abstract class NaipeEspañol extends Naipe<NaipeEspañol.Palos> {
	public static enum Palos {
		OROS, COPAS, ESPADAS, BASTOS
	}

	public NaipeEspañol(Palos palo, byte valor) throws JuegoException {
		super(palo, valor);
	}
}