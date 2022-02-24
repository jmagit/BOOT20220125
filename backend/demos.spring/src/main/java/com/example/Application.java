package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.application.dtos.ActorDTO;
import com.example.domains.contracts.ActorRepositoy;
import com.example.domains.entities.Actor;
import com.example.ioc.Servicio;
import com.example.jdbc.ConsultaSQL;

import java.sql.Date;
import java.sql.Timestamp;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
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
	public void run(String... args) {
//		srv.run();
//		if(srv1 != null) srv1.run();
//		srv.setName("coña");
//		srv.run();
//		if(srv1 != null) srv1.run();
		
//		jdbc.run();
//		jdbc.conPlantilla();
		
//		try {
//			crud();
//		} catch (Exception ex) {
//			System.out.println("ERROR: " + ex.getMessage());
//		}
		
//		dao.save(new Actor(206, "Grillo", "Pepito", new Timestamp(0)));
//		dao.deleteById(206);
//		dao.findAll().forEach(System.out::println);
//		System.err.println(dao.findById(1).get());
//		dao.findByFirstName("nick").forEach(System.out::println);
//		dao.findByFirstNameStartingWithAndLastNameEndingWith("n","s").forEach(System.out::println);
//		System.err.println(dao.getClass().getName());
		
//		var a = dao.findById(1).get();
//		a.getFilmActors().forEach(item -> System.out.println(item.getFilm().getTitle()));
		var a = new Actor(206, "ggg", null, new Timestamp(0));
		if(a.isInvalid())
			System.out.println(a.getErrorsMessage());
		else 
			System.out.println("OK");
		//dao.save(a);
		a = dao.getById(1);
		ModelMapper modelMapper = new ModelMapper();
		var dto = modelMapper.map(a, ActorDTO.class);
		System.out.println(a);		
		System.out.println(dto);		
	}

	@Transactional
	private void crud() {
		System.out.println("Create <-----------------------");
		var a = new Actor("Pepito", "Grillo");
		dao.save(a);
		System.out.println(a);
		a.addFilmActor(1);
		a.addFilmActor(2);
		a.addFilmActor(14);
		dao.save(a);

		System.out.println("Read <-----------------------");
		var newId = a.getActorId();
		a = dao.getById(newId);
		System.out.println(a);
		a.getFilmActors().forEach(item -> System.out.println(item.getFilm().getTitle()));

		System.out.println("Update <-----------------------");
		a.setFirstName(a.getFirstName().toUpperCase());
		a.removeFilmActor(a.getFilmActors().get(1));
		a.addFilmActor(3);
		dao.save(a);
		// Recarga
		a = dao.getById(newId);
		System.out.println(a);
		a.getFilmActors().forEach(item -> System.out.println(item.getFilm().getTitle()));

		System.out.println("Delete <-----------------------");
		dao.deleteById(newId);
		if(dao.findById(newId).isEmpty())
			System.out.println("Ya no está");
		else
			System.out.println("ERROR: no se ha borrado");

		System.out.println("Fin CRUD <-----------------------");
	}

}
