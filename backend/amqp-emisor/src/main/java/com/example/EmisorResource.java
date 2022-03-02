package com.example;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmisorResource {
	@Autowired
	private AmqpTemplate amqp;
	
	@GetMapping(path = "/saludo/{nom}")
	public String saluda(@PathVariable String nom) {
		String msg = "Hola " + nom;
		amqp.convertAndSend("saludos", new MessageDTO(msg));
		return "SEND: " + msg;
	}
}
