package com.example.application.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.example.domains.entities.Contacto;
import com.example.exceptions.BadRequestException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;
import com.example.infraestructure.repositories.ContactoRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Value;

@RestController
@RequestMapping(path = "/api/contactos")
public class ContactoResource {
	@Value
	public static class PageCount {
		private int pages;
		private int rows;
	}

	@Autowired
	private ContactoRepository dao;

	@GetMapping
	@ApiOperation(value = "Listar todos")
	public List<Contacto> getAll(@RequestParam(required = false) Integer _page,
			@RequestParam(required = false, defaultValue = "20") int _rows, @RequestParam(required = false) String _sort) {
		if (_sort != null)
			return dao.findAll(Sort.by(Direction.ASC, _sort));
		return dao.findAll();
	}

	@GetMapping(params = "_page")
	@ApiOperation(value = "Listar paginados", hidden = true )
	public List<Contacto> getPaged(@ApiParam(value = "Empieza en la pagina 0", required = false) @RequestParam int _page,
			@RequestParam(required = false, defaultValue = "20") int _rows, @RequestParam(required = false) String _sort) {
		if (_sort != null)
			return dao.findAll(PageRequest.of(_page, _rows, Sort.by(Direction.ASC, _sort))).getContent();
		return dao.findAll(PageRequest.of(_page, _rows)).getContent();
	}

	@GetMapping(params = "_page=count")
	@ApiOperation(value = "Obtener valores de paginación", hidden = true)
	public PageCount getPageCount(@ApiParam(required = false) @RequestParam(required = false, defaultValue = "20") int _rows) {
		var rows = (int) dao.count();
		return new PageCount((int) Math.ceil((double) rows / _rows), rows);
	}

	@GetMapping(path = "/{id}")
	public Contacto getOne(@PathVariable int id) throws Exception {
		Optional<Contacto> rslt = dao.findById(id);
		if (!rslt.isPresent())
			throw new NotFoundException();
		return rslt.get();
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Object> add(@Valid @RequestBody Contacto item) throws Exception {
		if (dao.findById(item.getId()).isPresent())
			throw new InvalidDataException("Duplicate key");
		int id = 1;
		Optional<Contacto> encontrado = dao.findAll(PageRequest.of(0, 1, Sort.by(Direction.DESC, "id"))).stream()
				.findFirst();
		if (encontrado.isPresent())
			id = encontrado.get().getId() + 1;
		item.setId(id);
		dao.save(item); // ConstraintViolationException
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(location).build();
	}

	@Secured({ "ROLE_USER" })
	@PutMapping(path = "/{id}")
	public Contacto modify(@PathVariable int id, @Valid @RequestBody Contacto item) throws Exception {
		if (item.getId() != id)
			throw new BadRequestException("No coinciden los ID");
		if (!dao.findById(item.getId()).isPresent())
			throw new NotFoundException("Missing item");
		return dao.save(item); // ConstraintViolationException
	}

	@PreAuthorize("authenticated")
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) throws Exception {
		try {
			dao.deleteById(id);
		} catch (Exception e) {
			throw new NotFoundException("Missing item", e);
		}
	}

}
