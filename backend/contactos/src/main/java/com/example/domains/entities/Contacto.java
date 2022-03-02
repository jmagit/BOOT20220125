package com.example.domains.entities;

import java.time.LocalDate;
import java.util.Objects;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Document("contactos")
@ApiModel(value = "Contacto", description = "Datos de la persona de contacto")
public class Contacto {
	@Id
	@Field("_id")
	private int id;
	@ApiModelProperty(name = "Esto es un ejemplo", value = "Maximo 3 letras")
	private String tratamiento;
	@NotBlank
	@Length(min=2, max=50)
	private String nombre;
	@Length(min=2, max=50)
	private String apellidos;
	@Digits(integer = 9, fraction = 0)
	private String telefono;
	@Email
	private String email;
	@Pattern(regexp = "[HM]")
	private String sexo;
	@Past
	private LocalDate nacimiento;
	@URL
	private String avatar;
	private boolean conflictivo = false;

	public Contacto() {
		super();
	}
	public Contacto(int id, String tratamiento, @NotBlank @Length(min = 2, max = 2) String nombre,
			@Length(min = 2, max = 2) String apellidos, @Digits(integer = 9, fraction = 0) String telefono,
			@Email String email, @Pattern(regexp = "[HM]") String sexo, @Past LocalDate nacimiento, @URL String avatar,
			boolean conflictivo) {
		super();
		this.id = id;
		this.tratamiento = tratamiento;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.email = email;
		this.sexo = sexo;
		this.nacimiento = nacimiento;
		this.avatar = avatar;
		this.conflictivo = conflictivo;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTratamiento() {
		return tratamiento;
	}
	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public LocalDate getNacimiento() {
		return nacimiento;
	}
	public void setNacimiento(LocalDate nacimiento) {
		this.nacimiento = nacimiento;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public boolean isConflictivo() {
		return conflictivo;
	}
	public void setConflictivo(boolean conflictivo) {
		this.conflictivo = conflictivo;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contacto other = (Contacto) obj;
		return id == other.id;
	}
	@Override
	public String toString() {
		return "Contacto [id=" + id + ", tratamiento=" + tratamiento + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", telefono=" + telefono + ", email=" + email + ", sexo=" + sexo + ", nacimiento=" + nacimiento
				+ ", avatar=" + avatar + ", conflictivo=" + conflictivo + "]";
	}	
		
}
