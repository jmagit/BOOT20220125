package com.example.domains.contracts.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.domains.entities.Rental;
import com.example.domains.entities.projections.AlquilerRetrasado;

@RepositoryRestResource(exported = false)
public interface AlquileresRepository extends JpaRepository<Rental, Integer> {
	<T> List<T> findByRentalIdIsNotNull(Class<T> type);
	<T> Iterable<T> findByRentalIdIsNotNull(Sort sort, Class<T> type);
	<T> Page<T> findByRentalIdIsNotNull(Pageable pageable, Class<T> type);

	<T> Optional<T> findByRentalId(Integer id, Class<T> type);
	
	@Query(value = """
SELECT r.rental_id id, r.rental_date alquiler, r.rental_date + INTERVAL f.rental_duration DAY vencimiento, a.phone telefono, CONCAT(c.first_name, ' ', c.last_name) AS cliente,
           f.title pelicula
FROM rental r
	INNER JOIN customer c ON r.customer_id = c.customer_id
	INNER JOIN address a ON c.address_id = a.address_id
	INNER JOIN inventory i ON r.inventory_id = i.inventory_id
	INNER JOIN film f ON i.film_id = f.film_id
WHERE r.return_date IS NULL
	AND rental_date + INTERVAL f.rental_duration DAY < CURRENT_DATE()
ORDER BY vencimiento, alquiler
			""", nativeQuery = true)
	List<AlquilerRetrasado> getFueraDePlazo();
}
