<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<script type="text/javascript">
function check(){
	var username=document.form1.username.value
	var itcode=document.form1.itcode.value
	if(username==""||itcode=="")
		return false
	else
		return true
}
function changed() {
	document.getElementById("image").src="checkimage?"+Math.random();
	
}
</script>


</head>
<body>

<form action="signin" name="form1" method="post">
<table>
<tr><td colspan="3"><div id="info" style="color: red;"></div></td></tr>
<tr><td></td><td align="center" colspan="2">登录页面</td></tr>
<tr><td>员工号:</td><td colspan="2"><input name="itcode" id="itcode"></td></tr>
<tr><td>姓名:</td><td colspan="2"><input  name="username" id="username"></td></tr>
<tr><td>验证码:</td><td colspan="2"><input name="imagecheck" id="imagecheck"></td><td><img name="image" id="image" alt="" src="checkimage" onclick="changed()"></td></tr>
<tr><td></td><td><input type="submit" value="登录" onclick="checkname()"></td><td><input type="button" value="注册" onclick="register()"></tr>
</table>
</form>
</body>
<script type="text/javascript">
 function register() {
		window.location.href="register";
	}
</script>
</html>