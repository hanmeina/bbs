package com.xjtu.bbs.util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {
	//发送邮件
	public static void sendMail(String email,String username) throws Exception {
		Properties props = new Properties();
		props.put("mail.transport.protocol","smtp");
		props.put("mail.host","smtp.163.com");
		Session session = Session.getDefaultInstance(props);
		Transport transport = session.getTransport();
		transport.connect("18896778529@163.com","han182");
		Message message = createMessage(session,email,username);
	//	Thread.sleep(10*1000);
		transport.send(message);
		transport.close();
	}
	//创建邮件
	public static Message createMessage(Session session,String email,String username) throws Exception{
		MimeMessage message = new MimeMessage(session);
		//网站管理员
		message.setFrom(new InternetAddress("18291776860@163.com"));
		message.setRecipient(RecipientType.TO,new InternetAddress(email));
		message.setSubject("BBS论坛");
		message.setContent("<font color='red'>恭喜"+username+",登录成功</font>","text/html;charset=UTF-8");
		return message;
	}
}




