package com.xjtu.bbs.domain;

//���
public class Type {
	 //���
	 private int id;
	 //�����
	 private String title;
	 //���ͼƬ·��
	 private String image;
	 //��������
	 private Admin admin;
	 //�������
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
