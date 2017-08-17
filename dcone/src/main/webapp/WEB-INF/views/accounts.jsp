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
<script type="text/javascript">

function send(a) {
	$.post("freeze",{
		uid:a
	},
		function(data,status){
			if(data==1)
				alert("冻结成功");
			else if(data==2)
				alert("解冻成功");
	});
}

</script>
</head>
<body>
<div style="position:absolute;left: 10%;right: 10%;">
<div style="position:relative; width: 200px;height: 200px">
<ul class="nav nav-pills nav-stacked">
	<li><a href="account">个人账户</a></li>
	<li class="active"><a href="accounts">用户账户</a></li>
	<li><a href="#"></a></li>
</ul>
</div>
<div style="position: absolute;left: 200px;top: 0px;right: 0px;">
<form action="accounts" class="bs-example bs-example-form" role="form">
<table class="table table-striped">
<tr><td style="width: 25%;"><input id="itcode" name="itcode" type="text" class="form-control" placeholder="输入员工号"></td>
	<td style="width: 25%;">
		<div class="input-group" >
			<input id="username" name="username" type="text" class="form-control" placeholder="输入姓名">
				<span class="input-group-btn">
				<button id="button2" class="btn btn-default" type="submit">查询</button> 
				</span></div></td>
</tr>
<tr><td>员工号</td><td>姓名</td><td>场内/场外</td><td>状态</td><td>解冻/冻结</td></tr>
<c:forEach items="${accounts }" var="account">
<tr><td>${account.itcode }</td><td>${account.username }</td><td><c:choose><c:when test="${account.inside==1}">场内</c:when><c:otherwise>场外</c:otherwise></c:choose></td>
	<td><c:choose><c:when test="${account.locked==0}">正常</c:when><c:when test="${account.locked==1 }">冻结</c:when><c:otherwise>管理员</c:otherwise></c:choose></td><td width="80px"><button type="button" id="${account.uid}" onclick="send(${account.uid})">冻结</button></td></tr>
</c:forEach>

</table>
</form>
</div>
</div>
</body>
</html>