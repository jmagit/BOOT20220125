package com.example.security.resources;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.dtos.AuthToken;
import com.example.security.dtos.BasicCredential;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
//	@CrossOrigin(origins = "http://localhost:4200", allowCredentials="true", methods={RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS })
//	@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials="false")
public class UserResource {
	@Value("${jwt.secret}")
	private String SECRET;

	@RequestMapping(path = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<AuthToken> login(@RequestParam("name") String username, @RequestParam("password") String pwd) {		
		//Realizar proceso de autenticación	
			if("falla".compareToIgnoreCase(username) == 0)
				return ResponseEntity.notFound().build();				
		// ---------------------------
		return ResponseEntity.ok(new AuthToken(true, getJWTToken(username), username));		
	}
	
	@PostMapping(path = "/login", consumes = "application/json")
	public ResponseEntity<AuthToken> loginPostJSON(@RequestBody BasicCredential usr) {		
		return login(usr.getUsername(), usr.getPassword());		
	}

	private String getJWTToken(String username) {
		List<GrantedAuthority> grantedAuthorities = "admin".compareToIgnoreCase(username) == 0 ?
				AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER, ROLE_ADMIN") :
				AuthorityUtils.createAuthorityList("ROLE_USER")	;	
		String token = Jwts.builder()
				.setId("MicroserviciosJWT")
				.setSubject(username)
				.claim("authorities",
					grantedAuthorities.stream()
						.map(GrantedAuthority::getAuthority)
						.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						SECRET.getBytes()).compact();
		return "Bearer " + token;
	}
}
