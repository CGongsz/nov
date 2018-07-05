package com.nov.bean;

public class Visitor {
	// 以邮箱来进行身份唯一性验证
	private String email;
	// 昵称
	private String username;
	public Visitor() {
	}
	public Visitor(String email, String username) {
		super();
		this.email = email;
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
