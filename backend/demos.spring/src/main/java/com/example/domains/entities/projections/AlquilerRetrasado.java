package com.example.domains.entities.projections;

import java.util.Date;
import java.util.List;

import com.example.domains.entities.Customer;
import com.example.domains.entities.Inventory;
import com.example.domains.entities.Rental;
import com.example.domains.entities.Staff;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Value;

public interface AlquilerRetrasado {
	int getId();
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	Date getAlquiler();
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	Date getVencimiento();
	String getTelefono();
	String getCliente();
	String getPelicula();
}
