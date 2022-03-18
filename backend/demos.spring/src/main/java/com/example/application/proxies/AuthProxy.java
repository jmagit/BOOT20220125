package com.example.application.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.application.dtos.ContactoDTO;

@FeignClient(name = "autentication-service", url = "http://localhost:8091")
public interface AuthProxy {
	@GetMapping(path = "/seguro")
	String getSeguro(@RequestHeader String authorization);
	@GetMapping(path = "/secreto")
	String getSecreto(@RequestHeader String authorization);
}
