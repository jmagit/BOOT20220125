package com.example.juegos.ajedrez;

import com.example.juegos.Color;
import com.example.juegos.JuegoException;
import com.example.juegos.Movimiento;
import com.example.juegos.Pieza;
import com.example.juegos.Tablero;

/**
 * El Pe�n como pieza del Ajedrez.
 * 
 * @author Javier
 * @version 1.0
 */
public class Peon extends Pieza {
	private PromocionEvent onPromocion;

	/**
	 * Constructor �nico del Pe�n.
	 * 
	 * @param color       Color del Pe�n.
	 * @param onPromocion Delegado a ejecutar para solicitar al usuario la pieza
	 *                    para la promoci�n.
	 */
	public Peon(Color color, PromocionEvent onPromocion) {
		super(color);
		assert onPromocion != null : "El m�todo de promoci�n es obligatorio";
		this.onPromocion = onPromocion;
	}

	/**
	 * Indica si el Pe�n se encuentra en la posici�n inicial y, por lo tanto, puede
	 * saltar dos escaques.
	 * 
	 * @param movimiento Movimiento con la posici�n inicial.
	 * @return Es true si no se ha movido; en caso contrario, es false.
	 */
	private boolean esApertura(Movimiento movimiento) {
		return movimiento.getPosicionInicial().getFila() == (getColor() == Color.BLANCO ? 2 : 7);
	}

	/**
	 * Indica si el Pe�n va a alcanzar la �ltima fila y, por lo tanto, necesita
	 * promocionar.
	 * 
	 * @param movimiento Movimiento con la posici�n final.
	 * @return Es true si alcanza la �ltima fila; en caso contrario, es false.
	 */
	private boolean esPromocion(Movimiento movimiento) {
		return movimiento.getPosicionFinal().getFila() == (getColor() == Color.BLANCO ? 8 : 1);
	}

	/**
	 * Indica si el Pe�n avanza.
	 * 
	 * @param movimiento Movimiento a verificar.
	 * @return Es true si avanza; en caso contrario, es false.
	 */
	private boolean avanza(Movimiento movimiento) {
		return movimiento.deltaVertical() == (getColor() == Color.BLANCO ? 1 : -1)
				&& (movimiento.esDiagonal() || movimiento.esVertical()) && !movimiento.esHorizontal();
	}

	/**
	 * Indica si en la posici�n final hay una pieza contraria.
	 * 
	 * @param movimiento Movimiento a verificar.
	 * @param tablero    Tablero para verificar las piezas.
	 * @return Es true si puede capturar; en caso contrario, es false.
	 */
	private boolean puedeComer(Movimiento movimiento, Tablero tablero) {
		return movimiento.esDiagonal() && movimiento.saltoVertical() == 1
				&& tablero.hayPieza(movimiento.getPosicionFinal())
				&& tablero.getPieza(movimiento.getPosicionFinal()).getColor() != getColor();
	}

	/**
	 * Reemplazado. Indica porque el Pe�n no puede realizar el movimiento. Verifica
	 * que el pe�n avanza. Si el movimiento es vertical comprueba que solo avance
	 * una posici�n (dos, en caso de apertura, sin saltar otras piezas) siempre que
	 * el escaque de destino no est� ocupado. En caso de ser diagonal, verifica que
	 * solo avance una posici�n y pueda capturar una pieza contraria. NO CONTEMPLA
	 * LA CAPTURA AL PASO.
	 * 
	 * @param movimiento Movimiento a verificar.
	 * @param tablero    Tablero para verificar que no salta piezas.
	 * @return Mensaje de porque la pieza no puede realizar el movimiento o cadena
	 *         vacia si puede realizarlo.
	 */
	@Override
	public String getError(Movimiento movimiento, Tablero tablero) {
		if(movimiento == null)
			throw new IllegalArgumentException("El movimiento no puede ser nulo.");
		if(tablero == null)
			throw new IllegalArgumentException("El tablero no puede ser nulo.");
		if (!avanza(movimiento))
			return "El pe�n solo puede avanzar.";
		if (movimiento.esDiagonal() && !puedeComer(movimiento, tablero))
			return "El pe�n solo puede avanzar una posici�n en diagonal para capturar otra pieza.";
		if (movimiento.esVertical()) {
			if (movimiento.saltoVertical() > 2)
				return "El pe�n no puede desplazase " + movimiento.saltoVertical() + " escaques.";
			if (tablero.hayPieza(movimiento.getPosicionFinal()))
				return "El pe�n captura piezas en diagonal.";
			try {
				if (movimiento.saltoVertical() == 2 && (!esApertura(movimiento) || tablero.hayPiezaEntre(movimiento)))
					return "El pe�n solo puede desplazase 2 escaques en la apertura sin saltar piezas.";
			} catch (JuegoException e) {
				return e.getMessage();
			}
		}
		return "";
	}

	/**
	 * Reemplazado. Mueve el Pe�n en el tablero. En caso de alcanzar la �ltima fila,
	 * lanza el evento de Promoci�n.
	 */
	@Override
	public void mover(Movimiento movimiento, Tablero tablero) throws JuegoException {
		if(movimiento == null)
			throw new IllegalArgumentException("El movimiento no puede ser nulo.");
		if(tablero == null)
			throw new IllegalArgumentException("El tablero no puede ser nulo.");
		if (esValido(movimiento, tablero)) {
			if (esPromocion(movimiento)) {
				PromocionEventArgs e = new PromocionEventArgs(getColor());

				Pieza nuevaPieza = onPromocion.promociona(e);
				if (e.isCancel())
					throw new JuegoException("Se ha cancelado el movimiento.");
				if (nuevaPieza == null)
					throw new JuegoException("No se ha proporcionado la pieza a promocionar.");
				if (!(nuevaPieza instanceof Dama || nuevaPieza instanceof Alfil || nuevaPieza instanceof Caballo
						|| nuevaPieza instanceof Torre))
					throw new JuegoException(
							"Es obligatorio promocionar al pe�n a una Dama, un Alfil, una Torre o un Caballo.");
				if (nuevaPieza.getColor() != getColor())
					throw new JuegoException("No se puede promocionar a una pieza del otro jugador.");
				tablero.quitaPieza(movimiento.getPosicionInicial());
				tablero.setPieza(movimiento.getPosicionInicial(), nuevaPieza);
			}
			super.mover(movimiento, tablero);
		} else
			throw new JuegoException(getError(movimiento, tablero));
	}

}
