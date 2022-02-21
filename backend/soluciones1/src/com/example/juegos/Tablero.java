package com.example.juegos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Function;

/**
 * Representa al Tablero del juego como contenedor de piezas.
 * 
 * @author Javier
 * @version 1.5
 */
public class Tablero implements Cloneable {
//  
//  Version 1.0
//
//	private Pieza[][] piezas;
//
//	public Tablero() {
//		piezas = new Pieza[8][8];
//	}
//
//	/**
//	 * Obtiene la pieza del tablero indicada por la fila y la columna.
//	 * @param fila Valor entre 1 y 8 de la fila en el tablero.
//	 * @param columna Valor entre 1 y 8 de la columna en el tablero.
//	 * @return Pieza en el tablero, nulo en caso de que no haya pieza.
//	 */
//	private Pieza get(int fila, int columna) {
//		if (!esValido(fila) || !esValido(columna))
//			throw new IndexOutOfBoundsException("Posición fuera del tablero.");
//		return piezas[fila - 1][columna - 1];
//	}
//
//	/**
//	 * Obtiene la pieza del tablero indicada por la posición.
//	 * @param posicion Posición con la fila y la columna en el tablero.
//	 * @return Pieza en el tablero, nulo en caso de que no haya pieza.
//	 */
//	private Pieza get(Posicion posicion) {
//		if(posicion == null)
//			throw new IllegalArgumentException("La posición no puede ser nula.");
//		return get(posicion.getFila(), posicion.getColumna());
//	}
//
//	/**
//	 * Establece la pieza del tablero indicada por la fila y la columna.
//	 * @param fila Valor entre 1 y 8 de la fila en el tablero.
//	 * @param columna Valor entre 1 y 8 de la columna en el tablero.
//	 * @param pieza Pieza a introducir en el tablero, sobre escribe en caso de que haya otra pieza.
//	 */
//	private void set(int fila, int columna, Pieza pieza) {
//		if (!esValido(fila) || !esValido(columna))
//			throw new IndexOutOfBoundsException("Posición fuera del tablero.");
//		piezas[fila - 1][columna - 1] = pieza;
//	}
//
//	/**
//	 * Establece la pieza del tablero indicada por la posición.
//	 * @param posicion Posición con la fila y la columna en el tablero.
//	 * @param pieza Pieza a introducir en el tablero, sobre escribe en caso de que haya otra pieza.
//	 */
//	private void set(Posicion posicion, Pieza pieza) {
//		if(posicion == null)
//			throw new IllegalArgumentException("La posición no puede ser nula.");
//		set(posicion.getFila(), posicion.getColumna(), pieza);
//	}

//  
//  Version 1.5
//

	private Map<Posicion, Pieza> piezas;

	public Tablero() {
		piezas = new HashMap<Posicion, Pieza>();
	}

	/**
	 * Obtiene la pieza del tablero indicada por la fila y la columna.
	 * 
	 * @param fila    Valor entre 1 y 8 de la fila en el tablero.
	 * @param columna Valor entre 1 y 8 de la columna en el tablero.
	 * @return Pieza en el tablero, nulo en caso de que no haya pieza.
	 */
	private Pieza get(int fila, int columna) {
		if (!esValido(fila) || !esValido(columna))
			throw new IndexOutOfBoundsException("Posición fuera del tablero.");
		try {
			return get(new Posicion(fila, columna));
		} catch (JuegoException e) {
			throw new IndexOutOfBoundsException("Posición fuera del tablero.");
		}
	}

	/**
	 * Obtiene la pieza del tablero indicada por la posición.
	 * 
	 * @param posicion Posición con la fila y la columna en el tablero.
	 * @return Pieza en el tablero, nulo en caso de que no haya pieza.
	 */
	private Pieza get(Posicion posicion) {
		if(posicion == null)
			throw new IllegalArgumentException("La posición no puede ser nula.");
		return piezas.get(posicion);
	}

	/**
	 * Establece la pieza del tablero indicada por la fila y la columna.
	 * 
	 * @param fila    Valor entre 1 y 8 de la fila en el tablero.
	 * @param columna Valor entre 1 y 8 de la columna en el tablero.
	 * @param pieza   Pieza a introducir en el tablero, sobre escribe en caso de que
	 *                haya otra pieza.
	 */
	private void set(int fila, int columna, Pieza pieza) {
		if (!esValido(fila) || !esValido(columna))
			throw new IndexOutOfBoundsException("Posición fuera del tablero.");
		try {
			set(new Posicion(fila, columna), pieza);
		} catch (JuegoException e) {
			throw new IndexOutOfBoundsException("Posición fuera del tablero.");
		}
	}

	/**
	 * Establece la pieza del tablero indicada por la posición.
	 * 
	 * @param posicion Posición con la fila y la columna en el tablero.
	 * @param pieza    Pieza a introducir en el tablero, sobre escribe en caso de
	 *                 que haya otra pieza.
	 */
	private void set(Posicion posicion, Pieza pieza) {
		if (piezas.containsKey(posicion)) {
			if (pieza == null)
				piezas.remove(posicion);
			else {
				piezas.replace(posicion, pieza);
			}
		} else
			piezas.put(posicion, pieza);
	}

	private boolean esValido(int indice) {
		return 1 <= indice && indice <= 8;
	}

	/**
	 * Muestra si hay una pieza ocupando una posición del tablero indicada por la
	 * fila y la columna.
	 * 
	 * @param fila    Valor entre 1 y 8 de la fila en el tablero.
	 * @param columna Valor entre 1 y 8 de la columna en el tablero.
	 * @return Es true si hay pieza en el tablero; en caso contrario, es false.
	 */
	public boolean hayPieza(int fila, int columna) {
		return get(fila, columna) != null;
	}

	/**
	 * Muestra si hay una pieza ocupando una posición del tablero indicada por la
	 * posición.
	 * 
	 * @param posicion Posición con la fila y la columna en el tablero.
	 * @return Es true si hay pieza en el tablero; en caso contrario, es false.
	 */
	public boolean hayPieza(Posicion posicion) {
		if(posicion == null)
			throw new IllegalArgumentException("La posición no puede ser nula.");
		return hayPieza(posicion.getFila(), posicion.getColumna());
	}

	/**
	 * Obtiene la pieza del tablero indicada por la fila y la columna.
	 * 
	 * @param fila    Valor entre 1 y 8 de la fila en el tablero.
	 * @param columna Valor entre 1 y 8 de la columna en el tablero.
	 * @return Pieza en el tablero, nulo en caso de que no haya pieza.
	 */
	public Pieza getPieza(int fila, int columna) {
		if (!hayPieza(fila, columna))
			throw new NoSuchElementException("No hay pieza en la posición");
		return get(fila, columna);
	}

	/**
	 * Obtiene la pieza del tablero indicada por la posición.
	 * 
	 * @param posicion Posición con la fila y la columna en el tablero.
	 * @return Pieza en el tablero, nulo en caso de que no haya pieza.
	 */
	public Pieza getPieza(Posicion posicion) {
		if(posicion == null)
			throw new IllegalArgumentException("La posición no puede ser nula.");
		return getPieza(posicion.getFila(), posicion.getColumna());
	}

	/**
	 * Establece la pieza del tablero indicada por la fila y la columna.
	 * 
	 * @param fila    Valor entre 1 y 8 de la fila en el tablero.
	 * @param columna Valor entre 1 y 8 de la columna en el tablero.
	 * @param pieza   Pieza a introducir en el tablero, sobre escribe en caso de que
	 *                haya otra pieza. No puede ser nula.
	 */
	public void setPieza(int fila, int columna, Pieza pieza) {
		assert pieza != null : "La pieza no puede ser nula.";
		set(fila, columna, pieza);
	}

	/**
	 * Establece la pieza del tablero indicada por la posición.
	 * 
	 * @param posicion Posición con la fila y la columna en el tablero.
	 * @param pieza    Pieza a introducir en el tablero, sobre escribe en caso de
	 *                 que haya otra pieza. No puede ser nula.
	 */
	public void setPieza(Posicion posicion, Pieza pieza) {
		if(posicion == null)
			throw new IllegalArgumentException("La posición no puede ser nula.");
		setPieza(posicion.getFila(), posicion.getColumna(), pieza);
	}

	/**
	 * Quita la pieza que ocupa la posición del tablero indicada por la fila y la
	 * columna.
	 * 
	 * @param fila    Valor entre 1 y 8 de la fila en el tablero.
	 * @param columna Valor entre 1 y 8 de la columna en el tablero.
	 */
	public void quitaPieza(int fila, int columna) {
		if (!hayPieza(fila, columna))
			throw new NoSuchElementException("No hay pieza en la posición");
		set(fila, columna, null);
	}

	/**
	 * Quita la pieza que ocupa la posición del tablero indicada por la posición.
	 * 
	 * @param posicion Posición con la fila y la columna en el tablero.
	 */
	public void quitaPieza(Posicion posicion) {
		if(posicion == null)
			throw new IllegalArgumentException("La posición no puede ser nula.");
		quitaPieza(posicion.getFila(), posicion.getColumna());
	}

	/**
	 * Mueve la pieza del tablero desde la posición inicial a la posición final
	 * indicada por el movimiento.
	 * 
	 * @param movimiento Movimiento a realizar.
	 * @throws JuegoException No hay pieza para mover.
	 */
	public void mover(Movimiento movimiento) throws JuegoException {
		if(movimiento == null)
			throw new IllegalArgumentException("El movimiento no puede ser nulo.");
		if (!hayPieza(movimiento.getPosicionInicial()))
			throw new JuegoException("No hay pieza para mover.");
		if (hayPieza(movimiento.getPosicionFinal()))
			quitaPieza(movimiento.getPosicionFinal());
		setPieza(movimiento.getPosicionFinal(), get(movimiento.getPosicionInicial()));
		quitaPieza(movimiento.getPosicionInicial());
	}

	/**
	 * Indica si hay piezas en el tablero entre la posición inicial y la posición
	 * final indicada por el movimiento, sin incluir dichas posiciones.
	 * 
	 * @param movimiento Movimiento a realizar.
	 * @return Es true si hay piezas en la trayectoria; en caso contrario, es false.
	 * @throws JuegoException Si el movimiento no es horizontal, vertical o diagonal
	 */
	public boolean hayPiezaEntre(Movimiento movimiento) throws JuegoException {
		if(movimiento == null)
			throw new IllegalArgumentException("El movimiento no puede ser nulo.");
		if (!movimiento.esDiagonal() && !movimiento.esHorizontal() && !movimiento.esVertical())
			throw new JuegoException(
					"El movimiento debe ser horizontal, vertical o diagonal para verificar si hay piezas entre la posición inicial y la posición final.");
		int dColum = movimiento.deltaHorizontal();
		int dFila = movimiento.deltaVertical();
		Posicion posicion = new Posicion(movimiento.getPosicionInicial().getFila() + dFila,
				movimiento.getPosicionInicial().getColumna() + dColum);
		while (!posicion.equals(movimiento.getPosicionFinal())) {
			if (hayPieza(posicion))
				return true;
			posicion = new Posicion(posicion.getFila() + dFila, posicion.getColumna() + dColum);
		}
		return false;
	}

	/**
	 * Método de clase para informar al interfaz gráfico de qué color es el escaque.
	 * 
	 * @param fila    Valor entre 1 y 8 de la fila en el tablero.
	 * @param columna Valor entre 1 y 8 de la columna en el tablero.
	 * @return Color del escaque.
	 */
	public static Color colorEscaque(int fila, int columna) {
		return (fila + columna) % 2 == 0 ? Color.NEGRO : Color.BLANCO;
	}

	/**
	 * Realiza una copia del tablero.
	 * 
	 * @return Copia del tablero.
	 */
	@Override
	public Tablero clone() {
		Tablero rslt = new Tablero();
		for (int f = 1; f <= 8; f++) {
			for (int c = 1; c <= 8; c++) {
				rslt.set(f, c, get(f, c));
			}
		}
		return rslt;
	}

	@Override
	public int hashCode() {
		return Objects.hash(piezas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tablero other = (Tablero) obj;
		return Objects.equals(piezas, other.piezas);
	}

	/**
	 * Representa un escaque del Tablero con la posición y la pieza que contiene.
	 * 
	 * @author Javier
	 * @version 1.5
	 */
	public class Escaque {
		private Posicion posicion;
		private Pieza pieza;

		public Escaque(int fila, int columna, Pieza pieza) throws JuegoException {
			this(new Posicion(fila, columna), pieza);
		}

		public Escaque(char fila, char columna, Pieza pieza) throws JuegoException {
			this(new Posicion(fila, columna), pieza);
		}

		public Escaque(Posicion posicion, Pieza pieza) {
			this.posicion = posicion;
			this.pieza = pieza;
		}

		/**
		 * Obtiene la posición del escaque
		 * 
		 * @return la posición
		 */
		public Posicion getPosicion() {
			return posicion;
		}

		/**
		 * Cambia la posición del escaque
		 * 
		 * @param posicion Nueva posición
		 */
		public void setPosicion(Posicion posicion) {
			this.posicion = posicion;
		}

		/**
		 * Obtiene el valor de la fila.
		 * 
		 * @return Valor entre 1 y 8 con la fila de la posición.
		 */
		public int getFila() {
			return posicion.getFila();
		}

		/**
		 * Obtiene el valor de la columna.
		 * 
		 * @return Valor entre 1 y 8 con la columna de la posición.
		 */
		public int getColumna() {
			return posicion.getColumna();
		}

		/**
		 * Obtiene la pieza que ocupa el escaque.
		 * 
		 * @return la pieza
		 */
		public Pieza getPieza() {
			if (pieza == null)
				throw new NoSuchElementException("No hay pieza en el escaque");
			return pieza;
		}

		/**
		 * Pone la pieza que ocupa el escaque.
		 * 
		 * @param pieza la pieza
		 */
		public void setPieza(Pieza pieza) {
			this.pieza = pieza;
		}

		/**
		 * Muestra si hay una pieza ocupando el escaque.
		 * 
		 * @return Es true si hay pieza en el escaque; en caso contrario, es false.
		 */
		public boolean hayPieza() {
			return pieza != null;
		}

		@Override
		public String toString() {
			return "Escaque [" + posicion.getFila() + ", " + posicion.getColumna() + ", " + pieza + "]";
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			return posicion.equals(((Escaque) obj).posicion);
		}

		@Override
		protected Object clone() {
			return new Escaque(posicion, pieza);
		}
	}

	/**
	 * Permite realizar búsquedas en el tablero mediante expresiones lambda.
	 * 
	 * @param filtro Expresión lambda que recibe un escaque y debe devolver true si
	 *               satisface la búsqueda.
	 * @return Lista de escaques que satisfacen la búsqueda, estará vacía si no
	 *         encuentra ninguno.
	 */
	public List<Escaque> buscar(Function<Escaque, Boolean> filtro) {
		List<Escaque> rslt = new ArrayList<Escaque>();
		for (int f = 1; f <= 8; f++) {
			for (int c = 1; c <= 8; c++) {
				try {
					Escaque actual = new Escaque(f, c, get(f, c));
					if (filtro.apply(actual))
						rslt.add(actual);
				} catch (Exception e) {
				}
			}
		}
		return rslt;
	}
}
