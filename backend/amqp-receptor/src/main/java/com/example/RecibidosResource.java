package com.example;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecibidosResource {
	@GetMapping("/recibidos")
	public List<Message> get() {
		return Store.get();
	}

}
