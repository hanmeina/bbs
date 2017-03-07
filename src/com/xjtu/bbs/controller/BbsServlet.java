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
     * 安全退出
     * @param request
     * @param response
     * @throws IOException 
     */
	private void exit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		        //取得在线用户列表
				List<String> usernameList = (List<String>) this.getServletContext().getAttribute("usernameList");
				//取得该用户对象
				User user = (User) request.getSession().getAttribute("user");
				//取得用户名
				String username = user.getUsername();
				//从列表中删除该用户名
				usernameList.remove(username);
				//销毁当前用户的HttpSession
				request.getSession().invalidate();
				//重定向到welcome.jsp页面
				response.sendRedirect(request.getContextPath()+"/welcome.jsp");
	}



	/**
	 * 查询所有版块
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
			request.setAttribute("message", "查询失败");
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
     * 用户登录方法
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
		// 通过BeanUtils框架收集表单的所有参数到User对象中
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
	   //进入数据格式校验		
		LoginForm loginForm = new LoginForm();
		boolean  flag = loginForm.validate(user);
		if(flag){
			//如果数据格式正确，再判段当前用户是否在线
			List<String> usernameList = (List<String>) this.getServletContext().getAttribute("usernameList");
			if(usernameList==null){
				usernameList = new ArrayList<String>();
				this.getServletContext().setAttribute("usernameList",usernameList);
			}
			BbsService bbsService = new BbsService();
			flag = bbsService.checkOnline(user,usernameList);
			if(flag){
				//在线
				request.setAttribute("message","<font color='blue' size='44'>当前用户已在线</font>");
				request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(request,response);
				
			}else{
				  try {
					User u = bbsService.findUserByUsernameAndPassword(user);
				    if(u!=null){
				    	//登录成功，再将用户信息绑定到HttpSession中
						request.getSession().setAttribute("user",user);
						request.setAttribute("message","登录成功");
						request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(request,response);
				    }else{  
				    	//登录失败
						request.setAttribute("message","登录失败");
						request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(request,response);
				    }
				  
				  } catch (Exception e) {
					//登录失败
					request.setAttribute("message","登录失败");
					request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(request,response);
				}		
			}		
		}else{
			//错误
			request.setAttribute("loginForm",loginForm);
			request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
		}
				
				
		
	}



	/**
	 * 用户注册
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 通过BeanUtils框架收集表单的所有参数到User对象中
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
		//进入数据格式校验
		RegisterForm registerForm = new RegisterForm();
		boolean flag = registerForm.validate(user);
		//根据校验结果转到不同页面显示
		if(flag){
			  try {
				bbsService.regist(user);
				request.setAttribute("message","注册成功");
				request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(request,response);
				
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("message","注册失败");
				request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(request,response);
			}
		}else{
		 
			//错误
			request.setAttribute("registerForm",registerForm);
			request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request,response);
		}
		
		
		
		
		
		

	}

}
