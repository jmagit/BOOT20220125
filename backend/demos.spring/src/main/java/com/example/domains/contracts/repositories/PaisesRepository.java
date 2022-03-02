package com.example.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.domains.entities.Country;

@RepositoryRestResource(exported = false)
public interface PaisesRepository extends JpaRepository<Country, Integer> {
	<T> List<T> findByCountryIdIsNotNull(Class<T> type);
	<T> Iterable<T> findByCountryIdIsNotNull(Sort sort, Class<T> type);
	<T> Page<T> findByCountryIdIsNotNull(Pageable pageable, Class<T> type);

}
