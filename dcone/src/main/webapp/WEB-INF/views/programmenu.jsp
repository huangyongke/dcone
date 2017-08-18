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
th,tr,td{
	
}

</style>
</head>
<body>

<script type="text/javascript">
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
function submit1(a,b){
	var money = document.getElementById(a).value
	document.getElementById(a).value=""
	var httprequest=initAjax();
	if(money!=""){
	httprequest.open("post", "setReward", true);
	httprequest.onreadystatechange = function(){
		if(httprequest.readyState==4){
			if(httprequest.status == 200) {
				if(httprequest.responseText==0){
					alert("打赏失败,余额不足")
				}
				else  {
					alert("打赏成功")
					//$("#"+b).html=httprequest.responseText
					document.getElementById(b).innerHTML=httprequest.responseText
				}
			}
		}
		
	};
	httprequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
	var pr="pid="+a+"&money="+money;
	httprequest.send(pr);
	}
}


</script>
<div style="position:absolute;left: 10%;right: 10%;top: 10%">
<form action="" method="get" id="fram1" name="fram1">
<table class="table table-bordered">
<tr><td align="center">节目名称</td><td align="center">表演者</td><td align="center">表演时间</td><td align="center">部门</td><td align="center">总打赏金额</td><td align="center">打赏</td></tr>
<c:forEach items="${menu }" var="program">
<tr><td>${program.program}</td><td>${program.actor }</td><td>${program.showtime }</td>
	<td align="center">${program.department }</td><td align="center"><div id="${program.sequence }">${program.reward}</div></td>
	<td style="width: 25%;"><div class="input-group" >
			<input id="${program.pid }" class="form-control" placeholder="输入打赏金额">
				<span class="input-group-btn">
				<button class="btn btn-default" type="button" onclick="submit1(${program.pid},${program.sequence})">打赏</button> 
				</span></div></td></tr>
</c:forEach>

</table>
</form>
</div>
</body>
</html>
