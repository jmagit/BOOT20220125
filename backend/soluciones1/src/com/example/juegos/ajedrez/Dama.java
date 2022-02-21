package com.example.juegos.ajedrez;

import com.example.juegos.Color;
import com.example.juegos.JuegoException;
import com.example.juegos.Movimiento;
import com.example.juegos.Pieza;
import com.example.juegos.Tablero;

/**
 * La Dama como pieza del Ajedrez.
 * @author Javier
 * @version 1.0
 */
public class Dama extends Pieza {

	/**
	 * Constructor único de la Dama.
	 * @param color Color de la Dama.
	 */
	public Dama(Color color) {
		super(color);
	}

	/**
	 * Reemplazado. Indica porque la Dama no puede realizar el movimiento.
	 * Verifica que la dama se desplaza horizontal, vertical y diagonal sin saltar piezas.
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
        if (!(movimiento.esHorizontal() || movimiento.esVertical() || movimiento.esDiagonal()))
            return "La reina solo puede realizar desplazamientos horizontales, verticales o diagonales.";
		else
			try {
				if (tablero.hayPiezaEntre(movimiento))
					return "La reina no puede saltar piezas.";
			} catch (JuegoException e) {
				return e.getMessage();
			}
        return "";
	}

}
