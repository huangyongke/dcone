<%@page import="com.dcone.dtss.dao.UserDAO"%>
<%@page import="com.dcone.dtss.model.*" %>
<%@page import="org.springframework.jdbc.core.JdbcTemplate"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
window.onload=function() 
{ 
var arr; 
	if(arr=document.cookie.match(/scrollTop=([^;]+)(;|$)/))
		document.documentElement.scrollTop=parseInt(arr[1]); 
	document.body.scrollTop=parseInt(arr[1]); 
} 
window.onbeforeunload=function(){ 
	var scrollPos; 
	if (typeof window.pageYOffset != 'undefined') { 
	   scrollPos = window.pageYOffset; 
	} 
	else if (typeof document.compatMode != 'undefined' && 
	     document.compatMode != 'BackCompat') { 
	   scrollPos = document.documentElement.scrollTop; 
	} 
	else if (typeof document.body != 'undefined') { 
	   scrollPos = document.body.scrollTop; 
	} 
	else
		scrollPos = document.getElementById("scroll").scrollHeight;
	document.cookie="scrollTop="+scrollPos; 
	}
</script>   
</head>
<body >
<div id="scroll" style="position:relative; width:600px; overflow-y:auto" >
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
<script type="text/javascript">
    var ex = document.getElementById("scroll");  
    ex.scrollTop = ex.scrollHeight;
</script>
</html>