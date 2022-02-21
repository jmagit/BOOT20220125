package com.example.juegos.ajedrez;

import com.example.juegos.Color;

/**
 * Argumentos de acompa�amiento de la promoci�n del evento.
 * @author Javier
 * @version 1.0
 */
public class PromocionEventArgs {
	private Color color;
	private boolean cancel;
	
	/**
	 * Constructor �nico
	 * @param color Color del Pe�n.
	 */
	public PromocionEventArgs(Color color) {
		this.color = color;
		cancel = false;
	}

	/**
	 * Color para hacer la petici�n en la promoci�n
	 * @return Color del Pe�n.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Indica al pe�n que debe cancelar la promoci�n
	 * @return true para cancelar, false por defecto
	 */
	public boolean isCancel() {
		return cancel;
	}

	/**
	 * Permite establecer desde el delegado que se debe cancelar la promoci�n
	 * @param cancel true para cancelar, false por defecto
	 */
	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}
	

}
