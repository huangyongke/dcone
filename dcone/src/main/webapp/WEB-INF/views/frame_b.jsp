<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.div1{
background-image: url("img/outputbox.jpg");
background-size: cover;
font-size: 15px;
}

</style>
</head>
<body >
<div id="div1">
<table style="width: 100%;">
<c:forEach items="${messages }" var="message">
<% 
	String itcode=(String)session.getAttribute("itcode");
	String username =(String)session.getAttribute("username");
%>
	<c:choose>
		<c:when test="${message.itcode == itcode}">
		<tr><td><div align="right"><table>
		<tr><td align="right">${message.talktime }</td><td>&nbsp;&nbsp;&nbsp;${message.username }</td><td rowspan="2"><img width="40px" height="30px" alt="" src="photo?itcode=${message.itcode }"></td></tr>
		<tr><td align="right">${message.message}</td><td></td></tr>
		</table></div></td></tr>
		</c:when>
		
		<c:otherwise>
		<tr><td><div><table>
		<tr><td rowspan="2"><img width="40px" height="30px" alt="" src="photo?itcode=${message.itcode }"></td><td>${message.username }&nbsp;&nbsp;&nbsp;</td><td>${message.talktime }</td></tr>
		<tr><td></td><td>${message.message}</td></tr>
		</table></div></td></tr>
		</c:otherwise>
	</c:choose>
	
</c:forEach>
</table>
</div>
</body>
</html>