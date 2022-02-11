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
