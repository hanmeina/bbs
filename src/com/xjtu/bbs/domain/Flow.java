package com.xjtu.bbs.domain;

//流量
public class Flow {
	//编号
	private int id;
	//日期
	private java.util.Date historydate;
	//流量数
	private int num;
	public Flow(){
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public java.util.Date getHistorydate() {
		return historydate;
	}
	public void setHistorydate(java.util.Date historydate) {
		this.historydate = historydate;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
}
