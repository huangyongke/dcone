<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="css/bootstrap.min.css">  
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<div style="position:absolute;left: 10%;right: 10%;">
<div style="position:relative; width: 200px;height: 200px">
<ul class="nav nav-pills nav-stacked">
	<li  class="active"><a href="account">个人账户</a></li>
	<li><a href="accounts">用户账户</a></li>
	<li><a href="#"></a></li>
</ul>
<div style="position: absolute;left: 200px;top: 0px;right: 0px;">
<table>
<tr><td>账号：</td><td><input type="text" disabled="disabled">${user.itcode }</td></tr>
<tr><td>姓名：</td><td><input type="text" disabled="disabled">${user.username }</td></tr>
<tr><td>姓名：</td><td><input type="text" disabled="disabled">${user.username }</td></tr>
</table>
</div>
</div>
</div>
</body>
</html>