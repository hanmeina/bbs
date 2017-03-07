package com.xjtu.bbs.domain;

//°æÖ÷
public class Admin {
	 //±àºÅ
	 private int id;
	 //ĞÕÃû
	 private String name;
	 //°æ¿éÃû
	 private String title;
	 public Admin(){}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
