package com.xjtu.bbs.listener;

import javax.servlet.ServletContext;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.xjtu.bbs.domain.User;


public class OnLineListener implements HttpSessionAttributeListener,ServletRequestListener {
	public void attributeAdded(HttpSessionBindingEvent se) {
		String name = se.getName();//name="user"�ַ���
		Object obj =  se.getValue();//value=user����
		if(obj instanceof User){
			//ȷʵ����HttpSession�а��ǵ�user����
			if("user".equals(name)){
				HttpSession session = se.getSession();
				ServletContext context = session.getServletContext();
				Integer online = (Integer) context.getAttribute("online");
				if(online==null){
					online = 1;
				}else{
					online ++;
				}
				//��������
				context.setAttribute("online",online);
				Integer caller = (Integer) context.getAttribute("caller");
				if(caller==null){
					caller = 1;
				}else{
					caller ++;
				}
				//���ʴ���
				context.setAttribute("caller",caller);//�ɱ�
				session.setAttribute("caller",caller);//����
			}
		}
	}
	public void attributeRemoved(HttpSessionBindingEvent se) {
		String name = se.getName();//name="user"�ַ���
		//ȷʵ����HttpSession�а��ǵ�user����
		if("user".equals(name)){
			HttpSession session = se.getSession();
			ServletContext context = session.getServletContext();
			Integer online = (Integer) context.getAttribute("online");
			online --;
			context.setAttribute("online",online);
		}
	}
	public void attributeReplaced(HttpSessionBindingEvent se) {
	}
	public void requestDestroyed(ServletRequestEvent sre) {
	}
	public void requestInitialized(ServletRequestEvent sre) {
		HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
		HttpSession session = request.getSession();
		if(session.isNew()){
			ServletContext context = sre.getServletContext();
			Integer todayFlow = (Integer) context.getAttribute("todayFlow");
			if(todayFlow==null){
				todayFlow = 1;
			}else{
				todayFlow ++;
			}
			//�����������󶨵�ServletContext������
			context.setAttribute("todayFlow",todayFlow);
		}else{
			;
		}
	}
}








