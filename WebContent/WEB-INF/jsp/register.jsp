<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <body>
    <jsp:include page="/WEB-INF/jsp/header.jsp"/>
    <form action="${pageContext.request.contextPath}/BbsServlet?method=register" method="post">
		<table border="1" align="center">
			<caption><h1>用户注册</h1></caption>
			<tr>
				<th>用户名</th>
				<td><input type="text" name="username" value="${registerForm.username}"/></td>
				<td>${registerForm.errors.username}</td>
			</tr>
			<tr>
				<th>MD5密码</th>
				<td><input type="password" name="password"/></td>
				<td>${registerForm.errors.password}</td>
			</tr>
	    	<tr>
				<th>性别</th>
				<td>
					<input ${registerForm.gender=='男'?'checked':''} type="radio" name="gender" value="男" checked/>男
					<input ${registerForm.gender=='女'?'checked':''} type="radio" name="gender" value="女"/>女
				</td>
			</tr>
			<tr>
				<th>邮箱</th>
				<td><input type="text" name="email" value="${registerForm.email}"/></td>
				<td>${registerForm.errors.email}</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="注册"/>
				</td>
			</tr>
		</table>
	</form>
    <jsp:include page="/WEB-INF/jsp/footer.jsp"/>
  </body>
</html>
