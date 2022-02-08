package com.example.juegos.naipes;

import com.example.juegos.JuegoException;

public class Baraja48Espa�ola extends Baraja<Naipe48Espa�ol> {
	public Baraja48Espa�ola() {
		super(generaCartas());
	}

	private static Naipe48Espa�ol[] generaCartas() {
		var cartas = new Naipe48Espa�ol[4 * Naipe48Espa�ol.CARTASxPALO];
		int delta = 0;
		for (NaipeEspa�ol.Palos palo : NaipeEspa�ol.Palos.values()) {
			for (byte i = 0; i < Naipe48Espa�ol.CARTASxPALO; i++)
				try {
					cartas[Naipe48Espa�ol.CARTASxPALO * delta + i] = new Naipe48Espa�ol(palo, (byte) (i + 1));
				} catch (JuegoException e) {
				}
			delta++;
		}
		return cartas;
	}
}