package com.example.basicos;

public class CalculadoraCientifica extends Calculadora implements Grafico {
	public CalculadoraCientifica() {
		this(0);
	}
	public CalculadoraCientifica(int i) {
		super(i);
	}
//	public double divide(double a, double b) {
//		return a / b;
//	}
	
//	@Override
	public int suma(int a, int b) {
		return a - b;
	}
	
	private int contador = 0;
	
	public void inicializa(int contador) {
		this.contador = contador;
		//otro.registra(this)
		
	}
	
	@Override
	protected void finalize() throws Throwable {
		System.out.println("Me destruyen");
		super.finalize();
	}
	@Override
	public void pintate() {
		System.out.println("Soy un calculadora cientifica");
	}
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

}
