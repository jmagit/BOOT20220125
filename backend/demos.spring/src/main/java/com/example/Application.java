package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.ioc.Servicio;
import com.example.jdbc.ConsultaSQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class Application implements CommandLineRunner { 

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Autowired
	@Qualifier("despliegue")
	Servicio srv;
	
	@Autowired(required = false)
	@Qualifier("manual")
	Servicio srv1;
	
	@Autowired
	ConsultaSQL jdbc;
	
	@Override
	public void run(String... args) throws Exception {
//		srv.run();
//		if(srv1 != null) srv1.run();
//		srv.setName("coña");
//		srv.run();
//		if(srv1 != null) srv1.run();
		
		jdbc.run();
		jdbc.conPlantilla();
	}


}
