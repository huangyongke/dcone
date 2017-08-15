<%@page import="java.util.ArrayList"%>
<%@page import="org.springframework.jdbc.core.JdbcTemplate"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String name= (String)session.getAttribute("username");
%>
<div >
<form action="file.html">
<table>
<tr><td rowspan="2"><img width="150px" height="100px" name="image" id="image" alt="" src="getPhoto"></td><td colspan="2" align="center"><div style="border: 1px;solid: #000;" id="username"><%=name %></div></td></tr>
<tr><td><input type="submit" value="上传头像" ></td><td><input type="button" value="注销" onclick="back1()"></td></tr>
</table>
</form>

<table border="1" width="200" cellpadding="10" style="float:center;">
<%
	int counts=0;
	if(application.getAttribute("onlinecount")!=null)
		counts=((Integer)application.getAttribute("onlinecount")).intValue();
	%>
	<tr><td>在线人数：</td><td><%=counts %></td></tr>
		<%	
	if(application.getAttribute("onlineuser")!=null)
	{
	ArrayList<String> user=(ArrayList<String>)application.getAttribute("onlineuser");
	for(String u:user)
	{	
%>
	<tr><td><%  out.print(u); %></td></tr>
<%
	}
	}
%>
</table>
</div>
<div>
<table>
<c:forEach items="${messages }" var="dc_message">
<% 
	String itcode=(String)session.getAttribute("itcode");
	String username =(String)session.getAttribute("username");
%>
<tr><td><%out.print(username); %></td><td>${dc_message.talk_time }</td></tr>
<tr><td>${dc_message.message }</td></tr>
</c:forEach>
<%
	response.addHeader("refresh", "2");
%>
</table>



</div>
</body>
</html>