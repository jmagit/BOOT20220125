package com.example.juegos.ajedrez;

import com.example.juegos.Pieza;

/**
 * Permite crear un delegado para solicitar al usuario la pieza para la promoci�n del pe�n.
 * @author Javier
 * @version 1.0
 */
@FunctionalInterface
public interface PromocionEvent {
	/**
	 * Solicitar al usuario la pieza para la promoci�n del pe�n.
	 * @param arg Color de la pieza y permitir cancelar la operaci�n.
	 * @return Pieza de sustituci�n del pe�n, debe ser de su mismo color
	 */
	Pieza promociona(PromocionEventArgs arg);
}
