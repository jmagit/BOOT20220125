package com.example.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuracion {

	@Bean
	@Qualifier("manual")
	public Servicio miServicio(Dependencia dep) {
//		System.out.println("Creando el servicio de " + dep.getName());
		return new ServicioMock();
	}
}
