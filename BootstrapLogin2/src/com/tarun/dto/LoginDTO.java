package com.tarun.dto;

public class LoginDTO {
	/*
	 * declare variables
	 */
	private String userid;
	private String password;
	
	/*
	 * create constructor
	 */
	public LoginDTO(String userid, String password) {
		this.userid = userid;
		this.password = password;
	}
	
	/*
	 * create getter and setter
	 */
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
