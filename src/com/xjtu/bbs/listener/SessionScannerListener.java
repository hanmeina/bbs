package com.xjtu.bbs.listener;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.xjtu.bbs.domain.User;


public class SessionScannerListener implements HttpSessionListener,ServletContextListener {
	private Timer timer = new Timer();
	private List<HttpSession> sessionList = new ArrayList<HttpSession>();
	public SessionScannerListener(){
		//����سǰ�ȫ�ļ���
		sessionList = Collections.synchronizedList(sessionList);
	}
	public void sessionCreated(HttpSessionEvent se) {
		//ȡ�ø��û���HttpSession
		HttpSession session = se.getSession();
		synchronized (sessionList) {
			sessionList.add(session);
		}
	}
	public void sessionDestroyed(HttpSessionEvent se) {
	}
	public void contextDestroyed(ServletContextEvent sce) {
	}
	public void contextInitialized(ServletContextEvent sce) {
		timer.schedule(new MyTimerTask(sessionList),0,1*1000);
	}
}
class MyTimerTask extends TimerTask{
	private List<HttpSession> sessionList;
	public MyTimerTask(List<HttpSession> sessionList) {
		this.sessionList = sessionList;
	}
	public void run() {
		//����HttpSession����ʱ���ٵ���
		if(sessionList.size()>0){
			ListIterator<HttpSession> it = sessionList.listIterator();
			synchronized (sessionList) {
				while (it.hasNext()) {
					HttpSession session = it.next();
					User user = (User) session.getAttribute("user");
					//�û�δ��"��ȫ�˳�"
					if(user!=null){
						int mid = (int) ((System.currentTimeMillis()-session.getLastAccessedTime())/1000);
						if(mid > 10){
							//����HttpSession�Ӽ�����ɾ��
							it.remove();
							
							//ȡ�������û��б�
							List<String> usernameList = (List<String>) session.getServletContext().getAttribute("usernameList");
							//ȡ���û���
							String username = user.getUsername();
							//���б���ɾ�����û���
							usernameList.remove(username);
							//���ٵ�ǰ�û���HttpSession
							session.removeAttribute("user");
						}
					}else{
						//�û��Ѱ�"��ȫ�˳�"
						//System.out.println("�û��Ѱ���ȫ�˳�");
						break;
					}
				}
			}
		}
	}
}




