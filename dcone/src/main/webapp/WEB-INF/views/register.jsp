<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="css/bootstrap.min.css">  
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
<title>注册</title>
<style>
body {
	background-image: url(img/luanchbackground.jpg);
	background-size: cover;
}
.outter{
	
	position: relative;
	top:75px;
	left: 500px;
}
.inn1{
	width:235px;
	height:38px;
	border-radius:10px;
	margin-top:3px;
	margin-bottom:3px;
	/*background-color:transparent;*/
	opacity:0.6;
	border:1.5px solid #ccc;
	transition:border-color ease-in-out .15s,box-shadow ease-in-out .15s;
}
.inn2{
	width:110px;
	height:38px;
	border-radius:10px;
	margin-top:20px;
	margin-bottom:10px;
	/*background-color:transparent;*/
	opacity:0.6;
	border:1.5px solid #ccc;
	transition:border-color ease-in-out .15s,box-shadow ease-in-out .15s;
}
.inn3{
	width:80px;
	height:30px;
	border-radius:10px;
	margin-top:20px;
	margin-bottom:10px;
	background-color:gray;
	opacity:0.6;
	border:1.5px solid #ccc;
	transition:border-color ease-in-out .15s,box-shadow ease-in-out .15s;
}
input:focus{
	border-color:#66AFE9 !important;
	outline:0;
	-webkit-box-shadow:insert 0 ipx 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,223,0.6);
	-moz-box-shadow:insert 0 ipx 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,223,0.6);
	box-shadow:insert 0 ipx 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,223,0.6);
}
::-webkit-input-placeholder{
	text-indent: 20px;
	font-size: 16px;
	text-align: center;
}
::-moz-input-placeholder{
	text-indent: 20px;
	font-size: 16px;
	text-align: center;
}
::-ms-input-placeholder{
	text-indent: 20px;
	font-size: 16px;
	text-align: center;
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
.out{
	position:absolute;
	left:39%;
	top:15%;
	background-color: #666666;
	width: 300px;
	text-align: center;
	font-size:large;
	display: none;
}

</style>
<script type="text/javascript">
function check() {
	var is=true
	var itcode=document.form1.itcode.value
	var username=document.form1.username.value
	var password=document.form1.password.value
	var repassword=document.form1.repassword.value
	if(itcode==""){
		$(".out").html("员工号不能为空");
		$(".out").show();
		setTimeout(() => {
			$(".out").hide();
		}, 3000);
		return false
	}
	if(username=="")
		{
		$(".out").html("姓名不能为空");
		$(".out").show();
		setTimeout(() => {
			$(".out").hide();
		}, 3000);
		return false
		}
	if(password=="")
		{
		$(".out").html("密码不能为空");
		$(".out").show();
		setTimeout(() => {
			$(".out").hide();
		}, 3000);
		return false
		}
	if(repassword==""){
		$(".out").html("密码不能为空");
		$(".out").show();
		setTimeout(() => {
			$(".out").hide();
		}, 3000);
		return false
	}
	else if(password!=repassword){
		$(".out").html("确认密码与密码不符合");
		$(".out").show();
		setTimeout(() => {
			$(".out").hide();
		}, 3000);
		return false
	}
	return true
}

 function initAjax(){
	var xmlhttp;
	try
	   {
	  // Firefox, Opera 8.0+, Safari
	   xmlhttp=new XMLHttpRequest();
	   }
	catch (e)
	   {

	 // Internet Explorer
	  try
	     {
	     xmlhttp=new ActiveXObject("Msxml2.XMLHTTP");
	     }
	  catch (e)
	     {
	     try
	        {
	        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	        }
	     catch (e)
	        {
	        alert("您的浏览器不支持AJAX！");
	        }
	     }
	   }
	return xmlhttp;
} 
 function changed() {
		document.getElementById("image").src="checkimage?"+Math.random();
		
	}
 
 
 	$(document).ready(function(){
 		$("#closed").click(function(){
			$("#div1").hide()
			$("#layout").hide()
		});
 		$("#sub").click(function(){
 			window.location.href="login";
 		})
 	});
 </script>
 <script type="text/javascript">
function checkname(){
	if(check()){
	var httprequest=initAjax();
	var itcode=document.form1.itcode.value
	var username=document.form1.username.value;
	var password=document.form1.password.value;
	var imagecheck=document.form1.imagecheck.value
	httprequest.open("post", "sign", true);
	httprequest.onreadystatechange = function(){
		if(httprequest.readyState==4){
			if(httprequest.status == 200) {
				var h=httprequest.responseText;
				if(h==0)
					{
					$(".out").html("注册失败！用户名和员工号不匹配;");
					$(".out").show();
					setTimeout(() => {
						$(".out").hide();
					}, 3000);
					document.getElementById("info").innerHTML="注册失败！用户名和员工号不匹配;";
					changed()
					}
				else if(h==1)
					{
					$('#layout').show();
					$('#div1').show();
					changed()
					}
				else if(h==2)
					{
					$(".out").html("注册失败！");
					$(".out").show();
					setTimeout(() => {
						$(".out").hide();
					}, 3000);
					document.getElementById("info").innerHTML="注册失败！";
					changed()
					}
				else if(h==3){
					$(".out").html("注册失败！该账户已经设置密码");
					$(".out").show();
					setTimeout(() => {
						$(".out").hide();
					}, 3000);
				document.getElementById("info").innerHTML="<a href='login'>前去登录</a>";
				changed()
				}
				else if(h==4){
					$(".out").html("注册失败！验证码错误");
					$(".out").show();
					setTimeout(() => {
						$(".out").hide();
					}, 3000);
				changed()
				}
			}
		}
	};
	httprequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
	var pr="itcode="+itcode+"&username="+username+"&password="+password+"&imagecheck="+imagecheck;
	httprequest.send(pr);
	}
	
} 
</script>
<script type="text/javascript">

</script>
</head>

<body>
<div class="out"></div>
<div id="layout"></div>
<div id="div1" class="div1">
	<div id="closed">X</div>
	<span style="position: absolute;top: 20%;left:35%;font-size:xx-large; ;">注册成功</span><br>
	<p style="position: absolute;top: 40%;left:40%;font-size: large;">前去登录</p>
   	<div class="form-group" style="position: absolute;left: 50%;top: 60%;">
    	<div class="col-sm-offset-2 col-sm-10" style="left: -60px">
      		<button id="sub" type="button" class="btn btn-info">登录</button>
    	</div>
  	</div>
</div>

<form action="" name="form1" method="get" onsubmit="">
<table style="position:absolute;left: 40%;top: 20%;">
<caption style="font-size:xx-large;position:relative; left: 90px;">注册</caption>
<tr><td colspan="2" align="center"><div id="info" style="color: red;"></div></td></tr>
<tr>
	<td colspan="2" ><input type="text" name="itcode"  style="width: 100%" placeholder="请输入用户账号" class="inn1"></td>
</tr>
<tr>
	<td colspan="2" ><input type="text" name="username" style="width: 100%" placeholder="请输入姓名" class="inn1"></td>
</tr>
<tr>
	<td colspan="2" ><input type="password" name="password" style="width: 100%" placeholder="请输入密码" class="inn1"></td>
</tr>
<tr>
	<td colspan="2" ><input type="password" name="repassword" style="width: 100%" placeholder="确认密码" class="inn1"></td>
</tr>
<tr>
	<td><input name="imagecheck" id="imagecheck" style="width: 100%" placeholder="请输入验证码" class="inn2"></td>
	<td><img name="image" id="image" alt="" src="checkimage"  onclick="changed()"></td>
</tr>
<tr><td colspan="2" align="center"><input type="button" value="注册" onclick="checkname()" class="inn3"></td></tr>
</table>
</form>
</body>
</html>