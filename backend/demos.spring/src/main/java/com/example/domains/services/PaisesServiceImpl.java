package com.example.domains.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.domains.contracts.repositories.ActorRepositoy;
import com.example.domains.contracts.repositories.PaisesRepository;
import com.example.domains.contracts.services.PaisesService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.Country;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

@Service
public class PaisesServiceImpl implements PaisesService {
	private PaisesRepository dao;
	
	public PaisesServiceImpl(PaisesRepository dao) {
		this.dao = dao;
	}
	
	@Override
	public List<Country> getAll() {
		return dao.findAll();
	}
	
	@Override
	public Iterable<Country> getAll(Sort sort) {
		return dao.findAll(sort);
	}
	@Override
	public Page<Country> getAll(Pageable pageable) {
		return dao.findAll(pageable);
	}
	
	@Override
	public <T> List<T> getByProjection(Class<T> type) {
		return dao.findByCountryIdIsNotNull(type);
	}

	@Override
	public <T> Iterable<T> getByProjection(Sort sort, Class<T> type) {
		return dao.findByCountryIdIsNotNull(sort, type);
	}

	@Override
	public <T> Page<T> getByProjection(Pageable pageable, Class<T> type) {
		return dao.findByCountryIdIsNotNull(pageable, type);
	}

	@Override
	public Country getOne(Integer id) throws NotFoundException {
		var item = dao.findById(id);
		if(item.isEmpty())
			throw new NotFoundException();
		return item.get();
	}
	
	@Override
	public Country add(Country item) throws DuplicateKeyException, InvalidDataException {
		if(item == null)
			throw new IllegalArgumentException();
		if(dao.findById(item.getCountryId()).isPresent())
			throw new DuplicateKeyException();
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}
	@Override
	public Country change(Country item) throws NotFoundException, InvalidDataException  {
		if(item == null)
			throw new IllegalArgumentException();
		if(dao.findById(item.getCountryId()).isEmpty())
			throw new NotFoundException();
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}
	@Override
	public void delete(Country item) {
		if(item == null)
			throw new IllegalArgumentException();
		deleteById(item.getCountryId());
		
	}
	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}
}
