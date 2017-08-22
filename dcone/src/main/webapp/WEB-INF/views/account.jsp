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
.div2 {
	position: absolute;
	width:300px; 
	height:200px;
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

.closed { 
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
.out{
	position:absolute;
	left:40%;
	top:46%;
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
function checkmoney(){
	var money = $("#amount").val();
	if(money=="")
		{
		$("#info2").html("输入不能为空");
		return false
		}else if(money<=0){
			$("#info2").html("充值金额必须大于0");
			return false;
		}
	else{
		$("#info2").html("");
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
		$(".closed").click(function(){
			$("#password").val("");
			$("#newpassword").val("");
			$("#renewpassword").val("");
			$("#amount").val("");
			$("#div1").hide()
			$("#div2").hide()
			$("#layout").hide()
			$("#info2").html("");
		});
		$("#change").click(function(){
			$("#div1").show()
			$("#layout").show()
			$(this).disabled
		});
		$("#addmoney").click(function(){
			$("#div2").show();
			$("#layout").show();
		})
		$("#sub").click(function(){
			if(check()){
			$.post("changepassword",{
				password: $("#password").val(),
				newpassword: $("#newpassword").val(),
				renewpassword: $("#renewpassword").val()
			},function(data,status){
				if(data==1){
					$("#div3").html("密码修改成功");
					$("#div3").show();
					setTimeout(() => {
						$("#div3").hide();
					}, 3000);
				}
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
		$("#submit").click(function(){
			if(checkmoney()){
				$.post("balance_adding",{
					amount:$("#amount").val()
				},function(data,status){
					if(data!=0){
						$("#money").html(data);
						$("#div3").html("您成功充值"+$("#amount").val()+"元");
						$("#div3").show();
						setTimeout(() => {
							$("#div3").hide();
						}, 3000);
					} else{
						$("#div3").html("充值失败");
						$("#div3").show();
						setTimeout(() => {
							$("#div3").hide();
						}, 3000);
					}
					$("#amount").val("");
					$("#div2").hide()
					$("#layout").hide()
				})
			}
		})
		$("#changeimage").click(function(){
			if($("#image").val() != '') 
				$("#subimg").submit();
		});
	});
</script>
</head>
<body>
<div id="layout"></div>
<div id="div3" class="out"></div>
<div id="div1" class="div1">
	<div class="closed">X</div>
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
      		<button id="sub" type="button" class="btn btn-info">修改密码</button>
    	</div>
  	</div>
  	</form>
</div>
<div id="div2" class="div2">
	<div class="closed">X</div>
	<div id="info2" style="color: red;"></div>
	<form>
		<div class="form-group" >
    	<label for="amount" style="position:absolute;top: 30px;left: 80px;">您的充值金额为</label></td>
    	<div class="col-sm-10" style="top: 20px;">
      		<input type="text" class="form-control" style="position:absolute; left: 20px;top: 20px;" id="amount" placeholder="请输入充值金额">
    	</div>
    	</div>
    	<div class="form-group">
    	<div class="col-sm-offset-2 col-sm-10" style="top: 90px;left:-40px;">
      		<button id="submit" type="button" class="btn btn-info">充值</button>
    	</div>
  	</div>
	</form>


</div>

<div style="position:absolute;left: 10%;right: 10%;top: 10%;height: 80%;">
<div style="position:relative; width: 200px;height: 200px">
<ul class="nav nav-pills nav-stacked">
	<li  class="active"><a href="account">个人账户</a></li>
	<li><a href="accounts">用户账户</a></li>
	<li><a href="wallets">钱包信息</a></li>
	<li><a href="program">节目列表</a></li>
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
	<div style="position: absolute;left: 10%;top: 20%;">
	<table>
		<caption style="font-size:xx-large;position:relative;color:black; left: 50px;">账户信息</caption>
		<tr><td>账号：</td><td><input type="text" disabled="disabled" value="${user.itcode }"></td></tr>
		<tr><td>姓名：</td><td><input type="text" disabled="disabled" value="${user.username }"></td></tr>
		<tr><td>密码：</td><td><input type="password" disabled="disabled" value="000000"></td><td><button id="change" type="button" class="btn btn-primary">更改密码</button></td></tr>
		<tr><td>余额：</td><td><input id="money" type="text" disabled="disabled" value="${amount }"></td><td><button id="addmoney" type="button" class="btn btn-primary">充值</button></td></tr>
	</table>
	</div>

	<div style="position: absolute;top: 20%;left:60%;width: 200px;height: 180px;">
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