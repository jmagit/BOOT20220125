package com.example.security.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BasicCredential {
	@JsonProperty("name")
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String user) {
		this.username = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
