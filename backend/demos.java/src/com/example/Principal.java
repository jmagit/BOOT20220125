/**
 * 
 */
package com.example;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.example.basicos.Alumno;
import com.example.basicos.AlumnoRepository;
import com.example.basicos.AlumnoRepositoryMock;
import com.example.basicos.Autor;
import com.example.basicos.Calculadora;
import com.example.basicos.CalculadoraCientifica;
import com.example.basicos.Dias;
import com.example.basicos.DiasLaborables;
import com.example.basicos.Factura;
import com.example.basicos.Grafico;
import com.example.basicos.Persona;
import com.example.basicos.Profesor;
import com.example.basicos.EjemplosGenericos;
/**
 * Ejemplo de la sintaxis
 * 
 * @author Javier
 *
 */
public class Principal {

	/**
	 * 
	 * @author Javier
	 *
	 */
	enum Genero {
		/**
		 * 
		 */
		MASCULINO('M', 1), FEMENINO('F', 2), OTROS('O', 3);

		private char letra;
		private int numero;

		private Genero(char letra, int numero) {
			this.letra = letra;
			this.numero = numero;
		}

		/**
		 * Valor numerico asociado al genero
		 * 
		 * @return De 1 a 3
		 */
		public int getNumero() {
			return numero;
		}

		public int getLetra() {
			return letra;
		}

		public static Genero getEnum(int numero) {
			switch (numero) {
			case 1:
				return Genero.MASCULINO;
			case 2:
				return Genero.FEMENINO;
			case 3:
				return Genero.OTROS;
			default:
				throw new IllegalArgumentException("Unexpected value: " + numero);
			}
		}

		public static Genero getEnum(char letra) {
			switch (letra) {
			case 'M':
				return Genero.MASCULINO;
			case 'F':
				return Genero.FEMENINO;
			case 'O':
				return Genero.OTROS;
			default:
				throw new IllegalArgumentException("Unexpected value: " + letra);
			}
		}
	}

	/**
	 * Método principal
	 * 
	 * @param args Lista de argumentos
	 * @return 0 si termina bien
	 */
	public static void main(String[] args) {
		List<Persona> lista = new ArrayList<>();
		lista.add(new Alumno(1, "uno", "", "1995-08-12"));
		lista.add(new Alumno(2, "dos", "apell", "2000-02-01"));
		lista.add(new Profesor(3, "Pepito", "Grillo", "1991-02-07", 1000));
		lista.add(new Alumno(4, "tres", "", "2000-02-11"));
		lista.add(new Profesor(5, "Otro", "Profe", "1988-12-17", 1500));

		lista.stream()
			.filter(item -> item instanceof Profesor)
			.map(item -> (Profesor)item)
			.peek(item -> item.setSalario(item.getSalario() * 1.1))
			.count();
//			.forEach(item -> System.out.println(item.getId() + " gana " + item.getSalario()));
//
		System.out.println(lista.stream()
		.filter(item -> item instanceof Profesor)
		.mapToDouble(item -> ((Profesor)item).getSalario())
		.sum());
////		System.out.println(lista.stream()
////		.filter(item -> item instanceof Profesor)
////		.map(item -> ((Profesor)item).getSalario())
////		.reduce(0, (a, item) -> a + item));

//		lista.stream().filter(item -> item.getEdad() <= 22)
//			.forEach(item -> System.out.println(item.getNombre() + " " + item.getEdad()));
		
		boolean jovenes = true, paginado = true;
		int page = 0, rows = 3;
		var query = lista.stream();
		if(jovenes)
			query = query.filter(item -> item.getEdad() <= 22);
		if(paginado)
			query = query.skip(page * rows).limit(rows);
		
		query.forEach(item -> System.out.println(item.getNombre() + " " + item.getEdad()));
//		query.forEach(item -> System.out.println(item.getNombre() + " " + item.getEdad()));
		
//		List<Integer> listOfIntegers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
//		System.out.println("Sequential Stream: ");
//		listOfIntegers.stream().forEach(e -> System.out.print(e + " "));
//		System.out.println("\nParallel Stream: ");
//		listOfIntegers.parallelStream().peek(item->  System.out.print(item+ "->")).sequential().forEach(e -> System.out.print(e + " "));

		Genero g = Enum.valueOf(Genero.class, "MASCULINO");
		String c = g.toString();
	}
	
	public static void ejem7(String[] args) {
		List<Alumno> lista = new ArrayList<>();
		lista.add(new Alumno(1, "uno", "", "1995-08-12"));
		lista.add(new Alumno(2, "dos", "apell", "2000-02-01"));
		lista.add(new Alumno(4, "tres", "", "2000-02-11"));
		lista.forEach(item -> {
			item.setNombre(item.getNombre().toUpperCase());
			if(item.getApellidos().isPresent())
				item.setApellidos(item.getApellidos().get().toUpperCase());
			var a = item.clone();
			System.out.println(a.getFechaNacimiento() + " -> " + a.getEdad());
		});
		AlumnoRepository dao = new AlumnoRepositoryMock();
		lista.add(dao.getOne(666));
		lista.forEach(System.out::println);
		
//		Map<Integer, Persona> diccionario = new HashMap<>();
//		diccionario.put(1, new Alumno(1, "uno", "", "1995-08-12"));
//		diccionario.put(2, new Alumno(2, "dos", "apell", "2000-02-01"));
//		diccionario.put(4, new Alumno(4, "tres", "", "2000-02-11"));
//		
//		System.out.println(lista.get(2));
//		System.out.println(diccionario.get(2));
//		diccionario.remove(2);
//		diccionario.values().forEach(System.out::println);
//		System.out.println("Conjuntos");
//		Set<Alumno> conjunto = new HashSet<>();
//		conjunto.add(new Alumno(1, "uno", "", "1995-08-12"));
//		conjunto.add(new Alumno(2, "dos", "apell", "2000-02-01"));
//		conjunto.add(new Alumno(4, "tres", "", "2000-02-11"));
//		conjunto.add(new Alumno(1, "uno", "", "1995-08-12"));
//		conjunto.add(new Alumno(2, "dos", "apell", "2000-02-01"));
//		conjunto.add(new Alumno(3, "nuevo", "", "2000-02-11"));
//		conjunto.forEach(System.out::println);
		
		
		
	}
	public static void ejem6(String[] args) {
		Annotation[] anotaciones = Alumno.class.getAnnotations();
		Alumno a = new Alumno(1, "original", "", "Sat, 12 Aug 1995 13:30:00 GMT");
		var a2 = a.clone();
		a.setCalle("Calle luna");
		a.setNombre("Otro");
		System.out.println(a.getNombre());
		System.out.println(a2.getNombre());
		System.out.println(a.getCalle());
		System.out.println(a2.getCalle());
		a.setCalle("Compartida");
		System.out.println(a.getCalle());
		System.out.println(a2.getCalle());

		System.out.println(a.getClass().getName());
		System.out.println(a.getClass().getAnnotation(Autor.class).nombre());
		Class<Persona> clase = Persona.class;
		System.out.println(clase.getAnnotation(Autor.class).nombre());
		System.out.println(a.getEdad());
		var f = new Factura(0);
		f.saluda();
		if(a == a2) {
			
		}
		System.out.println(a);
		System.out.println(a2);
	}
	
	public static void ejem5(String[] args) {
		EjemplosGenericos.Elemento<Character, String> g = new EjemplosGenericos.Elemento<Character, String>('H', "Hombre");
		g = new EjemplosGenericos.Elemento<Character, String>('M', "Mujer");
		g.setKey('P');
		EjemplosGenericos.Elemento<Integer, String> p = new EjemplosGenericos.Elemento<Integer, String>(8, "Barcelona");
		p.setKey(2);
	    // g.getValue().
		Class v = EjemplosGenericos.Elemento.class;
		Optional<String> opcional = Optional.of("cadena");
		var s = opcional.get();
	}

	public static void ejem4(String[] args) {
		DiasLaborables d = DiasLaborables.LUNES;
		int i = d.getValue();
		d = DiasLaborables.getEnum(3);

//		String s = "";
//		for (var i = 0; i < 100; i++)
//			s += "X";
//		StringBuilder sb = new StringBuilder("");
//		for (var i = 0; i < 100; i++)
//			sb.append("X");
//		s = sb.toString();
		Genero g = Genero.getEnum(2);
		if (g == Genero.FEMENINO || g == Genero.MASCULINO) {

		}
		i = g.getNumero();

		Persona p = null;
		try {
			p = new Alumno(1, null, "Grillo", "2000-01-01 00:00:00");

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		if (p == null)
			System.out.println("No hay persona");

	}

	public static void ejer3(String[] args) {
		Object o = null;

		o = 4; // new Integer(4);
		int i = (int) o; // o.getValue()
		o = i;
		Integer v = 4;
		i = v;
		String s = "";

//		for(int ) concatenacion - horario 13:30

		Factura f = new Factura(1);
		try {
			f.addLinea(null, 0, 9.0);
//		} catch (IllegalArgumentException e) {
//			System.out.println("ERRROR: " + e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (f.getEstado() == Factura.Estado.PENDIENTE) {
			f.cancelar();
		}
		f.guardar();

		Grafico grafico = f;
		// grafico = new CalculadoraCientifica();

		grafico.pintate();
		if (f instanceof Factura)
			try {
				((Factura) grafico).addLinea("Comida", 1, 9.0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		if (o instanceof Grafico)
			((Grafico) o).pintate();

	}

	public static void ejemplos2(String[] args) {
		Dias dia = Dias.DOMINGO;
		String s = "Hola";
		s += " mundo";
		System.out.println(dia);
		Calculadora c = Calculadora.getCurrent();// new Calculadora();
//		System.out.println(c.avg());
		System.out.println(c.avg(5));
		System.out.println(c.avg(1, 2, 3, 4, 5));
		double i = Calculadora.suma(0, 0.0);
		CalculadoraCientifica cc = new CalculadoraCientifica();
		System.out.println(cc.suma(2, 2));
		System.out.println("Elimino");
		cc = null;
		System.runFinalization();
		cc = new CalculadoraCientifica();
		System.out.println(cc.suma(1, 1));
	}

	/**
	 * @param args
	 */
	public static void ejemplos1(String[] args) {
		int[] t1 = { 1, 2, 3 };
		int[][] t2 = { { 1, 2 }, { 3, 3, 3 } };
		if (t2[0].length == t2[1].length) {
		}
		t2[0][2] = t2[1][2];
		t2[0] = t2[1];
		t2[0][0] = 0;
		t2[1] = new int[10];
		int[] aux = t2[0];
		t2[0] = t2[1];
		t2[1] = aux;

		int i = 3 / 2, j;
		i = i + 2;
		i += 2;

		++i;
		i += 1;
		i = i + 1;
		t2[i++] = aux;

		j = i++;

		String string = "Alumno" + (i > 1 ? "s" : "");
		string = (string == null ? "" : (string + "s")) + " algo ";
		double r = 3 / 2;
		byte b;
		b = (byte) i;

		i = (b = 0) + 1;

		Object o = "";

		if (o instanceof String) {
			String s = (String) o;

		}
		if (o instanceof String s)
			i = s.length();
		i++;

		var l = false;
		if (l == true) {
		}
		calc((int) 4);

	}

	static void calc(int op) {

	}

	static void calc(double op) {

	}
}
