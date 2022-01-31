package com.example.juegos;

public interface Juego<T> {
	/**
	 * Inicializa el juego
	 */
	void inicializar();
	/**
	 * Intento de adivinar el numero
	 * @param movimiento Cadena con un valor numerico
	 * @throws JuegoException Intentar jugar cuando ya ha terminado
	 */
	void jugada(String movimiento) throws JuegoException;
	/**
	 * Informa si ha terminado el juego
	 * @return true si ha finalizado, false si puede seguir jugando
	 */
	boolean getFinalizado();
	/**
	 * Devuele el resultado de la última jugada
	 * @return Resultado de la última jugada
	 */
	T getResultado();
	/**
	 * Devuelve el número de jugadas realizadas
	 * @return Valor con el numero jugadas realizadas, 0 si todavía no se ha jugado
	 */
	int getJugada();
}
