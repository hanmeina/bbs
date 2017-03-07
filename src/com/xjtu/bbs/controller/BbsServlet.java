package com.xjtu.bbs.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.sun.crypto.provider.RSACipher;
import com.xjtu.bbs.domain.Address;
import com.xjtu.bbs.domain.Type;
import com.xjtu.bbs.domain.User;
import com.xjtu.bbs.form.LoginForm;
import com.xjtu.bbs.form.RegisterForm;
import com.xjtu.bbs.service.BbsService;

/**
 * 
 */
public class BbsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BbsService bbsService = new BbsService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if ("findAllType".equals(method)) {
			this.findAllType(request, response);

		} else if ("toRegisterJsp".equals(method)) {
			request.getRequestDispatcher("WEB-INF/jsp/register.jsp").forward(request, response);
		}else if("toLoginJsp".equals(method)){
			request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
			
		}else if("exit".equals(method)){
			this.exit(request,response);
			
		}
	}

	
    /**
     * ��ȫ�˳�
     * @param request
     * @param response
     * @throws IOException 
     */
	private void exit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		        //ȡ�������û��б�
				List<String> usernameList = (List<String>) this.getServletContext().getAttribute("usernameList");
				//ȡ�ø��û�����
				User user = (User) request.getSession().getAttribute("user");
				//ȡ���û���
				String username = user.getUsername();
				//���б���ɾ�����û���
				usernameList.remove(username);
				//���ٵ�ǰ�û���HttpSession
				request.getSession().invalidate();
				//�ض���welcome.jspҳ��
				response.sendRedirect(request.getContextPath()+"/welcome.jsp");
	}



	/**
	 * ��ѯ���а��
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void findAllType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Type> typeList = bbsService.findAllType();
			request.setAttribute("typeList", typeList);
			request.getRequestDispatcher("WEB-INF/jsp/listAllType.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("message", "��ѯʧ��");
			request.getRequestDispatcher("WEB-INF/jsp/message.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if ("register".equals(method)) {
			this.register(request, response);
		}else if("login".equals(method)){
			this.login(request,response);
			
		}
	}
    /**
     * �û���¼����
     * @param request
     * @param response
     * @throws IOException 
     * @throws ServletException 
     */
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  String ip = request.getRemoteAddr(); 
		  Address address=null;
		try {
			address = bbsService.findAddressByIP(ip);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  request.getSession().setAttribute("address", address);
		 // System.out.println(ip);
		// ͨ��BeanUtils����ռ��������в�����User������
				Enumeration<String> params = request.getParameterNames();
				User user = new User();
				while (params.hasMoreElements()) {
					String name = params.nextElement();
					String[] value = request.getParameterValues(name);
					try {
						BeanUtils.setProperty(user, name, value);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
	   //�������ݸ�ʽУ��		
		LoginForm loginForm = new LoginForm();
		boolean  flag = loginForm.validate(user);
		if(flag){
			//������ݸ�ʽ��ȷ�����жε�ǰ�û��Ƿ�����
			List<String> usernameList = (List<String>) this.getServletContext().getAttribute("usernameList");
			if(usernameList==null){
				usernameList = new ArrayList<String>();
				this.getServletContext().setAttribute("usernameList",usernameList);
			}
			BbsService bbsService = new BbsService();
			flag = bbsService.checkOnline(user,usernameList);
			if(flag){
				//����
				request.setAttribute("message","<font color='blue' size='44'>��ǰ�û�������</font>");
				request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(request,response);
				
			}else{
				  try {
					User u = bbsService.findUserByUsernameAndPassword(user);
				    if(u!=null){
				    	//��¼�ɹ����ٽ��û���Ϣ�󶨵�HttpSession��
						request.getSession().setAttribute("user",user);
						request.setAttribute("message","��¼�ɹ�");
						request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(request,response);
				    }else{  
				    	//��¼ʧ��
						request.setAttribute("message","��¼ʧ��");
						request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(request,response);
				    }
				  
				  } catch (Exception e) {
					//��¼ʧ��
					request.setAttribute("message","��¼ʧ��");
					request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(request,response);
				}		
			}		
		}else{
			//����
			request.setAttribute("loginForm",loginForm);
			request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
		}
				
				
		
	}



	/**
	 * �û�ע��
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ͨ��BeanUtils����ռ��������в�����User������
		Enumeration<String> params = request.getParameterNames();
		User user = new User();
		while (params.hasMoreElements()) {
			String name = params.nextElement();
			String[] value = request.getParameterValues(name);
			try {
				BeanUtils.setProperty(user, name, value);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		//�������ݸ�ʽУ��
		RegisterForm registerForm = new RegisterForm();
		boolean flag = registerForm.validate(user);
		//����У����ת����ͬҳ����ʾ
		if(flag){
			  try {
				bbsService.regist(user);
				request.setAttribute("message","ע��ɹ�");
				request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(request,response);
				
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("message","ע��ʧ��");
				request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(request,response);
			}
		}else{
		 
			//����
			request.setAttribute("registerForm",registerForm);
			request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request,response);
		}
		
		
		
		
		
		

	}

}
