package com.example.juegos.ajedrez;

import com.example.juegos.Color;
import com.example.juegos.JuegoException;
import com.example.juegos.Movimiento;
import com.example.juegos.Pieza;
import com.example.juegos.Tablero;

/**
 * La Torre como pieza del Ajedrez.
 * @author Javier
 * @version 1.5
 */
public class Torre extends Pieza {
	/**
	 * Constructor único de la Torre.
	 * @param color Color de la Torre.
	 */
	public Torre(Color color) {
		super(color);
	}

	/**
	 * Reemplazado. Indica porque la Torre no puede realizar el movimiento.
	 * Verifica que la torre se desplaza horizontal y vertical sin saltar piezas.
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
        if (!(movimiento.esHorizontal() || movimiento.esVertical()))
            return "La torre solo puede realizar desplazamientos horizontales o verticales.";
		else
			try {
				if (tablero.hayPiezaEntre(movimiento))
					return "La torre no puede saltar piezas.";
			} catch (JuegoException e) {
				return e.getMessage();
			}
        return "";
	}
}
