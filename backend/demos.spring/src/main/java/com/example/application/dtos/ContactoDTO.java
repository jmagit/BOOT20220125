package com.example.application.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ContactoDTO {
	private int id;
	private String tratamiento;
	private String nombre;
	private String apellidos;
	private String telefono;
	private String email;
	private String sexo;
	private LocalDate nacimiento;
	private String avatar;
	private boolean conflictivo = false;
}
