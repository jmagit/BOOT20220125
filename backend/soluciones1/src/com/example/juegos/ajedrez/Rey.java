package com.example.juegos.ajedrez;

import com.example.juegos.Color;
import com.example.juegos.Movimiento;
import com.example.juegos.Pieza;
import com.example.juegos.Tablero;

/**
 * El Rey como pieza del Ajedrez.
 * 
 * @author Javier
 * @version 1.0
 */
public class Rey extends Pieza {
	/**
	 * Constructor único del Rey.
	 * 
	 * @param color Color del Rey.
	 */
	public Rey(Color color) {
		super(color);
	}

	/**
	 * Reemplazado. Indica porque el Rey no puede realizar el movimiento.
	 * rey solo se desplaza una posición en cualquier dirección. NO CONTEMPLA EL
	 * ENROQUE NI EL JAQUE.
	 * 
	 * @param movimiento Movimiento a verificar.
	 * @param tablero Tablero para verificar que no salta piezas.
	 * @return Mensaje de porque la pieza no puede realizar el movimiento o cadena vacia si puede realizarlo.
	 */
	@Override
	public String getError(Movimiento movimiento, Tablero tablero) {
		if(movimiento == null)
			throw new IllegalArgumentException("El movimiento no puede ser nulo.");
		if(tablero == null)
			throw new IllegalArgumentException("El tablero no puede ser nulo.");
		if (movimiento.saltoHorizontal() > 1 || movimiento.saltoVertical() > 1)
			return "El rey no pude desplazarse más de una posición.";
		return "";
	}
}
