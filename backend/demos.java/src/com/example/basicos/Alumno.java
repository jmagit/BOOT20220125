package com.example.basicos;

import java.util.Arrays;
import java.util.Date;

public class Alumno extends Persona {
	private double[] notas;
	
	public Alumno(int id, String nombre, String apellidos) {
		super(id, nombre, apellidos);
	}

	@Override
	public void veteAComer() {
		// TODO Auto-generated method stub
		
	}

	public void ponNotas(double nota) {
		if(notas == null) {
			notas = new double[1];
			notas[0] = nota;
			notas = new double[]{ nota };
		} else {
			double[] aux = new double[notas.length + 1];
			for(int i = 0; i < notas.length; i++) aux[i] = notas[i];
			aux[notas.length] = nota;
			notas = aux;
		}
		
	}
	public double dameNotas() {
		return 0;
	}

	@Override
	public void duermete() {
		System.out.println("No quiero");
	}
	
}