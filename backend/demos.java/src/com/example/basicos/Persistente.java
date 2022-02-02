package com.example.basicos;

public interface Persistente {
	void guardar();
	void leer();
	void close();
	default void saluda( ) { System.out.println("Adios mundo");}

}
