SELECT * FROM actor WHERE first_name = 'nick';

SELECT DISTINCT district FROM address ORDER BY district;

SELECT * FROM film WHERE description LIKE '%Secret Agent%';

SELECT title, rental_rate, LENGTH, rental_rate / LENGTH coste
FROM film 
ORDER BY coste DESC

SELECT customer_id, AVG(amount) media, SUM(amount) gasto, COUNT(*) pagos
FROM payment 
GROUP BY customer_id
HAVING SUM(amount) > 100 AND COUNT(*) < 25
ORDER BY media desc, gasto, pagos;

SELECT first_name nombre, COUNT(*) apariciones
FROM actor 
GROUP BY first_name
ORDER BY apariciones DESC
LIMIT 5;

SELECT CONCAT(c.first_name, ' ', c.last_name) nombre, a.address, a.postal_code, city.city, p.country
FROM customer c 
	INNER JOIN address a ON c.address_id = a.address_id
	INNER JOIN city ON a.city_id = city.city_id
	INNER JOIN country p ON city.country_id = p.country_id
WHERE country IN ('China', 'Taiwan')
ORDER BY nombre

SELECT rental_id, title pelicula, rental_date alquilada, return_date devuelta, DATEDIFF(return_date, rental_date) tiempo,
	CONCAT(c.first_name, ' ', c.last_name) cliente, 
	CONCAT(e.first_name, ' ', e.last_name) empleado,
	CONCAT(l.city, ', ', p.country) tienda	
FROM rental r
	INNER JOIN customer c ON r.customer_id = c.customer_id
	INNER JOIN staff e ON r.staff_id = e.staff_id
	INNER JOIN inventory i ON r.inventory_id = i.inventory_id
	INNER JOIN film f ON i.film_id = f.film_id
	INNER JOIN store t ON i.store_id = t.store_id
	INNER JOIN address a ON t.address_id = a.address_id
	INNER JOIN city l ON a.city_id = l.city_id
	INNER JOIN country p ON l.country_id = p.country_id

SELECT t.store_id codigo, CONCAT(l.city, ', ', p.country) tienda,
	CONCAT(e.first_name, ' ', e.last_name) jefe,
	(SELECT COUNT(i.film_id) FROM inventory i WHERE i.store_id = t.store_id) peliculas,
	(SELECT COUNT(*) FROM (
		SELECT DISTINCT i.film_id
		FROM inventory i
		WHERE i.store_id = t.store_id
	) td) titulos,
	(SELECT COUNT(*) FROM (
		SELECT DISTINCT r.customer_id
		FROM inventory i
			INNER JOIN rental r ON i.inventory_id = r.inventory_id
		WHERE i.store_id = t.store_id
	) td) clientes
FROM store t
	INNER JOIN staff e ON t.manager_staff_id = e.staff_id
	INNER JOIN address a ON t.address_id = a.address_id
	INNER JOIN city l ON a.city_id = l.city_id
	INNER JOIN country p ON l.country_id = p.country_id

SELECT r.rental_date alquiler, r.rental_date + INTERVAL f.rental_duration DAY vencimiento, a.phone telefono, CONCAT(c.first_name, ' ', c.last_name) AS cliente,
           f.title pelicula
FROM rental r
	INNER JOIN customer c ON r.customer_id = c.customer_id
	INNER JOIN address a ON c.address_id = a.address_id
	INNER JOIN inventory i ON r.inventory_id = i.inventory_id
	INNER JOIN film f ON i.film_id = f.film_id
WHERE r.return_date IS NULL
	AND rental_date + INTERVAL f.rental_duration DAY < CURRENT_DATE()
ORDER BY vencimiento, alquiler

SELECT RANK() OVER(ORDER BY COUNT(*) DESC) ranking, p.country pais, COUNT(*) alquileres
FROM actor 
	INNER JOIN film_actor f ON actor.actor_id = f.actor_id
	INNER JOIN inventory i ON f.film_id = i.film_id
	INNER JOIN rental r ON i.inventory_id = r.inventory_id
	INNER JOIN customer c ON r.customer_id = c.customer_id
	INNER JOIN address a ON c.address_id = a.address_id
	INNER JOIN city l ON a.city_id = l.city_id
	INNER JOIN country p ON l.country_id = p.country_id
WHERE actor.first_name = 'GINA' AND actor.last_name = 'DEGENERES'
GROUP BY p.country
ORDER BY ranking, pais

