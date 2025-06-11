package com.ecommerce.app.apiresponse;

import java.util.List;

public class LoginResponse {

	private String token;
	private List<String> roles;

	public LoginResponse() {
	}

	public LoginResponse(String token, List<String> roles) {
		this.token = token;
		this.roles = roles;
	}

	// Getters and Setters
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}
