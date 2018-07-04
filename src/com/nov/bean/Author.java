package com.nov.bean;

import java.util.Date;

public class Author {
	private Integer id;
	private String name;
	private Integer gender; // 1为男性，0为女性
	private Date birthday;
	// 账号密码
	private String username;
	private String password;
	// 作者简介
	private String introduction;
	public Author() {
	}
	public Author(Integer id, String name, Integer gender, Date birthday, String username, String password,
			String introduction) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.username = username;
		this.password = password;
		this.introduction = introduction;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
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
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", gender=" + gender + ", birthday=" + birthday + ", username="
				+ username + ", password=" + password + ", introduction=" + introduction + "]";
	}
	
	
}
