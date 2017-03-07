package com.xjtu.bbs.util;

import java.security.MessageDigest;

//����MD5�������
public class Md5Util {
	
	private static String[] hex = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
	//�������ַ�����MD5��ʽ���ܺ󷵻ؿɶ���ʮ���������ַ���
	public static String encodeByMd5(String password) throws Exception{
		MessageDigest md5 = MessageDigest.getInstance("md5");
		byte[] results = md5.digest(password.getBytes());
		return byteArrayToString(results);
	}
	//��byte[]ת��String����
	private static String byteArrayToString(byte[] results){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<results.length;i++){
			//��ÿһλbyteת��String
			sb.append(byteToString(results[i]));
		}
		return sb.toString();
	}
	//��ÿһλbyteת��String(�����㷨)
	private static String byteToString(byte b){
		int n = b;
		if(n < 0 ){//���Ǹ������
			n = 256 + n ; 
		}
		//ʮ�����Ƶĵ�һλ
		int d1 = n / 16;
		
		//ʮ�����Ƶĵڶ�λ
		int d2 = n % 16;
		
		//(ʮ)  0-255 ��256��
		//(ʮ��)0-FF
		
		return hex[d1] + hex[d2];
	}
	
	
	
	
	
	
	
	
}
