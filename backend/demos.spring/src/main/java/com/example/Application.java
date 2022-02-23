package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.domains.contracts.ActorRepositoy;
import com.example.domains.entities.Actor;
import com.example.ioc.Servicio;
import com.example.jdbc.ConsultaSQL;

import java.sql.Date;
import java.sql.Timestamp;

import javax.transaction.Transactional;

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
	
	@Autowired
	ActorRepositoy dao;
	
	@Override
	@Transactional
	public void run(String... args) throws Exception {
//		srv.run();
//		if(srv1 != null) srv1.run();
//		srv.setName("coña");
//		srv.run();
//		if(srv1 != null) srv1.run();
		
//		jdbc.run();
//		jdbc.conPlantilla();
		var a = new Actor("Pepito", "Grillo");
		dao.save(a);
		System.out.println(a);
		a.addFilmActor(1);
		a.addFilmActor(2);
		a.addFilmActor(14);
		dao.save(a);
		System.out.println(a);
//		dao.save(new Actor(206, "Grillo", "Pepito", new Timestamp(0)));
//		dao.deleteById(206);
//		dao.findAll().forEach(System.out::println);
//		System.err.println(dao.findById(1).get());
//		dao.findByFirstName("nick").forEach(System.out::println);
//		dao.findByFirstNameStartingWithAndLastNameEndingWith("n","s").forEach(System.out::println);
//		System.err.println(dao.getClass().getName());
		
//		var a = dao.findById(1).get();
//		a.getFilmActors().forEach(item -> System.out.println(item.getFilm().getTitle()));
	}


}
