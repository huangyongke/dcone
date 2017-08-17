<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body >
<table>
<c:forEach items="${messages }" var="message">
<% 
	String itcode=(String)session.getAttribute("itcode");
	String username =(String)session.getAttribute("username");
%>
	<tr><td><div><table>
		<tr><td rowspan="2"><img width="40px" height="30px" alt="" src="photo?itcode=${message.itcode }"></td><td>${message.username }&nbsp;&nbsp;&nbsp;</td><td>${message.talktime }</td></tr>
		<tr><td></td><td>${message.message}</td></tr>
		</table></div></td></tr>
</c:forEach>
</table>
<script type="text/javascript">

</script>
</body>
</html>