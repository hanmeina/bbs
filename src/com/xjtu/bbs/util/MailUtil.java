package com.xjtu.bbs.util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {
	//�����ʼ�
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
	//�����ʼ�
	public static Message createMessage(Session session,String email,String username) throws Exception{
		MimeMessage message = new MimeMessage(session);
		//��վ����Ա
		message.setFrom(new InternetAddress("18291776860@163.com"));
		message.setRecipient(RecipientType.TO,new InternetAddress(email));
		message.setSubject("BBS��̳");
		message.setContent("<font color='red'>��ϲ"+username+",��¼�ɹ�</font>","text/html;charset=UTF-8");
		return message;
	}
}




