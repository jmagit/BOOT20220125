package com.example.basicos;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PatronDelegate {
	@FunctionalInterface
	interface Comparacion<T> {
		int comparar(T x, T y); // < 0
	}

	public static int compararDeClase(Persona a, Persona b) {
		return a.getId() - b.getId();
	}

	public int compararDeInstancia(Persona a, Persona b) {
		return a.getId() - b.getId();
	}

	
	void run() {
		class PorId implements Comparacion<Persona> {
			@Override
			public int comparar(Persona a, Persona b) {
				return a.getId() - b.getId();
			}
		}
		List<Persona> lst = new ArrayList<Persona>();

		ordena(lst, new PorId());
		ordena(lst, (a, b) -> -(a.getId() - b.getId()));
		PatronDelegate pd = null;
//		ordena(lst, PatronDelegate::compararDeInstancia);
//		ordena(lst, pd::compararDeInstancia);
		ordena(lst, new Comparacion<Persona>() {
			@Override
			public int comparar(Persona a, Persona b) {
				return -(a.getId() - b.getId());
			}
		});
		ordena(lst, new Comparacion<Persona>() {
			@Override
			public int comparar(Persona a, Persona b) {
				return -(a.getId() - b.getId());
			}
		});
		int id = 4;
		Predicate<Persona> fn = item -> {
			// ...
			return item.getId() == id;
		};

		if (fn.test(lst.get(0))) {

		}
		ordena(lst, new Comparacion<Persona>() {
			@Override
			public int comparar(Persona a, Persona b) {
				return a.getNombre().compareTo(b.getNombre());
			}
		});
		ordena(lst, new Comparacion<Persona>() {
			@Override
			public int comparar(Persona a, Persona b) {
				return a.getNombre().compareToIgnoreCase(b.getNombre());
			}
		});
		ordena(new ArrayList<String>(), new Comparacion<String>() {
			@Override
			public int comparar(String a, String b) {
				return -a.compareToIgnoreCase(b);
			}
		});
		ordena(new ArrayList<String>(), String::compareToIgnoreCase);

	}

	<T> T converter(Class<T> tipo) {
		return null;
	}

	@Deprecated
	<T> List<T> ordena(List<T> lista, Comparacion<T> comp) {
		// ...
		T ele1 = lista.get(0);
		T ele2 = lista.get(1);

		if (comp.comparar(ele1, ele2) == 0) {

		} else if (comp.comparar(ele1, ele2) < 0) {

		} else {

		}

		return lista;

	}
	/*
	 * void run() { int i = 0; class local implements Comparator<String> {
	 * 
	 * @Override public int compare(String o1, String o2) { // TODO Auto-generated
	 * method stub return i; }
	 * 
	 * } ordena(new String[1], new local()); ordena(new String[1], new
	 * Comparator<String>() {
	 * 
	 * @Override public int compare(String o1, String o2) { // TODO Auto-generated
	 * method stub return 0; } }); } public String[] ordena(String[] origen,
	 * Comparator<String> comp) { Arrays.s String a, b; // ... if (comp.compare(a,
	 * b) == 0) { // ... } else if (comp.compare(a, b) < 0) {
	 * 
	 * } else {
	 * 
	 * } // ... return null; } public String[] oredena(String[] origen, TipoOrden
	 * tipo) { String a, b; // ... switch (tipo) { case value: { if (a == b) { //
	 * ... } else if (a < b) {
	 * 
	 * } else {
	 * 
	 * } } default: throw new IllegalArgumentException("Unexpected value: " + tipo);
	 * } // ... return null; }
	 * 
	 * public String[] oredena(String[] origen) { String a, b; // ... if (a == b) {
	 * // ... } else if (a < b) {
	 * 
	 * } else {
	 * 
	 * } // ... return null; } public String[] oredenaDesc(String[] origen) { String
	 * a, b; // ... if (a == b) { // ... } else if (a > b) {
	 * 
	 * } else {
	 * 
	 * } // ... return null; }
	 */
}
