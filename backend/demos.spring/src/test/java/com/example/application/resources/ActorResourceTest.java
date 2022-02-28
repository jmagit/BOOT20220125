package com.example.application.resources;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;

import com.example.application.dtos.ActorDTO;
import com.example.domains.contracts.repositories.ActorRepositoy;
import com.example.domains.contracts.services.ActorService;
import com.example.domains.entities.Actor;
import com.example.domains.services.ActorServiceImpl;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

@SpringBootTest
//@AutoConfigureMockMvc
class ActorResourceTest {
	List<ActorDTO> listado;

	@BeforeEach
	void setUp() throws Exception {
		listado = new ArrayList<ActorDTO>();
		listado.add(new ActorDTO(1, "uno", "auno"));
		listado.add(new ActorDTO(2, "dos", "ados"));
	}

	public static class IoCTestConfig {
		@Bean
		ActorService getServicio() {
			return mock(ActorService.class);
		}
		@Bean
		ActorResource getRest() {
			return new ActorResource();
		}
	}

	@Nested
	//@ContextConfiguration(classes = IoCTestConfig.class)
	@MockBean(ActorService.class)
	class PruebasUnitarias {
		@Autowired
		ActorService srv;
		
		@Autowired
		ActorResource rest;
		
		@Test
		void testMock() {
			assertNotNull(srv);
			assertNotNull(rest);
		}
		@Test
		void testGetAll() {
			when(srv.getByProjection(ActorDTO.class)).thenReturn(listado);

			var rslt = rest.getAll();

			assertNotNull(rslt);
			assertEquals(2, rslt.size());
		}

		@Test
		void testGetOne() throws NotFoundException {
			when(srv.getOne(1)).thenReturn(ActorDTO.from(listado.get(0)));

			var rslt = rest.getOne(1);
			assertNotNull(rslt);
			assertEquals(1, rslt.getActorId());
		}

		@Test
		void testGetOneNotFound() throws NotFoundException {
			when(srv.getOne(1)).thenThrow(NotFoundException.class);
			
			assertThrows(NotFoundException.class, () -> rest.getOne(1));
		}

		@Test
		void testCreate() throws NotFoundException, DuplicateKeyException, InvalidDataException {
			when(srv.add(any())).thenReturn(ActorDTO.from(listado.get(0)));

			var rslt = rest.create(listado.get(0));
			assertNotNull(rslt);
			assertEquals(HttpStatus.CREATED, rslt.getStatusCode());
		}
		@Test
		void testCreateDuplicateKey() throws NotFoundException, DuplicateKeyException, InvalidDataException {
			when(srv.add(any())).thenThrow(DuplicateKeyException.class);
			
			assertThrows(DuplicateKeyException.class, () -> rest.create(listado.get(0)));
		}
		@Test
		void testCreateInvalidData() throws NotFoundException, DuplicateKeyException, InvalidDataException {
			when(srv.add(any())).thenThrow(InvalidDataException.class);
			
			assertThrows(InvalidDataException.class, () -> rest.create(listado.get(0)));
		}

		@Test
		void testUpdate() throws NotFoundException, InvalidDataException {
			when(srv.change(any())).thenReturn(ActorDTO.from(listado.get(0)));

			rest.update(1, listado.get(0));
			verify(srv).change(ActorDTO.from(listado.get(0)));
		}

		@Test
		void testUpdateInvalidId() throws NotFoundException, InvalidDataException {
			assertThrows(InvalidDataException.class, () -> rest.update(0, listado.get(0)));
		}
		@Test
		void testUpdateNotFound() throws NotFoundException, InvalidDataException {
			when(srv.change(any())).thenThrow(NotFoundException.class);

			assertThrows(NotFoundException.class, () -> rest.update(1, listado.get(0)));
		}
		@Test
		void testUpdateInvalidData() throws NotFoundException, InvalidDataException {
			assertThrows(InvalidDataException.class, () -> rest.update(1, new ActorDTO()));
		}

		@Test
		void testDelete() throws NotFoundException, InvalidDataException {
			doNothing().when(srv).deleteById(any());

			rest.delete(1);
			verify(srv).deleteById(1);
		}
	}
}
