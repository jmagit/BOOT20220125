SELECT title, case rental_rate 
	when 0.99 then 'baja' 
	when 2.99 then 'media' 
	when 4.99 then 'alta'
	ELSE 'desconocida'
	END gama
FROM film

SELECT title, rental_rate, LENGTH,  case  
	when rental_rate < 1 OR LENGTH < 60 then 'baja' 
	when rental_rate < 3 AND LENGTH BETWEEN 60 AND 100 then 'media' 
	when rental_rate > 4 then 'alta'
	ELSE 'desconocida'
	END gama
FROM film
ORDER BY LENGTH, rental_rate --  DESC, title

SELECT if(GROUPING(rating) = 1, 'TOTAL', rating) Clasificacion, 
	if(GROUPING(rental_rate) = 1, 'SUBTOTAL', case rental_rate 
	when 0.99 then 'baja' 
	when 2.99 then 'media' 
	when 4.99 then 'alta'
	ELSE 'desconocida'
	END) gama, count(title) `Nº Peliculas`, AVG(LENGTH) `Duración media`
FROM film
GROUP BY rating, rental_rate WITH rollup
ORDER BY GROUPING(rating), rating, GROUPING(rental_rate), rental_rate

SELECT rating Clasificacion, 
	case rental_rate 
	when 0.99 then 'baja' 
	when 2.99 then 'media' 
	when 4.99 then 'alta'
	ELSE 'desconocida'
	END gama, count(title) `Nº Peliculas`, AVG(LENGTH) `Duración media`
FROM film
WHERE rating NOT IN ('g', 'pg')
GROUP BY rating, rental_rate
HAVING COUNT(*) > 0

SELECT title, rental_rate, 'Peli' origen
FROM film
WHERE rating NOT IN ('g', 'pg')
UNION
SELECT CONCAT(first_name, ' ', last_name) actor, 0, 'Actor'
FROM actor


t1: 2cx10
t2: 10cx100
t2: 3cx5

SELECT *
FOR staff empleados INNER JOIN staff jefes ON empleados.jefe_id = jefes.staff_id

SELECT title, f.rating, rental_rate, media
	FROM film f INNER JOIN 
		(SELECT rating, avg(rental_rate) media from film GROUP BY rating) m
	ON f.rating = m.rating
WHERE rental_rate > media

SELECT title, f.rating, rental_rate, 
	(SELECT avg(rental_rate) from film where rating = f.rating) media
	FROM film f 
WHERE rental_rate > (SELECT avg(rental_rate) from film where rating = f.rating)


SELECT ROW_NUMBER () over(ORDER  BY LENGTH) NumLinea, film_id,
title, LENGTH, NTILE(5) over(ORDER BY length)
FROM film
ORDER BY NumLinea

SELECT max(f.film_id), title, COUNT(a.actor_id) actores
FROM film f
	LEFT JOIN film_actor fa ON f.film_id = fa.film_id
	LEFT JOIN actor a ON a.actor_id = fa.actor_id
WHERE a.first_name LIKE 'P%'
GROUP BY title
HAVING COUNT(a.actor_id) > 1
ORDER BY actores DESC 


