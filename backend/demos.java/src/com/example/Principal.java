/**
 * 
 */
package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Ejemplo de la sintaxis
 * 
 * @author Javier
 *
 */
public class Principal {

	/**
	 * Método principal
	 * 
	 * @param args Lista de argumentos
	 * @return 0 si termina bien
	 */
	public static void main(String[] args) {
		Object o = null;
		
		Factura f = new Factura(1);
		try {
			f.addLinea(null, 0, 9.0);
//		} catch (IllegalArgumentException e) {
//			System.out.println("ERRROR: " + e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(f.getEstado() == Factura.Estado.PENDIENTE) {
			f.cancelar();
		}
		f.guardar();
		
		Grafico grafico = f;
		//grafico = new CalculadoraCientifica();
		
		grafico.pintate();
		if(f instanceof Factura)
			try {
				((Factura)grafico).addLinea("Comida", 1, 9.0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		if(o instanceof Grafico)
			((Grafico)o).pintate();
		
		
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
