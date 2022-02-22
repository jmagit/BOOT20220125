package com.example.ioc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Dependencia {
	@Value("${mi.config.name:'no se'}")
	private String nombre;
	
	public Dependencia() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return nombre;
	}
}
