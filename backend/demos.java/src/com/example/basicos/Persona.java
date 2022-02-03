package com.example.basicos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Autor(nombre = "Javi")
public abstract class Persona implements AutoCloseable, Serializable {
	private static int cont = 0;

	@Autor(nombre = "Yo mismo", version = 2)
	private int id = 0;
	private String nombre = "";
	private String apellidos;
	private LocalDate fechaNacimiento;
	protected Direccion direccion = new Direccion();
	
	private transient int edad;

	public Persona(int id, String nombre) {
		cont++;
		setId(id);
		setNombre(nombre);
	}
	
	public Persona(int id, String nombre, String apellidos, String fechaNacimiento) {
		cont++;
		setId(id);
		setNombre(nombre);
		setApellidos(apellidos);
		setFechaNacimiento(LocalDate.parse(fechaNacimiento));
	}
	
	@Autor(nombre = "Javier")
	public int getId() {
		return id;
	}
	protected void setId(int id) {
		if(id < 0)
			throw new IllegalArgumentException("El identificador no puede ser negativo"); // Error
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		if(nombre == null || "".equals(nombre)) 
			throw new IllegalArgumentException("El nombre no puede estar vacio"); // Error
		if(this.nombre.equals(nombre)) return;
		this.nombre = nombre;
	}
	
	public Optional<String> getApellidos() {
		return Optional.ofNullable(apellidos);
	}
	public void setApellidos(String apellidos) {
		if(apellidos == null) 
			throw new IllegalArgumentException("Los apellidos no puede estar vacio"); // Error
		if(apellidos.equals(this.apellidos)) return;
		this.apellidos = apellidos;
	}
	
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
		this.edad = calculaAños(fechaNacimiento);
	}

	public int getEdad() { return edad; }
	
	public static void dimeCuantos() { System.out.println("Personas " + cont); }

	@Override
	protected void finalize() throws Throwable {
		cont--;
		super.finalize();
	}
	
	@Override
	public void close() throws Exception {
		cont--;
	}

	public void duermete() {
		System.out.println("ZZZZZ");
	}
	
	public abstract void veteAComer();
	
	public static int calculaAños(LocalDate fechaNacimiento) {
		LocalDate hoy = LocalDate.now();
		return hoy.getYear() - fechaNacimiento.getYear() - (hoy.getDayOfYear() < fechaNacimiento.getDayOfYear() ? 1 : 0);
//		return hoy.getYear() - fechaNacimiento.getYear() - (hoy.getMonthValue() < fechaNacimiento.getMonthValue() || 
//				(hoy.getMonth() == fechaNacimiento.getMonth() && hoy.getDayOfMonth() < fechaNacimiento.getDayOfMonth()) ? 1 : 0);
	}

	public int dimeEdad() {
		return calculaAños(fechaNacimiento);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Persona))
			return false;
		return id == ((Persona) obj).id;
	}

	public String getCalle() {
		return direccion.getCalle();
	}
	
	public void setCalle(String nombre) {
		this.direccion.setCalle(nombre); 
	}

}
