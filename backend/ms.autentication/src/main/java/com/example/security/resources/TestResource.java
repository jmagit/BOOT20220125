package com.example.security.resources;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestResource {

	@GetMapping("/seguro")
	public String get(@RequestHeader String authorization, Principal principal) {
		return "El usuario está autenticado: " + principal.getName() + "\nAuthorization: " + authorization;
	}
	
	@GetMapping("/admin")
	public String getAdmin() {
		return "El usuario es administrador";
	}
	
	@Value("${jwt.secret}")
	private String SECRET;
	
	@GetMapping("/secreto")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String getSecreto() {
		return SECRET;
	}
}
