package com.example.juegos.naipes;

import com.example.juegos.JuegoException;

public class BarajaFrancesa extends Baraja<NaipeFrances> {
	public BarajaFrancesa() {
		super(generaCartas());
	}

	private static NaipeFrances[] generaCartas() {
		var cartas = new NaipeFrances[4 * NaipeFrances.CARTASxPALO];
		int delta = 0;
		for (NaipeFrances.Palos palo : NaipeFrances.Palos.values()) {
			for (byte i = 0; i < NaipeFrances.CARTASxPALO; i++)
				try {
					cartas[NaipeFrances.CARTASxPALO * delta + i] = new NaipeFrances(palo, (byte) (i + 1));
				} catch (JuegoException e) {
				}
			delta++;
		}
		return cartas;
	}
}
