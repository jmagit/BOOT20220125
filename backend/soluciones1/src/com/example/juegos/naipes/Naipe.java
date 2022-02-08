package com.example.juegos.naipes;

import com.example.juegos.JuegoException;

/* Version 1.0
public class Naipe {
	public static enum Palos { OROS, COPAS, ESPADAS, BASTOS }
	public static final String[] LITERALES = {"Comodin", "As", "Dos", "Tres", 
			"Cuatro", "Cinco", "Seis", "Siete", "Sota", "Caballo", "Rey" };
	private Palos palo;
	private byte valor;
	
	public Naipe(Palos palo, byte valor) throws JuegoException {
		if(valor < 0 || valor > 10)
			throw new JuegoException("El valor debe estar 1 y 10");
		this.palo = palo;
		this.valor = valor;
	}

	public Palos getPalo() {
		return palo;
	}

	public byte getValor() {
		return valor;
	}

	@Override
	public String toString() {
		return LITERALES[valor] + " de " + palo;
	}
}
*/
public abstract class Naipe<T> {
	private final T palo;
	private final byte valor;
	
	public Naipe(T palo, byte valor) throws JuegoException {
		this.palo = palo;
		this.valor = valor;
	}

	public T getPalo() {
		return palo;
	}

	public byte getValor() {
		return valor;
	}
	
	public String getLiteral() {
		// Falta validar
		return getLiterales()[valor];
	}
	
	protected abstract String[] getLiterales();

	@Override
	public String toString() {
		return getLiterales()[valor] + " de " + palo;
	}
}
