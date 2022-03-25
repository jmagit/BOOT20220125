package com.example.application.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.multipart.MultipartFile;

import com.example.domains.contracts.repositories.StaffRepository;
import com.example.exceptions.NotFoundException;

@RestController
@RequestMapping(path = "/api/empleados")
public class EmpleadosResource {
	@Autowired
	private StaffRepository dao;
	
	@GetMapping("/{id}/foto")
	public ResponseEntity<byte[]> getFoto(@PathVariable int id) throws NotFoundException {
		var result = dao.findById(id);
		if(result.isEmpty() || result.get().getPicture() == null)
			throw new NotFoundException();
		return ResponseEntity.ok().header("content-type", "image/png").body(result.get().getPicture());
	}
	
	@GetMapping(path="/{id}/photo", produces = { "image/png" })
	public byte[] getPhoto(@PathVariable int id) throws NotFoundException {
		var result = dao.findById(id);
		if(result.isEmpty() || result.get().getPicture() == null)
			throw new NotFoundException();
		return result.get().getPicture();
	}
	
	@PutMapping(path="/{id}/photo", produces = { "image/png" })
	public byte[]  setPhoto(@PathVariable int id, @RequestBody byte[] file) throws NotFoundException {
		var item = dao.findById(id);
		if(item.isEmpty())
			throw new NotFoundException();
		item.get().setPicture(file);
		var result = dao.save(item.get());
		return result.getPicture();
	}
	
	@DeleteMapping("/{id}/foto")
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public void deleteFoto(@PathVariable int id) throws NotFoundException {
		var item = dao.findById(id);
		if(item.isEmpty())
			throw new NotFoundException();
		item.get().setPicture(null);
		dao.save(item.get());
	}
	
	
	@PostMapping("/upload")
    public void formUploadFile(@RequestParam("file") MultipartFile file) {
    }

}
