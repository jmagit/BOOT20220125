package com.example.domains.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.domains.contracts.repositories.ActorRepositoy;
import com.example.domains.entities.Actor;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

class ActorServiceImplTest {
	List<Actor> listado;
	ActorRepositoy dao;
	
	@BeforeEach
	void setUp() throws Exception {
		listado = new ArrayList<Actor>();
		listado.add(new Actor(1, "uno", "auno"));
		listado.add(new Actor(2, "dos", "ados"));
		dao = mock(ActorRepositoy.class);
	}

	@Test
	void testGetAll() {
		when(dao.findAll()).thenReturn(listado);
		var srv = new ActorServiceImpl(dao);
		
		var rslt = srv.getAll();
		
		assertNotNull(rslt);
		assertEquals(2, rslt.size());
	}

	@Test
	void testGetOne() throws NotFoundException {
		when(dao.findById(1)).thenReturn(Optional.of(listado.get(0)));
		var srv = new ActorServiceImpl(dao);
		
		var rslt = srv.getOne(1);
		assertNotNull(rslt);
		assertEquals(1, rslt.getActorId());
	}
	@Test
	void testGetOneNotFound() throws NotFoundException {
		when(dao.findById(1)).thenReturn(Optional.empty());
		var srv = new ActorServiceImpl(dao);
		
		assertThrows(NotFoundException.class, () -> srv.getOne(1));
	}

	@Test
	void testAdd() throws NotFoundException, DuplicateKeyException, InvalidDataException {
		when(dao.findById(1)).thenReturn(Optional.empty());
		when(dao.save(any())).thenReturn(listado.get(0));
		var srv = new ActorServiceImpl(dao);
		
		var rslt = srv.add(listado.get(0));
		assertNotNull(rslt);
		assertEquals(1, rslt.getActorId());
	}
	@Test
	void testAddNull() {
		var srv = new ActorServiceImpl(dao);
		
		assertThrows(IllegalArgumentException.class, () -> srv.add(null));
	}
	@Test
	void testAddDuplicateKey() throws NotFoundException, DuplicateKeyException, InvalidDataException {
		when(dao.findById(1)).thenReturn(Optional.of(listado.get(0)));
		var srv = new ActorServiceImpl(dao);
		
		assertThrows(DuplicateKeyException.class, () -> srv.add(listado.get(0)));
	}
	@Test
	void testAddInvalidData() throws NotFoundException, DuplicateKeyException, InvalidDataException {
		when(dao.findById(1)).thenReturn(Optional.empty());
		var srv = new ActorServiceImpl(dao);
		
		assertThrows(InvalidDataException.class, () -> srv.add(new Actor(1)));
	}

	@Test
	void testChange() throws NotFoundException, DuplicateKeyException, InvalidDataException {
		when(dao.findById(1)).thenReturn(Optional.of(listado.get(0)));
		when(dao.save(any())).thenReturn(listado.get(0));
		var srv = new ActorServiceImpl(dao);
		
		var rslt = srv.change(listado.get(0));
		assertNotNull(rslt);
		assertEquals(1, rslt.getActorId());
	}

	@Test
	void testChangeNull() {
		var srv = new ActorServiceImpl(dao);
		
		assertThrows(IllegalArgumentException.class, () -> srv.change(null));
	}
	@Test
	void testChangeNotFound() throws NotFoundException, DuplicateKeyException, InvalidDataException {
		when(dao.findById(1)).thenReturn(Optional.empty());
		var srv = new ActorServiceImpl(dao);
		
		assertThrows(NotFoundException.class, () -> srv.change(listado.get(0)));
	}
	@Test
	void testChangeInvalidData() throws NotFoundException, DuplicateKeyException, InvalidDataException {
		when(dao.findById(1)).thenReturn(Optional.of(listado.get(0)));
		var srv = new ActorServiceImpl(dao);
		
		assertThrows(InvalidDataException.class, () -> srv.change(new Actor(1)));
	}

	@Test
	void testDelete() {
		doNothing().when(dao).deleteById(1);
		var srv = new ActorServiceImpl(dao);
		srv.delete(new Actor(1));
		verify(dao).deleteById(1);
	}

	@Test
	void testDeleteById() {
		doNothing().when(dao).deleteById(1);
		var srv = new ActorServiceImpl(dao);
		srv.deleteById(1);
		verify(dao).deleteById(1);
	}

}
