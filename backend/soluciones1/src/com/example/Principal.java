package com.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.example.juegos.Color;
import com.example.juegos.Juego;
import com.example.juegos.JuegoException;
import com.example.juegos.Pieza;
import com.example.juegos.Tablero;
import com.example.juegos.ajedrez.Ajedrez;
import com.example.juegos.ajedrez.Alfil;
import com.example.juegos.ajedrez.Caballo;
import com.example.juegos.ajedrez.Dama;
import com.example.juegos.ajedrez.PromocionEventArgs;
import com.example.juegos.ajedrez.Torre;
import com.example.juegos.naipes.BarajaFrancesa;
import com.example.juegos.naipes.ValorNaipe;

public class Principal {
	@SuppressWarnings("resource")
	private static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {
		Principal app = new Principal();
//		app.run();

		// app.juego();
		// app.juegoConClase();
		app.ajedrez();
//		decode("3+4+3,4-7*1=");
//		try {
//			//calcula("2+2-2+7*5+162/15=");
//			calculaList("3+4+3,4-7*1=");
//			//calculaList("25+43-37*88/9,9=");
//		} catch (CalculadoraException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		ficheros();
	}

	public void run() {
		naipes();
	}

	public void juego() {

		int numeroBuscado = (new Random()).nextInt(100) + 1;
		int numeroIntroducido;
		int intentos = 0;
		boolean encontrado = false;
		do {
			try {
				intentos += 1;
				System.out.print("Dame tu número del 1 al 100 (" + intentos + " de 10): ");
//				System.out.print("[" + numeroBuscado + "]: ");
				numeroIntroducido = Integer.parseInt(teclado.nextLine());
				if (numeroBuscado == numeroIntroducido) {
					encontrado = true;
				} else if (numeroBuscado > numeroIntroducido) {
					System.out.println("Mi número es mayor.");
				} else {
					System.out.println("Mi número es menor.");
				}

			} catch (NumberFormatException e) {
				System.out.println("No es un número.");
			}
		} while (intentos < 10 && !encontrado);
		if (encontrado) {
			System.out.println("Bieeen!!! Acertaste.");
		} else {
			System.out.println("Upsss! Se acabaron los intentos, el número era el " + numeroBuscado);
		}

//		teclado.close();
	}

	public void juegoConClase() {
		Scanner teclado = new Scanner(System.in);

		try {
			Juego<String> juego = new com.example.juegos.numero.JuegoDelNumero();
			juego.inicializar();
			((com.example.juegos.numero.JuegoDelNumero)juego).setNotifica(arg -> {
				System.out.println("NOTIFICA: " + arg.getMsg());
				System.out.println("¿Qieres cancelar?:");
				arg.setCancel(teclado.nextLine().equals("s"));
			});
			for (int intentos = 1; intentos <= 10; intentos++) {
				System.out.print("Dame tu número del 1 al 100 (" + (juego.getJugada() + 1) + " de 10): ");
//				try {
				juego.jugada(teclado.nextLine());
				System.out.println(juego.getResultado());
				if (juego.getFinalizado())
					break;
//				} catch (JuegoException e) {
//					if (e.getCause() instanceof NumberFormatException)
//						System.out.println(e.getMessage());
//					else
//						throw e;
//				}
			}
		} catch (JuegoException e) {
			e.printStackTrace();
		}

		teclado.close();
	}

	public static void decode(String expresion) {
		if (expresion == null || "".equals(expresion) || !Character.isDigit(expresion.charAt(0)))
			throw new java.lang.IllegalArgumentException("No es una expresión valida");
		String operando = "";
		for (int i = 0; i < expresion.length(); i++) {
			char ch = expresion.charAt(i);
			if (Character.isDigit(ch)) {
				operando += ch;
			} else if (ch == ',') {
				if (operando.indexOf(ch) == -1) {
					operando += ch;
				} else {
					// throw new Exception("Ya existe separador decimal.");
				}
			} else if ("+-*/%=".indexOf(ch) >= 0) {
				if (operando.endsWith(",")) {
					operando += "0";
				}
				System.out.println(operando + " " + ch);
				if (ch == '=') {
					break;
				}
				operando = "";
			} else if (ch != ' ') {
//				throw new Exception("Carácter no valido.");
			}
		}
	}

	public static double calcula(String expresion) throws CalculadoraException, Exception {
		if (expresion == null || "".equals(expresion) || !Character.isDigit(expresion.charAt(0)))
			throw new java.lang.IllegalArgumentException("No es una expresión valida");
		String operando = "";
		Calculadora calculadora = new Calculadora();
		for (int i = 0; i < expresion.length(); i++) {
			char ch = expresion.charAt(i);
			if (Character.isDigit(ch)) {
				operando += ch;
			} else if (ch == ',') {
				if (operando.indexOf(ch) == -1) {
					operando += ch;
				} else {
					// throw new Exception("Ya existe separador decimal.");
				}
			} else if ("+-*/%=".indexOf(ch) >= 0) {
				if (operando.endsWith(",")) {
					operando += "0";
				}
				calculadora.calcula(operando, ch);
				System.out.println(operando + "\t" + ch + "\t" + calculadora.getAcumulado());
				if (ch == '=') {
					break;
				}
				operando = "";
			} else if (ch != ' ') {
//				throw new Exception("Carácter no valido.");
			}
		}
		System.out.println(calculadora.getAcumulado());
		return calculadora.getAcumulado();
	}

	public static List<Calculadora.Operacion> decodeToList(String expresion) {
		if (expresion == null || "".equals(expresion) || !Character.isDigit(expresion.charAt(0)))
			throw new java.lang.IllegalArgumentException("No es una expresión valida");
		List<Calculadora.Operacion> resulatado = new ArrayList<>();
		String operando = "";
		for (int i = 0; i < expresion.length(); i++) {
			char ch = expresion.charAt(i);
			if (Character.isDigit(ch)) {
				operando += ch;
			} else if (ch == ',') {
				if (operando.indexOf(ch) == -1) {
					operando += ch;
				} else {
					// throw new Exception("Ya existe separador decimal.");
				}
			} else if ("+-*/%=".indexOf(ch) >= 0) {
				if (operando.endsWith(",")) {
					operando += "0";
				}
				resulatado.add(new Calculadora.Operacion(operando, ch));
				if (ch == '=') {
					break;
				}
				operando = "";
			} else if (ch != ' ') {
//				throw new Exception("Carácter no valido.");
			}
		}
		return resulatado;

	}

	public static void calculaList(String expresion) throws CalculadoraException, Exception {
		try {
			var operaciones = decodeToList(expresion);
			for (Calculadora.Operacion operacion : operaciones) {
				System.out.println(operacion.getOperando() + " " + operacion.getOperador());
			}
			System.out.println((new Calculadora()).calcula(operaciones));
		} catch (CalculadoraException e) {
			e.printStackTrace();
		}
	}

	public static void ficheros() {
		String fileIn = "C:\\Archivos\\cursos\\BOOT20220125\\repositorio\\backend\\soluciones1\\ficheros\\Entrada.txt";
		String fileOut = "C:\\Archivos\\cursos\\BOOT20220125\\repositorio\\backend\\soluciones1\\ficheros\\Salida.txt";
		try {
			Path entada = Paths.get(fileIn), salida = Paths.get(fileOut);
			if(Files.exists(salida))
				Files.delete(salida);
			Files.createFile(salida);

			List<String> lines;
			lines = Files.readAllLines(entada, StandardCharsets.UTF_8);
			for (String line : lines) {
				try {
					System.out.println("Entrada: " + line);
					System.out.println("Salida:");
					var operaciones = decodeToList(line);
					for (Calculadora.Operacion operacion : operaciones) {
						System.out.print("\t" + operacion.getOperando() + "\n" + (operacion.getOperador() == '=' ? "--------------------------\n" : operacion.getOperador()));
						Files.writeString(salida, operacion.getOperando() + "\n" + (operacion.getOperador() == '=' ? "--------------------------\n" : operacion.getOperador()), StandardOpenOption.APPEND);
					}
					System.out.println("\t" + (new Calculadora()).calcula(operaciones));
					Files.writeString(salida, (new Calculadora()).calcula(operaciones) + "\n\n", StandardOpenOption.APPEND);
				} catch (CalculadoraException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void naipes() {
		var b = new BarajaFrancesa();

		try {
			System.out.println("Baraja\n-------------------------------");
			for (var c : b.getCartas())
				System.out.println(c);
			System.out.println("\nMazo\n-------------------------------");
			b.barajar();
			b.getMazo().forEach(System.out::println);
			b.reparte(4, 5).forEach(item -> {
				System.out.println("\nJugador\n-------------------------------");
				item.forEach(System.out::println);
			});
			System.out.println("\nQuedan " + b.getMazo().size());
			var mano = b.reparte(1, 2);
			System.out.println("\nQuedan " + b.getMazo().size());
			b.reparte(4, 5).forEach(item -> {
				System.out.println("\nJugador\n-------------------------------");
				item.forEach(System.out::println);
			});
			System.out.println("\nQuedan " + b.getMazo().size());
			mano.forEach(item -> {
				System.out.println("\nJugador\n-------------------------------");
				item.forEach(System.out::println);
			});
			System.out.println("\nQuedan " + b.getMazo().size());
			b.apilar(mano.get(0));
			b.getMazo().forEach(System.out::println);
			// b.apilar(mano.get(0));
			// b.apilar(List.of(new NaipeFrances(NaipeFrances.Palos.CORAZONES, (byte)1)));
			System.out.println("\nQuedan " + b.getMazo().size());
			System.out.println(ValorNaipe.REINA.valorNumerico);
			System.out.println(ValorNaipe.toEnum(12));
		} catch (JuegoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void ajedrez() {
		Juego<Tablero> juego = new Ajedrez(e -> pidePieza(e));
		juego.inicializar();
		do {
			try {
				pintaTablero(juego.getResultado());
				System.out.print("Juegan las " + (((Ajedrez) juego).getTurno() == Color.BLANCO ? "blancas" : "negras")
						+ ". Dame jugada [CFCF]: ");
				juego.jugada(teclado.nextLine().toUpperCase());
			} catch (JuegoException e) {
				System.out.println(e.getMessage());
			}
		} while (!juego.getFinalizado());
		System.out.println("Juego Finalizado");
	}

	private void pintaTablero(Tablero t) {
		for (int f = 8; f > 0; f--) {
			System.out.print(String.format("%2s ", f));
			for (int c = 1; c <= 8; c++) {
				if (t.hayPieza(f, c))
					System.out.print(String.format("%10s ", t.getPieza(f, c)));
				else
					System.out.print(Tablero.colorEscaque(f, c) == Color.BLANCO ? "            " : "-----------");
			}
			System.out.println();
		}
		for (char c = 'A'; c <= 'H'; c++) {
			System.out.print(String.format("%8c    ", c));
		}
		System.out.println();
	}
	
	private Pieza pidePieza(PromocionEventArgs e) {
		System.out.print("\t1: Dama\n\t2: Alfil\n\t3: Torre\n\t4: Caballo\n\t5: Cancelar\n"
				+ "Dame la opción para promocionar el peón " + (e.getColor() == Color.BLANCO ? "blanco: " : "negro:"));
		switch (Integer.parseInt(teclado.nextLine())) {
		case 1:
			return new Dama(e.getColor());
		case 2:
			return new Alfil(e.getColor());
		case 3:
			return new Torre(e.getColor());
		case 4:
			return new Caballo(e.getColor());
		case 5:
			e.setCancel(true);
		default:
			return null;
		}
	}
	
	public String kk(String nombre) {
		if(nombre == null)
			throw new IllegalArgumentException("No puede ser nulo");
		return "Hola " + nombre;
	}

}
