package com.example.basicos;

import java.math.BigDecimal;

public class Calculadora {
	public static final int CONSTANTE = 1;
	final int readOnly;
	
	private static Calculadora calculadora = null;
	
	static {
		Calculadora calculadora = new Calculadora(1);
	}
	
	public static Calculadora getCurrent() {
		if(calculadora == null)
			calculadora = new Calculadora(0);
		return calculadora;
	}
	
	// private Calculadora() {}
	public Calculadora(int algo) {
		readOnly = algo;
	}
	
	public int suma(int a, int b) {
		return a + b;
	}
//	public int suma(int a) {
//		return a + a;
//	}
	public static double suma(double a, double b) {
		return (new BigDecimal(a + b)).setScale(16, CONSTANTE).doubleValue();
//		return (new BigDecimal(a)).add(new BigDecimal(b)).setScale(32, CONSTANTE).doubleValue();
	}
	
//	public double avg(double... resto) {
//		if(resto.length == 0) return 0;
//		if(resto.length == 1) return resto[0];
//		double acumula = 0.0;
//		for(int i=0; i < resto.length; acumula += resto[i++]);
//		//for(double valor: resto) acumula += valor;
//		return acumula / resto.length;
//	}
	public double avg(double primero, double... resto) {
		if(resto.length == 0) return primero;
		double acumula = primero;
		for(int i=0; i < resto.length; acumula += resto[i++]);
		//for(double valor: resto) acumula += valor;
		return acumula / resto.length;
	}

	public static Double resta(double operando1, double operando2) {
		return operando1 - operando2;
	}

	public static Double divide(double operando1, double operando2) {
		if(operando2 == 0)
			throw new ArithmeticException("/ by zero");
		return operando1 / operando2;
	}

	public static int divide(int operando1, int operando2) {
		return operando1 / operando2;
	}
}
