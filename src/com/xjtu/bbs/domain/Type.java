package com.xjtu.bbs.domain;

//版块
public class Type {
	 //编号
	 private int id;
	 //版块名
	 private String title;
	 //版块图片路径
	 private String image;
	 //关联版主
	 private Admin admin;
	 //版块点击数
	 private int click;
	 //主题数
	 private int topicNum;
	 //最新主题
	 private Topic topic;
	 public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
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
	public int getTopicNum() {
		return topicNum;
	}
	public void setTopicNum(int topicNum) {
		this.topicNum = topicNum;
	}
}
