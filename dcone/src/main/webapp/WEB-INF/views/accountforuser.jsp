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
#layout { 
	background:#000; 
	position:absolute; 
	top:0; 
	left:0; 
	width:100%; 
	height:100%; 
	opacity:0.5; 
	filter:alpha(opacity=50); 
	cursor:pointer;
	display: none;
}
.div1 {
	position: absolute;
	width:400px; 
	height:250px;
	left: 55%;
	top: 50%;
	text-align:center; 
	margin:-200px auto 0 -250px; 
	background:#fafafa; 
	z-index:999; 
	border:2px solid #eee; 
	border-radius:5px; 
	box-shadow:0 0 40px #333; 
	display: none;
}
#closed { 
	width:20px; 
	height:20px; 
	text-align:center; 
	background:#800000; 
	color:#fff; 
	position:absolute; 
	top:-10px; 
	right:-10px; 
	cursor:pointer; 
}
.user1{
	position: absolute;
	float:left;
	left: 200px;
	top: 0px;
	right: 0px;
	width: 800px;
	height:90%;
	background-image: url("img/userback.jpg");
	background-size: cover;
	color:white;
	font-weight: 700;
}
.form-group{
	position:absolute;
	float:left;
	z-index:10;
	margin:0px;
}
.out{
	position:absolute;
	left:30%;
	top:60%;
	background-color: #666666;
	width: 300px;
	text-align: center;
	font-size:large;
	display: none;
}
</style>
<script type="text/javascript">
function check() {
	var password = $("#password").val();
	var newpassword = $("#newpassword").val();
	var renewpassword = $("#renewpassword").val();
	if(password==""||newpassword==""||renewpassword==""){
		$("#info").html("输入不能为空");
		return false;
	}else if(newpassword !=renewpassword){
		$("#info").html("确认密码与新密码不匹配");
		return false;
	}else{
		$("#info").html("");
	}
		return true;
}

function checkimage() {
	if($("#image").val()!=null){
		return true
	}
	return false
}
	$(document).ready(function(){
		$("#closed").click(function(){
			$("#password").val("");
			$("#newpassword").val("");
			$("#renewpassword").val("");
			$("#div1").hide()
			$("#layout").hide()
		});
		$("#change").click(function(){
			$("#div1").show()
			$("#layout").show()
			$(this).disabled
		});
		$("#sub").click(function(){
			if(check()){
			$.post("changepassword",{
				password: $("#password").val(),
				newpassword: $("#newpassword").val(),
				renewpassword: $("#renewpassword").val()
			},function(data,status){
				if(data==1){
					$(".out").html("密码修改成功");
					$(".out").show();
					setTimeout(() => {
						$(".out").hide();
					}, 3000);

				}else if(data==0){
					$("#div3").html("密码输入错误");
					$("#div3").show();
					setTimeout(() => {
						$("#div3").hide();
					}, 3000);
				}
				$("#password").val("");
				$("#newpassword").val("");
				$("#renewpassword").val("");
				$("#div1").hide()
				$("#layout").hide()
			});
			}
		});
		$("#changeimage").click(function(){
			if($("#image").val() != '') 
				$("#subimg").submit();
		});
	});
</script>
</head>
<body >

<div id="layout"></div>
<div id="div1" class="div1">
	<div id="closed">X</div>
	<form class="form-horizontal" role="form">
	<div id="info" style="color: red;"></div>
	<table style="position:absolute; left: 25%; top:10%;padding: 10px;">
	<tr><td align="left">原密码：</td><td><input type="password" class="form-control" style="width: 80%" id="password" placeholder="请输入密码"></td></tr>
	<tr><td align="left">新密码：</td><td><input type="password" class="form-control" style="width: 80%" id="newpassword" placeholder="请输入密码"></td></tr>
	<tr><td align="left">确认密码：</td><td><input type="password" class="form-control" style="width: 80%" id="renewpassword" placeholder="请输入密码"></td></tr>
	</table>
   	<div class="form-group" style="position: absolute;left: 50%;top: 60%;">
    	<div class="col-sm-offset-2 col-sm-10" style="left: -60px">
      		<button id="sub" type="button" class="btn btn-info">修改密码</button>
    	</div>
  	</div>
  	</form>
</div>

<div style="position:absolute;left: 10%;right: 10%;top:10%; height: 90%;">
<div style="float:left; width: 200px;height: 200px">
<ul class="nav nav-pills nav-stacked">
	<li class="active"><a href="accountforuser">个人账户</a></li>
	<li><a href="walletforuser">钱包信息</a></li>
	<li><a href="recordsforuser">交易记录</a></li>
</ul>
</div>
<div style="position:relate;width:5%;"></div>
<div style="float:left; height: 100%">
<div class="user1">
<div class="out">更改密码</div>
	<div style="position: absolute;top: 80px;width: 200px; height: 185px; z-index:10;left: 60%;"><!-- 图片以及上传图片 -->
		<img width="200px" height="150px" alt="" src="getPhoto">
		<form id="subimg" action="setpicture" method="post" enctype="multipart/form-data" onsubmit="return checkimage()">
		<p>
			<input type="file" name="picture" id="image">
  			<button id="changeimage" style=" position:relative; left: 80px" type="button" class="btn btn-primary">更换头像</button>
		</p>
		</form>
	</div>
	<div style="position: absolute;top:30%; left:10%;">
		<table style="color: black;">
		<tr><td>账号：</td><td><input type="text" disabled="disabled" value="${user.itcode }"></td></tr>
		<tr><td>姓名：</td><td><input type="text" disabled="disabled" value="${user.username }"></td></tr>
		<tr><td>密码：</td><td><input type="password" disabled="disabled" value="000000"></td><td><button id="change" type="button" class="btn btn-primary">更改密码</button></td></tr>
		</table>
  	</div>
</div>
</div>
</div>
</body>
</html>