package com.ems.model;


public class Admin {
	private String fullname;
	private String password;
	private String email;
	private String username;
	
	public Admin() {
		
	}
	
	public Admin(String fullname,String password,String email,String username) {
		this.email = email;
		this.fullname = fullname;
		this.password = password;
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}
