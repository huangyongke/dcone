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
				if(data==1)
					alert("密码修改成功");
				else if(data==0){
					alert("您的密码输入错误")	
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
<body>
<div id="layout"></div>
<div id="div1" class="div1">
	<div id="closed">X</div>
	<form class="form-horizontal" role="form">
	<div id="info" style="color: red;"></div>
	<div class="form-group" >
    	<label for="password" class="col-sm-2 control-label" style="top: 15px;">密码</label></td>
    	<div class="col-sm-10" style="top: 20px;">
      		<input type="password" class="form-control" style="width: 80%" id="password" placeholder="请输入密码">
    	</div>
    </div>
    <div class="form-group">
    	<label for="newpassword"  class="col-sm-2 control-label" style="top: 15px;">新密码</label></td>
    	<div class="col-sm-10" style="top: 20px;">
      		<input type="password" class="form-control" style="width: 80%" id="newpassword" placeholder="请输入新密码">
    	</div>
    </div>
    <div class="form-group">
    	<label for="renewpassword" class="col-sm-2 control-label" style="top: 15px;">确认密码</label></td>
    	<div class="col-sm-10" style="top: 20px;">
      		<input type="password"  class="form-control" style="width: 80%" id="renewpassword" placeholder="请输入新密码">
    	</div>
    </div>
  	<div class="form-group">
    	<div class="col-sm-offset-2 col-sm-10" style="left: -60px">
      		<button id="sub" type="button" class="btn btn-default">修改密码</button>
    	</div>
  	</div>
  	</form>
</div>




<div style="position:absolute;left: 10%;right: 10%;top: 10%">
<div style="position: absolute;left: 200px;top: 0px;right: 0px;width: 800px;height: 100%">
	<div class="form-group">
    	<label class="col-sm-2 control-label">账号：</label>
    	<div class="col-sm-10">
      	<p class="form-control-static">${user.itcode }</p>
    	</div>
  	</div>
  	<div class="form-group">
    	<label class="col-sm-2 control-label">姓名：</label>
    	<div class="col-sm-10">
      	<p class="form-control-static">${user.username }</p>
    	</div>
  	</div>
  	<p>
  		<button id="change" type="button" class="btn btn-primary">更改密码</button>
	</p>
	<div style="position: absolute;top: 0px;right: 40px;width: 200px;">
		<img alt="" src="getPhoto">
		<form id="subimg" action="setpicture" method="post" enctype="multipart/form-data" onsubmit="return checkimage()">
		<p>
			<input type="file" name="picture" id="image">
  			<button id="changeimage" style=" position:static; left: 40px" type="button" class="btn btn-primary">更换头像</button>
		</p>
		</form>
	</div>
</div>
</div>
</body>
</html>