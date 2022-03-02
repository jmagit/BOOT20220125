package com.example.domains.entities.projections;

import org.springframework.data.rest.core.config.Projection;

import com.example.domains.entities.Staff;

@Projection(name="Empleado", types = Staff.class)
public interface EmpleadoProjetion {
	byte getStaffId();
	String getFirstName();
	String getLastName();
	String getEmail();
	byte getActive();
}
