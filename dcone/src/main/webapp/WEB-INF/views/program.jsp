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
<style type="text/css">
body{
	background-image: url("img/space.jpg");
	background-size: cover;
}
.user1{
	position: absolute;
	left: 200px;
	top: 0px;
	right: 0px;
	width: 800px;
	height: 100%;
	float:left;
	background-image: url("img/userback.jpg");
	background-size: cover;
	color:black;
	font-weight: 700;
	overflow: auto;
}

</style>

</head>
<body>
<div style="position:absolute;left: 10%;right: 10%;top: 10%;height: 80%;">
<div style="position:relative; width: 200px;height: 200px">
<ul class="nav nav-pills nav-stacked">
	<li><a href="account">个人账户</a></li>
	<li><a href="accounts">用户账户</a></li>
	<li><a href="wallets">钱包信息</a></li>
	<li class="active"><a href="program">节目列表</a></li>
	<li><a href="newprogram">录入节目</a></li>
	<li><a href="luckymoneyrain">红包雨发放</a></li>
	<li><a href="grabluckymoney">抢红包发放</a></li>
	<li><a href="records">交易记录</a></li>
	<li><a href="luckyrecords">红包雨记录</a></li>
	<li><a href="grabluckyrecords">抢红包记录</a></li>
	<li><a href="rewardrecords">打赏记录</a></li>
</ul>
</div>
<div class="user1">
<form action="program" class="bs-example bs-example-form" role="form">
<table class="table table-striped">
<tr><td colspan="2" style="width: 30%;">
	<input id="program" name="program" type="text" class="form-control" placeholder="输入节目名称"></td>
	<td colspan="2"><input id="actor" name="actor" type="text" class="form-control" placeholder="输入表演者"></td>
	<td colspan="2" style="width: 33%;">
		<div class="input-group" >
			<input id="department" name="department" type="text" class="form-control" placeholder="输入节目部门">
				<span class="input-group-btn">
				<button id="button2" class="btn btn-default" type="submit">查询</button> 
				</span></div></td>
</tr>
<tr style="color: white;" align="center"><td>节目名称</td><td>表演者</td><td>总打赏金额(元)</td><td>表演开始时间</td><td>表演结束时间</td><td>节目部门</td></tr>
<c:forEach items="${programs }" var="program">
<tr align="center"><td>${program.program }</td><td>${program.actor }</td><td>${program.reward }</td><td>${program.showtime }</td><td>${program.stoptime }</td><td>${program.department }</td></tr>
</c:forEach>
</table>
</form>
</div>
</div>
</body>
</html>