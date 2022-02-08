package com.example.juegos.naipes;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.example.juegos.JuegoException;

/* Version 1.0
public class Baraja {
	private Naipe[] cartas;
	private Deque<Naipe> mazo;

	public Baraja() {
		cartas = new Naipe[40];
		int delta = 0;
		for (Naipe.Palos palo : Naipe.Palos.values()) {
			for (byte i = 0; i < 10; i++)
				try {
					cartas[10 * delta + i] = new Naipe(palo, (byte)(i + 1));
				} catch (JuegoException e) {
				}
			delta++;
		}
	}
	
	public Naipe[] getCartas() {
		return cartas.clone();
	}
	
	public void barajar() {
		class Orden {
			public Naipe carta;
			public int posicion;
			public Orden(Naipe carta, int posicion) {
				super();
				this.carta = carta;
				this.posicion = posicion;
			}			
		}
		Random rnd = new Random();
		mazo = new ArrayDeque<Naipe>(Arrays.asList(cartas).stream()
			.map(item -> new Orden(item, rnd.nextInt(1000)))
			.sorted((a, b) -> a.posicion - b.posicion)
			.map(item -> item.carta)
			.collect(Collectors.toList()));
	}
	
	public List<Naipe> getMazo() {
		return mazo.stream().collect(Collectors.toList());
	}
	
	public List<List<Naipe>> reparte(int jugadores, int cartas) {
		List<List<Naipe>> mano = new ArrayList<List<Naipe>>();
		for(int i=0; i < jugadores; i++) {
			mano.add(new ArrayList<Naipe>());			
		}
		for(int i=0; i < cartas; i ++)
			for(int j=0; j < jugadores; j++) {
				if(mano.size() == 0)
					return mano;
				mano.get(j).add(mazo.poll());
			}
		return mano;
	}
}
*/

public abstract class Baraja<T> {
	private T[] cartas;
	private Deque<T> mazo;

	public Baraja(T[] cartas) {
		this.cartas = cartas;
	}
	
	public T[] getCartas() {
		return cartas.clone();
	}
	
	public void barajar() {
		class Orden {
			public T carta;
			public int posicion;
			public Orden(T carta, int posicion) {
				super();
				this.carta = carta;
				this.posicion = posicion;
			}			
		}
		Random rnd = new Random();
		mazo = new ArrayDeque<T>(Arrays.asList(cartas).stream()
			.map(item -> new Orden(item, rnd.nextInt(10000)))
			.sorted((a, b) -> a.posicion - b.posicion)
			.map(item -> item.carta)
			.collect(Collectors.toList()));

//		var lista = Arrays.asList(cartas);
//		Collections.shuffle(lista);
//		mazo = new ArrayDeque<T>(lista);
	}
	
	public List<T> getMazo() throws JuegoException {
		if(mazo == null)
			throw new JuegoException("Es necesario barajar.");
		return mazo.stream().collect(Collectors.toList());
	}

	public boolean isQuedanCartas() {
		return mazo != null && mazo.size() > 0;
	}
	

	public List<List<T>> reparte(int jugadores, int cartas) {
		if(mazo == null)
			barajar();
		List<List<T>> mano = new ArrayList<List<T>>();
		for(int i=0; i < jugadores; i++) {
			mano.add(new ArrayList<T>());			
		}
		for(int i=0; i < cartas; i ++)
			for(int j=0; j < jugadores; j++) {
				if(mano.size() == 0)
					return mano;
				mano.get(j).add(mazo.poll());
			}
		return mano;
	}
	
	public void apilar(List<T> descarte) throws JuegoException {
		if(descarte == null || descarte.size() == 0)
			throw new JuegoException("Faltan las cartas.");
		if(!descarte.stream().allMatch( item -> Arrays.asList(cartas).contains(item)))
			throw new JuegoException("Hay cartas de otra baraja.");
		if(descarte.stream().allMatch( item -> mazo.contains(item)))
			throw new JuegoException("Hay cartas que ya estan en el mazo.");
		descarte.forEach(item -> mazo.push(item));	
	}
}