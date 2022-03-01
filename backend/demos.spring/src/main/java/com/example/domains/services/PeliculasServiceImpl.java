package com.example.domains.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.domains.contracts.repositories.PeliculasRepository;
import com.example.domains.contracts.services.PeliculasService;
import com.example.domains.entities.Film;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

@Service
public class PeliculasServiceImpl implements PeliculasService {
	private PeliculasRepository dao;
	
	public PeliculasServiceImpl(PeliculasRepository dao) {
		this.dao = dao;
	}
	
	@Override
	public List<Film> getAll() {
		return dao.findAll();
	}
	
	@Override
	public Iterable<Film> getAll(Sort sort) {
		return dao.findAll(sort);
	}
	@Override
	public Page<Film> getAll(Pageable pageable) {
		return dao.findAll(pageable);
	}
	
	@Override
	public <T> List<T> getByProjection(Class<T> type) {
		return dao.findByFilmIdIsNotNull(type);
	}

	@Override
	public <T> Iterable<T> getByProjection(Sort sort, Class<T> type) {
		return dao.findByFilmIdIsNotNull(sort, type);
	}

	@Override
	public <T> Page<T> getByProjection(Pageable pageable, Class<T> type) {
		return dao.findByFilmIdIsNotNull(pageable, type);
	}

	@Override
	public Film getOne(Integer id) throws NotFoundException {
		var item = dao.findById(id);
		if(item.isEmpty())
			throw new NotFoundException();
		return item.get();
	}
	
	@Override
	public Film add(Film item) throws DuplicateKeyException, InvalidDataException {
		if(item == null)
			throw new IllegalArgumentException();
		if(dao.findById(item.getFilmId()).isPresent())
			throw new DuplicateKeyException();
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}
	@Override
	public Film change(Film item) throws NotFoundException, InvalidDataException  {
		if(item == null)
			throw new IllegalArgumentException();
		if(dao.findById(item.getFilmId()).isEmpty())
			throw new NotFoundException();
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}
	@Override
	public void delete(Film item) {
		if(item == null)
			throw new IllegalArgumentException();
		deleteById(item.getFilmId());
		
	}
	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}
}
