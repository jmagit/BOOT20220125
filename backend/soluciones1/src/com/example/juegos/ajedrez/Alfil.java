package com.example.juegos.ajedrez;

import com.example.juegos.Color;
import com.example.juegos.JuegoException;
import com.example.juegos.Movimiento;
import com.example.juegos.Pieza;
import com.example.juegos.Tablero;

/**
 * El Alfil como pieza del Ajedrez.
 * @author Javier
 * @version 1.0
 */
public class Alfil extends Pieza {

	/**
	 * Constructor único del Alfil.
	 * @param color Color del Alfil.
	 */
	public Alfil(Color color) {
		super(color);
	}

	/**
	 * Reemplazado. Indica porque el Alfil no puede realizar el movimiento.
	 * Verifica que el alfil se desplaza diagonal sin saltar piezas.
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
        if (!movimiento.esDiagonal())
            return "El alfil solo puede realizar desplazamientos diagonales.";
		else
			try {
				if (tablero.hayPiezaEntre(movimiento))
					return "El alfil no puede saltar piezas.";
			} catch (JuegoException e) {
				return e.getMessage();
			}
        return "";
	}
}
