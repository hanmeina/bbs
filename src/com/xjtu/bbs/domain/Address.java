package com.xjtu.bbs.domain;

//IPπÈ Ùµÿ
public class Address { 
	 //±‡∫≈
	 private int id;
	 //IP
	 private String ip;
	 //πÈ Ùµÿ
	 private String location;
	 public Address(){}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
