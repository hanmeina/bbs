<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <body>
    <jsp:include page="/WEB-INF/jsp/header.jsp"/>
    <table border="1" align="center" width="60%">
    	<caption><h1>论坛系统</h1></caption>
    	<tr>
    		<th>图标</th>
    		<th>名称</th>
    		<th>点击数</th>
    		<th>版主</th>
    	</tr>
    	<c:forEach var="type" items="${typeList}">
    		<tr>
    			<td>
    				<img src="${type.image}" width="40" height="30"/>
    			</td>
    			<td>${type.title}</td>
    			<td>${type.click}</td>
    			<td>${type.admin.name}</td>
    		</tr>
    	</c:forEach>
    </table>
    <jsp:include page="/WEB-INF/jsp/footer.jsp"/>
  </body>
</html>
