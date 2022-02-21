package com.example.juegos.ajedrez;

import com.example.juegos.Color;

/**
 * Argumentos de acompañamiento de la promoción del evento.
 * @author Javier
 * @version 1.0
 */
public class PromocionEventArgs {
	private Color color;
	private boolean cancel;
	
	/**
	 * Constructor único
	 * @param color Color del Peón.
	 */
	public PromocionEventArgs(Color color) {
		this.color = color;
		cancel = false;
	}

	/**
	 * Color para hacer la petición en la promoción
	 * @return Color del Peón.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Indica al peón que debe cancelar la promoción
	 * @return true para cancelar, false por defecto
	 */
	public boolean isCancel() {
		return cancel;
	}

	/**
	 * Permite establecer desde el delegado que se debe cancelar la promoción
	 * @param cancel true para cancelar, false por defecto
	 */
	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}
	

}
