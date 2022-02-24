package com.example.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.application.dtos.ActorDTO;
import com.example.application.dtos.ActorShortDTO;
import com.example.domains.entities.Actor;

public interface ActorRepositoy extends JpaRepository<Actor, Integer> {
	List<Actor> findByFirstName(String nombre);
	List<Actor> findByFirstNameStartingWithAndLastNameEndingWith(String prefijo, String sufijo);

	<T> List<T> findByActorIdIsNotNull(Class<T> type);
	<T> Iterable<T> findByActorIdIsNotNull(Sort sort, Class<T> type);
	<T> Page<T> findByActorIdIsNotNull(Pageable pageable, Class<T> type);
}
