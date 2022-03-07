package com.example.security.dtos;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AuthToken implements Serializable {
	private boolean success = true;
    private String token;
    private String name;
	public AuthToken(boolean success, String token, String name) {
		this.success = success;
		this.token = token;
		this.name = name;
	}
	public boolean isSuccess() {
		return success;
	}
	public String getToken() {
		return token;
	}
	public String getName() {
		return name;
	}
    
}
