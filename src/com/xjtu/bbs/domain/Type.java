package com.xjtu.bbs.domain;

//°æ¿é
public class Type {
	 //±àºÅ
	 private int id;
	 //°æ¿éÃû
	 private String title;
	 //°æ¿éÍ¼Æ¬Â·¾¶
	 private String image;
	 //¹ØÁª°æÖ÷
	 private Admin admin;
	 //°æ¿éµã»÷Êı
	 private int click;
	 public Type(){}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getClick() {
		return click;
	}
	public void setClick(int click) {
		this.click = click;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
}
