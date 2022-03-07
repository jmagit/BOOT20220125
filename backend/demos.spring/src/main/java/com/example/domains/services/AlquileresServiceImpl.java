package com.example.domains.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.domains.contracts.repositories.AlquileresRepository;
import com.example.domains.contracts.services.AlquileresService;
import com.example.domains.entities.Rental;
import com.example.domains.entities.projections.AlquilerRetrasado;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

@Service
public class AlquileresServiceImpl implements AlquileresService {
	private AlquileresRepository dao;
	
	public AlquileresServiceImpl(AlquileresRepository dao) {
		this.dao = dao;
	}
	
	@Override
	public List<Rental> getAll() {
		return dao.findAll();
	}
	
	@Override
	public Iterable<Rental> getAll(Sort sort) {
		return dao.findAll(sort);
	}
	@Override
	public Page<Rental> getAll(Pageable pageable) {
		return dao.findAll(pageable);
	}
	
	@Override
	public <T> List<T> getByProjection(Class<T> type) {
		return dao.findByRentalIdIsNotNull(type);
	}

	@Override
	public <T> Iterable<T> getByProjection(Sort sort, Class<T> type) {
		return dao.findByRentalIdIsNotNull(sort, type);
	}

	@Override
	public <T> Page<T> getByProjection(Pageable pageable, Class<T> type) {
		return dao.findByRentalIdIsNotNull(pageable, type);
	}

	@Override
	public Rental getOne(Integer id) throws NotFoundException {
		var item = dao.findById(id);
		if(item.isEmpty())
			throw new NotFoundException();
		return item.get();
	}

	@Override
	public <T> T getOne(Integer id, Class<T> type) throws NotFoundException {
		var item = dao.findByRentalId(id, type);
		if(item.isEmpty())
			throw new NotFoundException();
		return item.get();
	}
	
	@Override
	public Rental add(Rental item) throws DuplicateKeyException, InvalidDataException {
		if(item == null)
			throw new IllegalArgumentException();
		if(dao.findById(item.getRentalId()).isPresent())
			throw new DuplicateKeyException();
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}
	@Override
	public Rental change(Rental item) throws NotFoundException, InvalidDataException  {
		if(item == null)
			throw new IllegalArgumentException();
		if(dao.findById(item.getRentalId()).isEmpty())
			throw new NotFoundException();
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}
	@Override
	public void delete(Rental item) {
		if(item == null)
			throw new IllegalArgumentException();
		deleteById(item.getRentalId());
		
	}
	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public List<AlquilerRetrasado> getFueraDePlazo() {
		return dao.getFueraDePlazo();
	}
}
