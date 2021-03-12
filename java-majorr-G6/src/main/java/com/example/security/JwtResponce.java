package com.example.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

public class JwtResponce {
    private String username;
	private List<String> roles = new ArrayList<>();
	private String token;
	private int userId;
	public JwtResponce() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JwtResponce(String username, List<String> roles, String token) {
		super();
		this.username = username;
		this.roles = roles;
		this.token = token;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "JwtResponce [username=" + username + ", roles=" + roles + ", token=" + token + "]";
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	


}
