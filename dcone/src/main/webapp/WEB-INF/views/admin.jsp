<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function round1() {
	document.getElementById("round").value="1"
}
function round2() {
	document.getElementById("round").value="2"
}
function round3() {
	document.getElementById("round").value="3"
}

</script>
</head>
<body>
<form action="lucky_on" method="get">
<table>
<tr><td>第一轮红包雨</td><td><input type="submit" value="开始" id="button1" onmousedown="round1()"></td></tr>
<tr><td>第二轮红包雨</td><td><input type="submit" value="开始" id="button2" onmousedown="round2()"></td></tr>
<tr><td>第三轮红包雨</td><td><input type="submit" value="开始" id="button3" onmousedown="round3()"></td></tr>
<tr><td><a href="getRecord">详细信息</a></td></tr>
<tr><td><input type="hidden" name="round" id="round" value="0"></td></tr>
</table>
</form>
</body>
</html>