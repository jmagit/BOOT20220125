package com.example.application.dtos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = {"id","fechaAlquiler","pelicula","cliente","empleado","pendiente","fechaDevolucion"})
public interface AlquilerShortDTO {
	@JsonProperty("id")
	int getRentalId();
	@JsonProperty("fechaAlquiler")
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	Date getRentalDate();
	@Value("#{target.inventory.film.title}")
	String getPelicula();
	@Value("#{target.customer.lastName + ', ' + target.customer.firstName}")
	String getCliente();
	@Value("#{target.staff.lastName + ', ' + target.staff.firstName}")
	String getEmpleado();
	@Value("#{target.returnDate == null}")
	boolean getPendiente();
	@JsonProperty("fechaDevolucion")
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	Date getReturnDate();
}
