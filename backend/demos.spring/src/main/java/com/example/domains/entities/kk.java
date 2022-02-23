package com.example.domains.entities;

public class kk {

	class Tarjeta {
		int num;
		String titular;
		
	}
	
	class Debito extends Tarjeta {
		int limite;
	}
	class Credito extends Tarjeta {
		int maximo;
	}

}
