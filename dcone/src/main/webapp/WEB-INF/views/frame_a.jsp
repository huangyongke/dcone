<%@page import="java.awt.image.BufferedImage" %>
<%@page import="java.util.ArrayList"%>
<%@page import="com.dcone.dtss.model.*" %>
<%@page import="java.awt.*" %>
<%@page import="java.io.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
<table border="1" width="200" style="float:center;">
<%
	int counts=0;
	if(application.getAttribute("onlinecount")!=null)
		counts=((Integer)application.getAttribute("onlinecount")).intValue();
	%>
	<tr align="center" height="50px"><td>在线人数：</td><td><%=counts %></td></tr>
		<%	
	if(application.getAttribute("onlineuser")!=null)
	{
	ArrayList<dc_user> users=(ArrayList<dc_user>)application.getAttribute("onlineuser");
	for(dc_user user:users)
	{	
%>
	<tr align="center"><td><%  out.print(user.getUsername()); %></td><td><img width="60px" height="40px" alt="" src="photo?itcode=<%=user.getItcode() %>"></td></tr>
<%
	}
	}
%>
</table>

</body>
</html>