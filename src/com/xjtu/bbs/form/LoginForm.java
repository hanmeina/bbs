package com.xjtu.bbs.form;

import java.util.HashMap;

import java.util.Map;

import com.xjtu.bbs.domain.User;


public class LoginForm {
	private String username;
	private String password;
	private Map<String,String> errors = new HashMap<String,String>();
	public boolean validate(User user){
		boolean flag = false;
		this.username = user.getUsername();
		this.password = user.getPassword();
		if(
			this.validateUsername(username) &
			this.validatePassword(password)
		){
			flag = true;
		}
		return flag;
	}
	//��֤�û���[����+����]
	private boolean validateUsername(String username){
		boolean flag = false;
		if(username!=null && username.trim().length()>0){
			if(username.matches("[\u4E00-\uFA29]+")){
				flag = true;
			}else{
				this.errors.put("username","<font color='red'>�û�������������</font>");
			}
		}else{
			this.errors.put("username","<font color='red'>�û�������</font>");
		}
		return flag;
	}
	//��֤����[����+����6λ]
	private boolean validatePassword(String password){
		boolean flag = false;
		if(password!=null && password.trim().length()>0){
			if(password.matches("[0-9]{6}")){
				flag = true;
			}else{
				this.errors.put("password","<font color='red'>���������6λ����</font>");
			}
		}else{
			this.errors.put("password","<font color='red'>�������</font>");
		}
		return flag;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public Map<String, String> getErrors() {
		return errors;
	}
}
