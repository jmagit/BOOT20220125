package com.example.juegos.ajedrez;

import com.example.juegos.Color;
import com.example.juegos.Movimiento;
import com.example.juegos.Pieza;
import com.example.juegos.Tablero;

/**
 * El Caballo como pieza del Ajedrez.
 * @author Javier
 * @version 1.0
 */
public class Caballo extends Pieza {

	/**
	 * Constructor único del Caballo.
	 * @param color Color del Caballo.
	 */
	public Caballo(Color color) {
		super(color);
	}

	/**
	 * Reemplazado. Indica porque el Caballo no puede realizar el movimiento.
	 * Verifica que el caballo se desplaza dos posiciones en horizontal y una en vertical o una posición en horizontal y dos en vertical.
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
        int s1 = movimiento.saltoVertical();
        int s2 = movimiento.saltoHorizontal();
        if (!((s1 == 1 && s2 == 2) || (s1 == 2 && s2 == 1))) {
            return "El caballo solo puede saltar 2 filas y 1 columna o 1 fila y 2 columnas.";
        }
        return "";
	}

}
