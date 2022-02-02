package com.example.basicos;

public interface Grafico {
	void pintate();
	void close();
	default void saluda( ) { System.out.println("Hola mundo"); pintate();}
}
