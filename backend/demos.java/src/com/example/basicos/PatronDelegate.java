package com.example.basicos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PatronDelegate {

	public PatronDelegate() {
		// TODO Auto-generated constructor stub
	}
	
	interface Comparacion<T> {
		int comparar(T a, T b); // < 0
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
		ordena(lst, new Comparacion<Persona>() {
			@Override
			public int comparar(Persona a, Persona b) {
				return -(a.getId() - b.getId());
			}
		});
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
		
	}
	
	<T> T converter(Class<T> tipo) {
		return null;
	}
	
	<T> List<T> ordena(List<T> lista, Comparacion<T> comp) {
		// ...
		T ele1 = lista.get(0);
		T ele2 = lista.get(1);
		
		if(comp.comparar(ele1, ele2) == 0) {
			
		} else if(comp.comparar(ele1, ele2) < 0) {
			
		} else {
			
		}
		
		return lista;
		
	}
/*
	void run() {
		int i = 0;
		class local implements Comparator<String> {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return i;
			}
			
		}
		ordena(new String[1], new local());
		ordena(new String[1], new Comparator<String>() {
			
			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return 0;
			}
		});
	}
	public String[] ordena(String[] origen, Comparator<String> comp) {
		Arrays.s
		String a, b;
		// ...
		if (comp.compare(a, b) == 0) {
			// ...
		} else if (comp.compare(a, b) < 0) {

		} else {
			
		}
		// ...
		return null;
	}
	public String[] oredena(String[] origen, TipoOrden tipo) {
		String a, b;
		// ...
		switch (tipo) {
		case value: {
		if (a == b) {
			// ...
		} else if (a < b) {

		} else {
			
		}
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + tipo);
		}
		// ...
		return null;
	}

	public String[] oredena(String[] origen) {
		String a, b;
		// ...
		if (a == b) {
			// ...
		} else if (a < b) {

		} else {
			
		}
		// ...
		return null;
	}
	public String[] oredenaDesc(String[] origen) {
		String a, b;
		// ...
		if (a == b) {
			// ...
		} else if (a > b) {

		} else {
			
		}
		// ...
		return null;
	}
*/
}
