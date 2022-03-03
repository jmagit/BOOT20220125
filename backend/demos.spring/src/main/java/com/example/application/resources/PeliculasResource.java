package com.example.application.resources;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/peliculas")
@Api(value = "/peliculas", description = "Mantenimiento de películas", produces = "application/json, application/xml", consumes="application/json, application/xml")
public class PeliculasResource {
	@Autowired
	private PeliculasService srv;


	@GetMapping
	@ApiOperation(value = "Listado de las películas")
	public List<PeliculaShortDTO> getAll() {
		return srv.getByProjection(PeliculaShortDTO.class);
	}

	@GetMapping(params = "page")
	@ApiOperation(value = "Listado paginable de las películas")
	public Page<PeliculaShortDTO> getAll(@ApiParam(required = false) Pageable page) {
		var content = srv.getAll(page);
		return new PageImpl(content.getContent().stream().map(item -> PeliculaShortDTO.from(item)).toList(), 
				page, content.getTotalElements());
//		return srv.getByProjection(page, PeliculaShortDTO.class);
	}

	@GetMapping(path = "/{id}")
	public PeliculaDetailsDTO getOneDetails(@PathVariable int id, @RequestParam(required = false, defaultValue = "details") String mode)
			throws NotFoundException {
			return PeliculaDetailsDTO.from(srv.getOne(id));
	}
	@GetMapping(path = "/{id}", params = "mode=edit")
	@ApiOperation(value = "Recupera una película")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Película encontrada"),
		@ApiResponse(code = 404, message = "Película no encontrada")
	})
	public PeliculaEditDTO getOneEdit(@ApiParam(value = "Identificador de la película") @PathVariable int id, 
			@ApiParam(value = "Versión completa o editable", required = false, allowableValues = "details,edit", defaultValue = "edit") @RequestParam() String mode)
			throws NotFoundException {
			return PeliculaEditDTO.from(srv.getOne(id));
	}

	@PostMapping
	@Transactional
	@ApiOperation(value = "Añadir una nueva película")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Película añadida"),
		@ApiResponse(code = 400, message = "Error al validar los datos o clave duplicada"),
		@ApiResponse(code = 404, message = "Película no encontrada")
	})
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
	@ApiOperation(value = "Modificar una película existente", notes = "Los identificadores deben coincidir")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Película añadida"),
		@ApiResponse(code = 400, message = "Error al validar los datos o discrepancias en los identificadores"),
		@ApiResponse(code = 404, message = "Película no encontrada")
	})
	public void update(@ApiParam(value = "Identificador de la película") @PathVariable int id, @Valid @RequestBody PeliculaEditDTO item)
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
	@ApiOperation(value = "Borrar una película existente")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Película borrada"),
		@ApiResponse(code = 404, message = "Película no encontrada")
	})
	public void delete(@ApiParam(value = "Identificador de la película") @PathVariable int id) {
		srv.deleteById(id);
	}
}
