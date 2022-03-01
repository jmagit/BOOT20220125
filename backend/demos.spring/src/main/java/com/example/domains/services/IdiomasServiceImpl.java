package com.example.domains.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.domains.contracts.repositories.IdiomasRepository;
import com.example.domains.contracts.services.IdiomasService;
import com.example.domains.entities.Language;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

@Service
public class IdiomasServiceImpl implements IdiomasService {
	private IdiomasRepository dao;
	
	public IdiomasServiceImpl(IdiomasRepository dao) {
		this.dao = dao;
	}
	
	@Override
	public List<Language> getAll() {
		return dao.findAll();
	}
	
	@Override
	public Iterable<Language> getAll(Sort sort) {
		return dao.findAll(sort);
	}
	@Override
	public Page<Language> getAll(Pageable pageable) {
		return dao.findAll(pageable);
	}
	
	@Override
	public <T> List<T> getByProjection(Class<T> type) {
		return dao.findByLanguageIdIsNotNull(type);
	}

	@Override
	public <T> Iterable<T> getByProjection(Sort sort, Class<T> type) {
		return dao.findByLanguageIdIsNotNull(sort, type);
	}

	@Override
	public <T> Page<T> getByProjection(Pageable pageable, Class<T> type) {
		return dao.findByLanguageIdIsNotNull(pageable, type);
	}

	@Override
	public Language getOne(Integer id) throws NotFoundException {
		var item = dao.findById(id);
		if(item.isEmpty())
			throw new NotFoundException();
		return item.get();
	}
	
	@Override
	public Language add(Language item) throws DuplicateKeyException, InvalidDataException {
		if(item == null)
			throw new IllegalArgumentException();
		if(dao.findById(item.getLanguageId()).isPresent())
			throw new DuplicateKeyException();
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}
	@Override
	public Language change(Language item) throws NotFoundException, InvalidDataException  {
		if(item == null)
			throw new IllegalArgumentException();
		if(dao.findById(item.getLanguageId()).isEmpty())
			throw new NotFoundException();
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}
	@Override
	public void delete(Language item) {
		if(item == null)
			throw new IllegalArgumentException();
		deleteById(item.getLanguageId());
		
	}
	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}
}
