package com.example.application.resources;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.application.dtos.ActorDTO;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

@RestController
@RequestMapping(path = "/demos")
public class DemosResource {
	// http://localhost:8001/demos/params/1111?nom=mundo&page=5
	@GetMapping(path = "/params/{id}", params = "nom")
	public String cotilla(
	        @PathVariable String id,
	        @RequestParam String nom,
	        @RequestHeader("Accept-Language") String language, 
	        @CookieValue(name = "JSESSIONID", required = false, defaultValue = "(Sin valor)" ) String cookie) { 
	    StringBuilder sb = new StringBuilder();
	    sb.append("id: " + id + "\n");
	    sb.append("nom: " + nom + "\n");
	    sb.append("language: " + language + "\n");
	    sb.append("cookie: " + cookie + "\n");
	    return sb.toString();
	}
	@GetMapping(path = "/params/{id}", produces = {"application/xml"})
	public String cotillaEnXML(
	        @PathVariable String id,
	        @RequestParam String nom,
	        @RequestHeader("Accept-Language") String language, 
	        @CookieValue(name = "JSESSIONID", required = false, defaultValue = "(Sin valor)" ) String cookie) { 
	    StringBuilder sb = new StringBuilder();
	    sb.append("<raiz>\n<id>" + id + "</id>\n");
	    sb.append("<nom>" + nom + "</nom>\n");
	    sb.append("<language>" + language + "</language>\n");
	    sb.append("<cookie>" + cookie + "</cookie>\n</raiz>");
	    return sb.toString();
	}
	@GetMapping("/params/{id}")
	public String cotilla(
	        @PathVariable String id) {

	    return "Peticion " + id ;
	}

	@GetMapping("/datos/{id}")
	public ActorDTO datos(
	        @PathVariable int id) throws Exception {

		throw new InvalidDataException("Forzado");
//	    return new ActorDTO(id, "Pepito", "Grillo") ;
	}

	@PostMapping("/datos")
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public String datos(
	        @RequestBody ActorDTO item) throws Exception {
		throw new NotFoundException("Forzado");
	    //return item.toString() ;
	}

}
