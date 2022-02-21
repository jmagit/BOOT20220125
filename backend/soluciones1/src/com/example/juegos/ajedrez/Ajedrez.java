package com.example.juegos.ajedrez;

import com.example.juegos.Color;
import com.example.juegos.Juego;
import com.example.juegos.JuegoException;
import com.example.juegos.Movimiento;
import com.example.juegos.Tablero;

/**
 * Clase principal del juego de Ajedrez con la implementación de la lógica general.
 * @author Javier
 * @version 1.0
 */
public class Ajedrez implements Juego<Tablero> {
	private Color turno;
	private Tablero tablero;
	private boolean partidaActiva;
	private int jugada;
	// Implementación de eventos mediante los patrones delegate y command
	private PromocionEvent onPromocion;
	
	/**
	 * Constructor que requiere el delegado a invocar cuando se solicita al usuario la pieza de sustitución del peón cuando promociona.
	 * @param onPromocion Delegado a ejecutar para solicitar al usuario la pieza para la promoción del peón.
	 */
	public Ajedrez(PromocionEvent onPromocion) {
		assert onPromocion != null : "El método de promoción es obligatorio";
		this.onPromocion = onPromocion;
		inicializar();
	}
	
	/**
	 * Obtiene el turno del jugador que le toca jugar.
	 * @return Color del jugador.
	 */
	public Color getTurno() {
		return turno;
	}

	/**
	 * Obtiene el tablero para que el contenedor pueda implementar el interfaz de usuario.
	 * @return Copia del tablero
	 */
	public Tablero getTablero() {
		return tablero.clone();
	}

	@Override
	public void inicializar() {
		turno = Color.BLANCO;
		tablero = new Tablero();

		tablero.setPieza(1, 1, new Torre(Color.BLANCO));
		tablero.setPieza(1, 2, new Caballo(Color.BLANCO));
		tablero.setPieza(1, 3, new Alfil(Color.BLANCO));
		tablero.setPieza(1, 4, new Dama(Color.BLANCO));
		tablero.setPieza(1, 5, new Rey(Color.BLANCO));
		tablero.setPieza(1, 6, new Alfil(Color.BLANCO));
		tablero.setPieza(1, 7, new Caballo(Color.BLANCO));
		tablero.setPieza(1, 8, new Torre(Color.BLANCO));

		tablero.setPieza(8, 1, new Torre(Color.NEGRO));
		tablero.setPieza(8, 2, new Caballo(Color.NEGRO));
		tablero.setPieza(8, 3, new Alfil(Color.NEGRO));
		tablero.setPieza(8, 4, new Dama(Color.NEGRO));
		tablero.setPieza(8, 5, new Rey(Color.NEGRO));
		tablero.setPieza(8, 6, new Alfil(Color.NEGRO));
		tablero.setPieza(8, 7, new Caballo(Color.NEGRO));
		tablero.setPieza(8, 8, new Torre(Color.NEGRO));

		for (byte c = 1; c <= 8; c++) {
			tablero.setPieza(2, c, new Peon(Color.BLANCO, onPromocion));
			tablero.setPieza(7, c, new Peon(Color.NEGRO, onPromocion));
		}

//		tablero.setPieza(7, 1, new Peon(Color.BLANCO, onPromocion));
//		tablero.setPieza(2, 1, new Peon(Color.NEGRO, onPromocion));

		partidaActiva = true;
		jugada = 0;
	}

	private void cambiaTurno() {
		turno = turno == Color.BLANCO ? Color.NEGRO : Color.BLANCO;
	}

	private void mover(Movimiento movimiento) throws JuegoException {
		if (!tablero.hayPieza(movimiento.getPosicionInicial()))
			throw new JuegoException("No hay pieza en la posición inicial.");
		if (tablero.getPieza(movimiento.getPosicionInicial()).getColor() != turno)
			throw new JuegoException("No puede mover piezas del otro jugador.");
		if (tablero.hayPieza(movimiento.getPosicionFinal())
				&& tablero.getPieza(movimiento.getPosicionFinal()).getColor() == turno)
			throw new JuegoException("No puede capturar sus propias piezas.");
		tablero.getPieza(movimiento.getPosicionInicial()).mover(movimiento, tablero);
	}


	/**
	 * Método principal que desarrolla el juego. El juego debe estar iniciado.
	 * NO CONTEMPLA EL JAQUE. Así mismo, no contempla las reglas de finalización como el jaque mate, el abandono o las tablas. Para terminar la partida es necesario teclear "FIN" o "TABLAS".
	 * @param movimiento Cadena en notación internacional con el movimiento del jugador.
	 * @throws JuegoException Posibles errores en la jugada
	 */
	@Override
	public void jugada(String movimiento) throws JuegoException {
		if(!partidaActiva) 
			throw new JuegoException("El juego ha terminado.");
		if("FIN".equals(movimiento.toUpperCase()) || "TABLAS".equals(movimiento.toUpperCase())) {
			partidaActiva=false;
			return;
		}
		this.mover(new Movimiento(movimiento));
		this.cambiaTurno();
		this.jugada++;
	}

	@Override
	public boolean getFinalizado() {
		return !partidaActiva;
	}

	@Override
	public Tablero getResultado() {
		return tablero.clone();
	}
	
	/**
	 * Permite seguir jugando una partida que quedo a medias.
	 * @param tablero Tablero con la situación en que quedo la partida.
	 * @param turno Color del jugador que le toca jugar
	 * @throws JuegoException La situación del tablero no es correcta.
	 */
	public void continuarPartida(Tablero tablero, Color turno) throws JuegoException {
		assert tablero != null: "Falta el tablero con las piezas";
		var reyes = tablero.buscar(e -> e.hayPieza() && e.getPieza() instanceof Rey);
		if(reyes.size() != 2 && reyes.get(0).getPieza().getColor() == reyes.get(1).getPieza().getColor())
			throw new JuegoException("Disposición incorrecta de piezas");
		this.tablero = tablero.clone();
		this.turno = turno;
	}

	@Override
	public int getJugada() {
		// TODO Auto-generated method stub
		return jugada;
	}

}
