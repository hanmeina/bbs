package com.xjtu.bbs.domain;

//�û�
public class User {
	 //���
	 private int id;
	 //�û���
	 private String username;
	 //MD5����
	 private String password;
	 //�Ա�
	 private String gender;
	 //����
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
