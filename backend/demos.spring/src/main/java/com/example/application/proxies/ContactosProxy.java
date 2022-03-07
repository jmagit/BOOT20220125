package com.example.application.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.application.dtos.ContactoDTO;

@FeignClient(name = "contactos-service", url = "http://localhost:8008")
public interface ContactosProxy {
	@GetMapping(path = "/api/contactos")
	List<ContactoDTO> getAll();
	@GetMapping(path = "/api/contactos/{idContacto}")
	ContactoDTO getContacto(@PathVariable int idContacto);
	@PostMapping(path = "/api/contactos")
	ContactoDTO addContacto(@RequestBody ContactoDTO item);
}
