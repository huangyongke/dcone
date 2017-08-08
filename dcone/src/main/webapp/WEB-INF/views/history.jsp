<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function check() {
	var date=document.from1.date.value
	var patern = new RegExp("^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$")
	var is=true
	if(date==""){
		document.getElementById("date").innerHTML="日期不能为空"
		is=false
	}
	else if(!patern.exec(date)){
		document.getElementById("date").innerHTML="日期格式不正确"	
		is = false
	}
	else
		document.getElementById("date").innerHTML=""
	return is
}
</script>

</head>
<body>
<form name="from1" action="balance_getting" onsubmit="return check()">
<table>
<tr><td>请选择日期:</td><td><input name="date" onblur="check()"></td><td><div id="date" style="color: red;"></div></td></tr>
<tr><td></td><td><input type="submit" value="查找"></td></tr>
</table>
</form>
</body>
</html>