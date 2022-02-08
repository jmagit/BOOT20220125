package com.example.juegos.naipes;

import com.example.juegos.JuegoException;

public class Baraja40Espa�ola extends Baraja<Naipe40Espa�ol> {
	public Baraja40Espa�ola() {
		super(generaCartas());
	}

	private static Naipe40Espa�ol[] generaCartas() {
		var cartas = new Naipe40Espa�ol[4 * Naipe40Espa�ol.CARTASxPALO];
		int delta = 0;
		for (NaipeEspa�ol.Palos palo : NaipeEspa�ol.Palos.values()) {
			for (byte i = 0; i < Naipe40Espa�ol.CARTASxPALO; i++)
				try {
					cartas[Naipe40Espa�ol.CARTASxPALO * delta + i] = new Naipe40Espa�ol(palo, (byte) (i + 1));
				} catch (JuegoException e) {
				}
			delta++;
		}
		return cartas;
	}
}
