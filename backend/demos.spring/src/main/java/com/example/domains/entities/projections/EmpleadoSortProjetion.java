package com.example.domains.entities.projections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.example.domains.entities.Staff;

@Projection(name="EmpleadoShort", types = Staff.class)
public interface EmpleadoSortProjetion {
	byte getStaffId();
	@Value("#{target.lastName + ', ' + target.firstName}")
	String getNombre();
}
