package com.example.application.resources;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.application.dtos.PeliculaDetailsDTO;
import com.example.application.dtos.PeliculaEditDTO;
import com.example.application.dtos.PeliculaShortDTO;
import com.example.domains.contracts.services.PeliculasService;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculasResource {
	@Autowired
	private PeliculasService srv;


	@GetMapping
	public List<PeliculaShortDTO> getAll() {
		return srv.getByProjection(PeliculaShortDTO.class);
	}

	@GetMapping(params = "page")
	public Page<PeliculaShortDTO> getAll(Pageable page) {
		return srv.getByProjection(page, PeliculaShortDTO.class);
	}

	@GetMapping(path = "/{id}")
	public PeliculaDetailsDTO getOneDetails(@PathVariable int id, @RequestParam(required = false, defaultValue = "details") String mode)
			throws NotFoundException {
			return PeliculaDetailsDTO.from(srv.getOne(id));
	}
	@GetMapping(path = "/{id}", params = "mode=edit")
	public PeliculaEditDTO getOneEdit(@PathVariable int id, @RequestParam(required = true) String mode)
			throws NotFoundException {
			return PeliculaEditDTO.from(srv.getOne(id));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Object> create(@Valid @RequestBody PeliculaEditDTO item)
			throws InvalidDataException, DuplicateKeyException, NotFoundException {
		var entity = PeliculaEditDTO.from(item);
		if (entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		entity = srv.add(entity);
		item.update(entity);
		srv.change(entity);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(entity.getFilmId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@Transactional
	public void update(@PathVariable int id, @Valid @RequestBody PeliculaEditDTO item)
			throws InvalidDataException, NotFoundException {
		if (id != item.getFilmId())
			throw new InvalidDataException("No coinciden los identificadores");
		var entity = srv.getOne(id);
		item.update(entity);
		if (entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		srv.change(entity);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		srv.deleteById(id);
	}
}
