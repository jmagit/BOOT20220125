package com.example.juegos.naipes;

import com.example.juegos.JuegoException;

public class Baraja40Española extends Baraja<Naipe40Español> {
	public Baraja40Española() {
		super(generaCartas());
	}

	private static Naipe40Español[] generaCartas() {
		var cartas = new Naipe40Español[4 * Naipe40Español.CARTASxPALO];
		int delta = 0;
		for (NaipeEspañol.Palos palo : NaipeEspañol.Palos.values()) {
			for (byte i = 0; i < Naipe40Español.CARTASxPALO; i++)
				try {
					cartas[Naipe40Español.CARTASxPALO * delta + i] = new Naipe40Español(palo, (byte) (i + 1));
				} catch (JuegoException e) {
				}
			delta++;
		}
		return cartas;
	}
}
