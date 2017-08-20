<%@ page import="com.dcone.dtss.model.*" %>
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
.left{
	float:left;
	padding:5px;
	min-width:20px;
	min-height:20px;
	opacity:0.6;
	border: 1px solid;
	border-radius: 10px;
	box-shadow:1px 1px 2px hsla(0, 0%, 0%, 0.3); 
	background: -moz-linear-gradient(top, #ffffff 0%, #A6DADC 100%);
    background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#ffffff), color-stop(100%,#A6DADC));
    background: -webkit-linear-gradient(top, #ffffff 0%,#A6DADC 100%);
    background: -o-linear-gradient(top, #ffffff 0%,#A6DADC 100%);
    background: -ms-linear-gradient(top, #ffffff 0%,#A6DADC 100%);
    background: linear-gradient(to bottom, #ffffff 0%,#A6DADC 100%);
}
.right{
	float:right;
	min-width:20px;
	min-height:20px;
	padding:5px;
	opacity:0.6;
	border: 1px solid;
	border-radius: 10px; 
	box-shadow:1px 1px 2px hsla(0, 0%, 0%, 0.3); 
	background: -moz-linear-gradient(top, #ffffff 0%, #A6DADC 100%);
    background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#ffffff), color-stop(100%,#A6DADC));
    background: -webkit-linear-gradient(top, #ffffff 0%,#A6DADC 100%);
    background: -o-linear-gradient(top, #ffffff 0%,#A6DADC 100%);
    background: -ms-linear-gradient(top, #ffffff 0%,#A6DADC 100%);
    background: linear-gradient(to bottom, #ffffff 0%,#A6DADC 100%);
}
/* .left:before{  
    position:relative;
    content:"\00a0";  
    width:0px;  
    height:0px;  
    border-width:8px 18px 8px 0;  
    border-style:solid;  
    border-color:transparent #A6DADC transparent transparent;  
    top:5px;  
    right:100%;  
}  */
/* .right:after{  
    position:absolute;  
    content:"\00a0";  
    display:inline-block;  
    width:0px;  
    height:0px;  
    border-width:8px 0px 8px 18px;  
    border-style:solid;  
    border-color:transparent transparent transparent #A6DADC;  
    right:-30%; 
    top:5px;
}
 */</style>
<script type="text/javascript">
<%
	if((dc_user)session.getAttribute("user")==null){
%>
		window.parent.location.href="login";
<% 	
	}
%>
</script>
</head>
<body >
	
<div id="div1">
<table style="width: 100%;">
<c:forEach items="${messages }" var="message">
	<c:choose>
		<c:when test="${message.itcode == user.itcode}">
		<tr><td><div align="right"><table>
		<tr><td align="right">${message.talktime }</td><td>&nbsp;&nbsp;&nbsp;${message.username }</td><td rowspan="2"><img width="40px" height="30px" alt="" src="photo?itcode=${message.itcode }"></td></tr>
		<tr><td><div class="right">${message.message}</div></td><td></td></tr>
		</table></div></td></tr>
		</c:when>
		
		<c:otherwise>
		<tr><td><div><table>
		<tr><td rowspan="2"><img width="40px" height="30px" alt="" src="photo?itcode=${message.itcode }"></td><td>${message.username }&nbsp;&nbsp;&nbsp;</td><td>${message.talktime }</td></tr>
		<tr><td></td><td><div class="left">${message.message}</div></td></tr>
		</table></div></td></tr>
		</c:otherwise>
	</c:choose>
	
</c:forEach>
</table>
</div>
</body>
</html>