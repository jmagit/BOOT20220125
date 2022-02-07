package com.example.basicos;

public class Profesor extends Persona {
	private double salario;
	
	public Profesor(int id, String nombre) {
		super(id, nombre);
		// TODO Auto-generated constructor stub
	}

	public Profesor(int id, String nombre, String apellidos, String fechaNacimiento, double salario) {
		super(id, nombre, apellidos, fechaNacimiento);
		this.salario = salario;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	@Override
	public void veteAComer() {
		// TODO Auto-generated method stub

	}

}
