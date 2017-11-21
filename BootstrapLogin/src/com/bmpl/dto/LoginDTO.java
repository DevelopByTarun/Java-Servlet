package com.bmpl.dto;

public class LoginDTO {
	
	private String username;
	private String password;
	
	/*
	 * create constructor
	 */
	public LoginDTO(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	/*
	 * create getter and setters
	 */
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
