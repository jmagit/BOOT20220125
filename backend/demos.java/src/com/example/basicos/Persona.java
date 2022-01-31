package com.example.basicos;

public abstract class Persona implements AutoCloseable {
	private static int cont = 0;

	private int id = 0;
	private String nombre = "";
	private String apellidos;

	public Persona(int id, String nombre, String apellidos) {
		cont++;
		setId(id);
		setNombre(nombre);
		setApellidos(apellidos);
	}
	
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
		if(nombre == null) 
			throw new IllegalArgumentException("El nombre no puede estar vacio"); // Error
		if(this.nombre.equals(nombre)) return;
		this.nombre = nombre;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		if(apellidos == null) 
			throw new IllegalArgumentException("Los apellidos no puede estar vacio"); // Error
		if(this.apellidos.equals(apellidos)) return;
		this.apellidos = apellidos;
	}
	
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

	private void duermete() {
		System.out.println("ZZZZZ");
	}
	
	public abstract void veteAComer();
}
