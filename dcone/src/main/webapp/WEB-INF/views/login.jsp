<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="css/bootstrap.min.css">  
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
<title>登录</title>

<style type="text/css">
body {
	background-image: url(img/luanchbackground.jpg);
	background-size: cover;
}
.out{
	position:absolute;
	left:38%;
	top:5%;
	background-color: #666666;
	width: 300px;
	text-align: center;
	font-size:large;
	display: none;
}
.outter{
	position: absolute;
	top:12%;
	left: 40%;
}
.inn1{
	width:235px;
	height:38px;
	border-radius:10px;
	margin-top:20px;
	margin-bottom:10px;
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

</style>
<script type="text/javascript">
function check(){
	var password=document.form1.password.value
	var itcode=document.form1.itcode.value
	if(password==""||itcode=="")
		return false
	else
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
function checkname(){
	if(check()){
	var httprequest=initAjax();
	var itcode=document.getElementById("itcode").value;
	var password=document.getElementById("password").value;
	var imagecheck=document.getElementById("imagecheck").value
	httprequest.open("post", "signin", true);
	httprequest.onreadystatechange = function(){
		if(httprequest.readyState==4)
			{
			if(httprequest.status == 200) {
				if(httprequest.responseText==1)
					{
					window.location.href="index.html";
					changed()
					}
				if(httprequest.responseText==3)
					{
						window.location.href="index4.html";
						changed()
					}
				else if(httprequest.responseText==2){
					$(".out").html("登录失败！验证码错误");
					$(".out").show();
					setTimeout(() => {
						$(".out").hide();
					}, 3000);
					changed()
				}
				else if(httprequest.responseText==0){
					$(".out").html("登录失败！密码错误");
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
	var pr="itcode="+itcode+"&password="+password+"&imagecheck="+imagecheck;
	httprequest.send(pr);
	}
}

function changed() {
	document.getElementById("image").src="checkimage?"+Math.random();
	
}
</script>


</head>
<body>
<div class="out">密码错误</div>
<form action="" name="form1" method="post">
<div class=outter >
<table >
<tr><td colspan="2"><div id="info" style="color: red;"></div></td></tr>
<tr><td align="center" colspan="2"><img src="img/36ad55dd352e493c3ed8a53f314fb21f.png" width="100px" height="100px" style="opacity:0.8"></td></tr>
<tr><td colspan="2"><input name="itcode" id="itcode" placeholder="请输入员工号" class=inn1></td></tr>
<tr><td colspan="2"><input type="password" name="password" id="password" placeholder="请输入密码" class=inn1></td></tr>
<tr><td><input name="imagecheck" id="imagecheck" placeholder="请输入验证码" class=inn2></td><td align="center" valign="middle"><img name="image" id="image" alt="" src="checkimage" onclick="changed()"></td></tr>
<tr><td align="center" valign="middle"><input type="button" value="登录" onclick="checkname()" class="inn3"></td><td align="center" valign="middle"><input type="button" value="注册" onclick="register()" class="inn3"></tr>
</table>
</div>
</form>
</body>
<script type="text/javascript">
 function register() {
		window.location.href="register";
	}
</script>
</html>