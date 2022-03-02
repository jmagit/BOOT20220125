package com.example.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.domains.entities.Actor;

@RepositoryRestResource(exported = false)
public interface ActorRepositoy extends JpaRepository<Actor, Integer> {
	List<Actor> findByFirstName(String nombre);
	List<Actor> findByFirstNameStartingWithAndLastNameEndingWith(String prefijo, String sufijo);

	<T> List<T> findByActorIdIsNotNull(Class<T> type);
	<T> Iterable<T> findByActorIdIsNotNull(Sort sort, Class<T> type);
	<T> Page<T> findByActorIdIsNotNull(Pageable pageable, Class<T> type);
}
