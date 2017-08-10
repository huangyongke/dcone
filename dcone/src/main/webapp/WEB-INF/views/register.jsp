<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<script type="text/javascript">
function check() {
	var is=true
	var itcode=document.form1.itcode.value
	var username=document.form1.username.value
	var password=document.form1.password.value
	var repassword=document.form1.repassword.value
	if(itcode==""){
		document.getElementById("itcode").innerHTML="员工号不能为空"
		is=false
	}
	else{
		document.getElementById("itcode").innerHTML=""
	}
	if(username=="")
		{
		document.getElementById("username").innerHTML="姓名不能为空"
		is=false
		}
	else
		document.getElementById("username").innerHTML=""
	if(password=="")
		{
		document.getElementById("password").innerHTML="密码不能为空"
		is=false
		}
	/* else if(password.length()<6)
		{
		document.getElementById("password").innerHTML="密码不能小于6位数"
			is=false
		}  */
	else
		document.getElementById("password").innerHTML=""
	if(repassword==""){
		document.getElementById("repassword").innerHTML="密码不能为空"
		is=false
	}
	else if(password!=repassword){
		document.getElementById("repassword").innerHTML="确认密码与密码不符合"
		is=false
	}
	else 
		document.getElementById("repassword").innerHTML=""
	if(!is)
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
 function changed() {
		document.getElementById("image").src="checkimage?"+Math.random();
		
	}
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
					document.getElementById("info").innerHTML="注册失败！用户名和员工号不匹配;";
					}
				else if(h==1)
					{
					document.getElementById("info").innerHTML="注册成功!前去<a href='login'>登录</a>";
					//window.location.href="register.jsp";
					}
				else if(h==2)
					{
					document.getElementById("info").innerHTML="注册失败！";
					}
				else if(h==3){
				document.getElementById("info").innerHTML="注册失败！该账户已经设置密码";
				}
				else if(h==4){
				document.getElementById("info").innerHTML="注册失败！验证码错误";
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
<form action="" name="form1" method="get" onsubmit="">
<table style="position:absolute;left: 35%;top: 10%;background-image: url('background.gif');" cellspacing="10">
<tr><td colspan="3"><div id="info" style="color: red;"></div></td></tr>
<caption style="font-size: large;">注册</caption>
<tr>
	<td colspan="3"><div id="info" style="color: red;"></div></td>
</tr>
<tr>
	<td>员工号：</td>
	<td><input type="text" name="itcode" onblur="check()" style="width: 100%"></td>
	<td><div id="itcode" style="color: red;">员工号不能为空</div></td>
</tr>
<tr>
	<td>姓名：</td>
	<td><input type="text" name="username" onblur="check()" style="width: 100%"></td>
	<td><div id="username" style="color: red;">姓名不能为空</div></td>
</tr>
<tr>
	<td>密码：</td>
	<td><input type="password" name="password"  onblur="check()" style="width: 100%"></td>
	<td><div id="password" style="color: red;">密码不能为空</div></td>
</tr>
<tr>
	<td>确认密码：</td>
	<td><input type="password" name="repassword" style="width: 100%" onblur="check()"></td>
	<td><div id="repassword" style="color: red;">密码不能为空</div></td>
</tr>
<tr>
	<td>验证码:</td>
	<td><input name="imagecheck" id="imagecheck" style="width: 100%"></td>
	<td><img name="image" id="image" alt="" src="checkimage"  onclick="changed()"></td>
</tr>

<tr><td colspan="3" align="center"><input type="button" value="注册" onclick="checkname()"></td></tr>
</table>
</form>
</body>
</html>