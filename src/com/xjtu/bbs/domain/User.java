package com.xjtu.bbs.domain;

//用户
public class User {
	 //编号
	 private int id;
	 //用户名
	 private String username;
	 //MD5密码
	 private String password;
	 //性别
	 private String gender;
	 //邮箱
	 private String email;
	 public User(){}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
