package com.xjtu.bbs.filter;

import java.io.IOException;

import java.util.Calendar;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xjtu.bbs.domain.Address;
import com.xjtu.bbs.domain.Flow;
import com.xjtu.bbs.service.BbsService;



//����welcome.jspҳ��
public class IpAddressFilter implements Filter {
	public void destroy() {
	}
	public void doFilter(
			ServletRequest req, 
			ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		try {
			//�ӿ�ǿת
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) res;
			// ȡ��HttpSession����
			HttpSession session = request.getSession();
			// �жε�ǰ�û�HttpSession���Ƿ����IP��������Ϣ
			Address address = (Address) session.getAttribute("address");
			// ���û��
			if(address == null){
				// ȡ��IP��ַ
				String ip = request.getRemoteAddr();
				BbsService bbsService = new BbsService();
				// ȡ�ö�Ӧ�Ĺ�����
				address = bbsService.findAddressByIP(ip);
				// �󶨵�HttpSession��
				session.setAttribute("address",address);
			}
			
			ServletContext context = session.getServletContext();
			Integer yesterdayFlow = (Integer) context.getAttribute("yesterdayFlow");
			//��һ��
			if(yesterdayFlow==null){
				BbsService bbsService = new BbsService();
				//ȡ����������
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DATE,-1);
				//ȡ����������
				Flow flow = bbsService.findFlowByDate(c.getTime());
				context.setAttribute("yesterdayFlow",flow.getNum());
			}
			//������Դ
			chain.doFilter(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public void init(FilterConfig filterConfig) throws ServletException {
	}
}
