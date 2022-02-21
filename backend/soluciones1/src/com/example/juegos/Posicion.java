package com.example.juegos;

import java.util.Objects;

/**
 * Posición dentro del tablero de ajedrez
 * @author Javier
 *
 */
public class Posicion {
	private int fila;
	private int columna;

	/**
	 * Constructor de la posición
	 * @param fila Valor entre 1 y 8
	 * @param columna Valor entre 1 y 8
	 * @throws JuegoException Valor de la fila o la columna incorrecto
	 */
	public Posicion(int fila, int columna) throws JuegoException {
		if(fila < 1 || fila > 8)
			throw new JuegoException("La fila debe estar entre el 1 y el 8");
		if(columna < 1 || columna > 8)
			throw new JuegoException("La columna debe estar entre el 1 y el 8");
		this.fila = fila;
		this.columna = columna;
	}
	/**
	 * Constructor de la posición
	 * @param fila Valor entre 1 y 8
	 * @param columna Valor entre A y H
	 * @throws JuegoException Valor de la fila o la columna incorrecto
	 */
	public Posicion(char fila, char columna) throws JuegoException {
		this(fila - '0', columna - 'A' + 1);
	}
	/**
	 * Obtiene el componente fila de la posición
	 * @return valor entre 1 y 8
	 */
	public int getFila() {
		return fila;
	}
	/**
	 * Obtiene el componente columna de la posición
	 * @return valor entre 1 y 8
	 */
	public int getColumna() {
		return columna;
	}
	@Override
	public int hashCode() {
		return Objects.hash(columna, fila);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Posicion))
			return false;
		Posicion other = (Posicion) obj;
		return columna == other.columna && fila == other.fila;
	}
	@Override
	public String toString() {
		return "Posicion [fila=" + fila + ", columna=" + columna + "]";
	}
	
	
}
