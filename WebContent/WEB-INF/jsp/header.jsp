<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
  	<style type="text/css">
  		a{
  			text-decoration:none
  		}
  	</style>
  </head>
  <body> 
	欢迎
	<font color="red">
		${!empty sessionScope.user?user.username:'游客'}
	</font>光临<br/>
	<c:if test="${empty sessionScope.user}">
	<a href="${pageContext.request.contextPath}/BbsServlet?method=toRegisterJsp">注册</a>|
	<a href="${pageContext.request.contextPath}/BbsServlet?method=toLoginJsp">登录</a>|
	</c:if>
	 <a href="${pageContext.request.contextPath}/welcome.jsp">首页</a>
	<c:if test="${!empty sessionScope.user}">
	  
	   <a href="${pageContext.request.contextPath}/BbsServlet?method=exit">|安全退出</a>
	</c:if>
	
	
	
	
	<div style="position:absolute;left:720;top:15">
		你的IP：<font color="red">${sessionScope.address.ip}</font>
		归属地：<font color="red">${sessionScope.address.location}</font><br/>
	          在线人数：<font color="red">${!empty applicationScope.online?applicationScope.online:'0'}</font>人
		你是第：<font color="red">${!empty sessionScope.caller?sessionScope.caller:'0'}</font>个来访者
	</div>
  </body>
</html>
