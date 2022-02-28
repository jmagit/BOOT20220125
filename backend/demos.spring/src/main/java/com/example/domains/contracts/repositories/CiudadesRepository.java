package com.example.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domains.entities.City;
import com.example.domains.entities.Country;

public interface CiudadesRepository extends JpaRepository<City, Integer> {
	<T> List<T> findByCityIdIsNotNull(Class<T> type);
	<T> Iterable<T> findByCityIdIsNotNull(Sort sort, Class<T> type);
	<T> Page<T> findByCityIdIsNotNull(Pageable pageable, Class<T> type);

}
