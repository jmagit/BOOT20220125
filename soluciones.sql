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

CREATE TABLE `contactos` (
	`idContacto` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`nombre` VARCHAR(50) NOT NULL COLLATE 'latin1_spanish_ci',
	`apellidos` VARCHAR(100) NULL DEFAULT NULL COLLATE 'latin1_spanish_ci',
	`fechaNacimiento` DATE NULL DEFAULT NULL,
	`activo` BIT(1) NOT NULL DEFAULT 'b\'1\'',
	PRIMARY KEY (`idContacto`) USING BTREE
)
COLLATE='latin1_spanish_ci'
ENGINE=InnoDB
AUTO_INCREMENT=35
;

CREATE TABLE `telefonos` (
	`idTelefono` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`telefono` VARCHAR(20) NOT NULL COLLATE 'latin1_spanish_ci',
	`idContacto` INT(10) UNSIGNED NOT NULL,
	PRIMARY KEY (`idTelefono`) USING BTREE,
	INDEX `FK__contactos` (`idContacto`) USING BTREE,
	CONSTRAINT `FK__contactos` FOREIGN KEY (`idContacto`) REFERENCES `demos`.`contactos` (`idContacto`) ON UPDATE NO ACTION ON DELETE CASCADE
)
COLLATE='latin1_spanish_ci'
ENGINE=InnoDB
;

insert into contactos (nombre, apellidos, fechaNacimiento) values ('Ania', 'Foresight', '2010-12-11 04:57:52');
insert into contactos (nombre, apellidos, fechaNacimiento) values ('Hyacintha', 'Tamburo', '2018-11-06 22:59:38');
insert into contactos (nombre, apellidos, fechaNacimiento) values ('Cam', 'Frichley', '2009-05-21 01:02:05');
insert into contactos (nombre, apellidos, fechaNacimiento) values ('Augy', 'MacGowing', '1977-08-05 12:12:12');
insert into contactos (nombre, apellidos, fechaNacimiento) values ('Ebba', 'Lasty', '2005-03-25 07:17:30');
insert into contactos (nombre, apellidos, fechaNacimiento) values ('Kalina', 'Phizakarley', '2010-04-02 22:24:26');
insert into contactos (nombre, apellidos, fechaNacimiento) values ('Donall', 'Muirhead', '1981-12-23 03:29:22');
insert into contactos (nombre, apellidos, fechaNacimiento) values ('Angie', 'Bloxsom', '1988-04-12 12:43:08');
insert into contactos (nombre, apellidos, fechaNacimiento) values ('Jerrilyn', 'Blampy', '1973-04-01 17:04:56');
insert into contactos (nombre, apellidos, fechaNacimiento) values ('Bent', 'Seymark', '1979-06-14 19:49:22');
insert into contactos (nombre, apellidos, fechaNacimiento) values ('Sella', 'Lainton', '1974-04-08 03:45:12');
insert into contactos (nombre, apellidos, fechaNacimiento) values ('Max', 'Hatchell', '1981-09-08 20:48:32');
insert into contactos (nombre, apellidos, fechaNacimiento) values ('Phedra', 'Colvill', '2017-04-12 12:07:03');
insert into contactos (nombre, apellidos, fechaNacimiento) values ('Alameda', 'Maffin', '1972-06-17 14:38:30');
insert into contactos (nombre, apellidos, fechaNacimiento) values ('Alden', 'Harring', '1990-08-09 18:28:57');
insert into contactos (nombre, apellidos, fechaNacimiento) values ('Karmen', 'Foote', '1983-05-31 23:20:23');
insert into contactos (nombre, apellidos, fechaNacimiento) values ('Lauretta', 'Rowsell', '2011-06-07 07:08:44');
insert into contactos (nombre, apellidos, fechaNacimiento) values ('Hewie', 'Jovicevic', '2016-11-13 19:37:19');
insert into contactos (nombre, apellidos, fechaNacimiento) values ('Larina', 'Heazel', '2004-05-18 00:25:48');
insert into contactos (nombre, apellidos, fechaNacimiento) values ('Virgie', 'Vanni', '1972-11-01 08:46:11');
insert into contactos (nombre, apellidos, fechaNacimiento) values ('Dalenna', 'Hallatt', '1970-05-16 08:12:16');
insert into contactos (nombre, apellidos, fechaNacimiento) values ('Andris', 'Kures', '1972-09-06 15:13:16');
insert into contactos (nombre, apellidos, fechaNacimiento) values ('Damon', 'Bisiker', '1993-10-21 18:47:27');
insert into contactos (nombre, apellidos, fechaNacimiento) values ('Darnall', 'Gladbeck', '1975-10-02 14:42:38');
insert into contactos (nombre, apellidos, fechaNacimiento) values ('Christie', 'MacAirt', '1985-04-26 13:09:55');
insert into contactos (nombre, apellidos, fechaNacimiento) values ('Rolf', 'Carloni', '1995-11-23 02:54:22');
insert into contactos (nombre, apellidos, fechaNacimiento) values ('Burtie', 'Soro', '2004-05-23 04:41:38');
insert into contactos (nombre, apellidos, fechaNacimiento) values ('Eduard', 'Longhi', '1992-03-17 17:38:00');
