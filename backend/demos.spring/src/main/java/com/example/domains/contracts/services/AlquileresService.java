package com.example.domains.contracts.services;

import java.util.List;

import com.example.domains.entities.Rental;
import com.example.domains.entities.projections.AlquilerRetrasado;
import com.example.exceptions.NotFoundException;

public interface AlquileresService extends ProjectionDomainService<Rental, Integer> {
	<T> T getOne(Integer id, Class<T> type) throws NotFoundException;
	List<AlquilerRetrasado> getFueraDePlazo();
}
