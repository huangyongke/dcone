<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
function submit1(a){
	var money = document.getElementById(a).value
	var httprequest=initAjax();
	httprequest.open("post", "setReward", true);
	httprequest.onreadystatechange = function(){
		if(httprequest.readyState==4)
			{
			if(httprequest.status == 200) {
				
				if(httprequest.responseText==1){
					document.getElementById("info").innerHTML="打赏成功";
					<% System.out.println("打赏成功"); %>
					window.location.href="program";
					}

				else {
					document.getElementById("info").innerHTML="登录失败！";
					<% System.out.println("打赏失败"); %>
				}
				
				
			}
			}
		
	};
	httprequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
	var pr="pid="+a+"&money="+money;
	httprequest.send(pr);
}


<%-- function setId(a){
	document.getElementById("pid").value=a
	//var mon=prompt("请输入打赏金额","0");
		<% System.out.println("成功"); %>
	document.getElementById("money").value=document.getElementById(a).value	

$.ajax({
    type: "post",
    url: '/dtss/RewardController/setReward.hml',
    async:true,
    data: {money:$('#money').val(), pid:$('#pid').val()},
    dataType: "json",
    success: function(data){
    	if(data=="success"){
    		alert("打赏成功");
    		<%="success" %>
    	}
    	else
    		alert("账户余额不足");
    }
})
}
 --%>
 </script>
<form action="" method="get" id="fram1" name="fram1">
<table>
<tr><td colspan="3"><div id="info" style="color: red;">你好</div></td></tr>
<tr><td align="center">节目名称</td><td align="center">表演者</td><td align="center">表演时间</td><td align="center">部门</td><td align="center">总打赏金额</td><td align="center">打赏</td></tr>
<c:forEach items="${menu }" var="program">
<tr><td>${program.program}</td><td>${program.actor }</td><td>${program.showtime }</td><td>${program.department }</td><td>${program.reward}</td><td><input id="${program.pid }"></td><td><input type="button" value="打赏" onclick="submit1(${program.pid})"></td></tr>

</c:forEach>
<tr><td><input type="hidden" name="pid" id="pid" value="0"></td></tr>
<tr><td><input type="hidden" name="money" id="money" value="0"></td></tr>
</table>
</form>
</body>
</html>
