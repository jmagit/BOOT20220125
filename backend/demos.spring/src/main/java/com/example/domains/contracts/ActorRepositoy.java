package com.example.domains.contracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domains.entities.Actor;

public interface ActorRepositoy extends JpaRepository<Actor, Integer> {
	List<Actor> findByFirstName(String nombre);
	List<Actor> findByFirstNameStartingWithAndLastNameEndingWith(String prefijo, String sufijo);
}
