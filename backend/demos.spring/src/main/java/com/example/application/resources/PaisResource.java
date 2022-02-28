package com.example.application.resources;

import java.net.URI;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.application.dtos.ActorDTO;
import com.example.application.dtos.PaisDTO;
import com.example.domains.contracts.services.ActorService;
import com.example.domains.contracts.services.PaisesService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.Country;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/paises")
public class PaisResource {
	@Autowired
	private PaisesService srv;

	@GetMapping
	public List<PaisDTO> getAll() {
		return srv.getByProjection(PaisDTO.class);
	}

	@GetMapping(path = "/{id}")
	public PaisDTO getOne(@PathVariable int id) throws NotFoundException {
		return PaisDTO.from(srv.getOne(id));
	}
	
	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody PaisDTO item) throws InvalidDataException, DuplicateKeyException {
		var entity = PaisDTO.from(item);
		if(entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		entity = srv.add(entity);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(entity.getCountryId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void update(@PathVariable int id, @Valid @RequestBody PaisDTO item) throws InvalidDataException, NotFoundException {
		if(id != item.getCountryId())
			throw new InvalidDataException("No coinciden los identificadores");
		var entity = PaisDTO.from(item);
		if(entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		srv.change(entity);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		srv.deleteById(id);
	}
}
