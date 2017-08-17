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
	<li class="active"><a href="program">节目列表</a></li>
	<li><a href="">新建节目</a></li>
	<li><a href="rewardrecords">打赏记录</a></li>
</ul>
</div>
<div style="position: absolute;left: 200px;top: 0px;right: 0px;">
<form action="program" class="bs-example bs-example-form" role="form">
<table class="table table-striped">
<tr><td colspan="2" style="width: 40%;">
	<input id="program" name="program" type="text" class="form-control" placeholder="输入节目名称"></td>
	<td><input id="actor" name="actor" type="text" class="form-control" placeholder="输入表演者"></td>
	<td colspan="2" style="width: 40%;">
		<div class="input-group" >
			<input id="department" name="department" type="text" class="form-control" placeholder="输入节目部门">
				<span class="input-group-btn">
				<button id="button2" class="btn btn-default" type="submit">查询</button> 
				</span></div></td>
</tr>
<tr><td>节目名称</td><td>表演者</td><td>总打赏金额(元)</td><td>表演时间</td><td>节目部门</td></tr>
<c:forEach items="${programs }" var="program">
<tr><td>${program.program }</td><td>${program.actor }</td><td>${program.reward }</td><td>${program.showtime }</td><td>${program.department }</td></tr>
</c:forEach>
</table>
</form>
</div>
</div>
</body>
</html>