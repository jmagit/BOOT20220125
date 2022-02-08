package com.example.juegos.naipes;

import com.example.juegos.JuegoException;

public class Baraja48Española extends Baraja<Naipe48Español> {
	public Baraja48Española() {
		super(generaCartas());
	}

	private static Naipe48Español[] generaCartas() {
		var cartas = new Naipe48Español[4 * Naipe48Español.CARTASxPALO];
		int delta = 0;
		for (NaipeEspañol.Palos palo : NaipeEspañol.Palos.values()) {
			for (byte i = 0; i < Naipe48Español.CARTASxPALO; i++)
				try {
					cartas[Naipe48Español.CARTASxPALO * delta + i] = new Naipe48Español(palo, (byte) (i + 1));
				} catch (JuegoException e) {
				}
			delta++;
		}
		return cartas;
	}
}