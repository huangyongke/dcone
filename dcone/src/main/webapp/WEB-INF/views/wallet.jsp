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
	<li class="active"><a href="wallets">钱包信息</a></li>
	<li><a href="records">交易记录</a></li>
	<li><a href="#"></a></li>
</ul>
</div>
<div style="position: absolute;left: 200px;top: 0px;right: 0px;">
<form action="wallets" class="bs-example bs-example-form" role="form">
<table class="table table-striped">
<tr><td style="width: 35%;"><input id="itcode" name="itcode" type="text" class="form-control" placeholder="输入员工号"></td>
	<td style="width: 35%;">
		<div class="input-group" >
			<input id="username" name="username" type="text" class="form-control" placeholder="输入姓名">
				<span class="input-group-btn">
				<button id="button2" class="btn btn-default" type="submit">查询</button> 
				</span></div></td>
</tr>
<tr><td>员工号</td><td>姓名</td><td>余额</td></tr>
<c:forEach items="${wallets }" var="wallet">
<tr><td>${wallet.itcode }</td><td>${wallet.username }</td><td>${wallet.amount}</td></tr>
</c:forEach>
</table>
</form>
</div>
</div>
</body>
</html>