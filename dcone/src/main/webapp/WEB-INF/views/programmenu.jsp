<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="css/bootstrap.min.css">  
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
<title>Insert title here</title>
<style type="text/css">

body{
	background-image: url("img/space.jpg");
	background-size: cover;
}
.outter{
	position:absolute;
	left:20%;
	right:20%;
	top:8%;
	height:90%;
	background-image: url("img/programmenu.jpg");
}
.inner{
	border-right:1px solid #F00;border-bottom:1px solid #F00;
	margin:0 auto; 
}
td.inner{
	border-left:1px solid #F00;border-top:1px solid #F00;
}

.out{
	position:absolute;
	left:30%;
	top:46%;
	background-color: #666666;
	width: 300px;
	text-align: center;
	font-size:large;
	display: none;
}
.layout { 
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
.div1 {
	position: absolute;
	width:400px; 
	height:250px;
	left: 55%;
	top: 60%;
	text-align:center; 
	margin:-200px auto 0 -250px; 
	background:#fafafa; 
	z-index:999; 
	border:2px solid #eee; 
	border-radius:5px; 
	box-shadow:0 0 40px #333; 
	display: none;
}
</style>
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
					$(".layout").show();
					$(".div1").show();
					$(".btn-default").attr("disabled","disabled");
				}
				else  {
					$("#out").html("你成功打赏"+money+"元");
					$("#out").show();
					setTimeout(function(){
						$("#out").hide();
					}, 3000);
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

	$(document).ready(function(){
		$("#yes").click(function(){
			window.location.href="walletforuser";
		});
		$("#no").click(function(){
			$(".layout").hide();
			$(".div1").hide();
			$(".btn-default").removeAttr("disabled");
		});
		$(".closed").click(function(){
			$(".layout").hide();
			$(".div1").hide();
			$(".btn-default").removeAttr("disabled");
		});
	});
</script>

</head>
<body>
<div class="layout">
</div>
<div class="div1">
<div class="closed">X</div>
	<span style="font-size: x-large;position:relative;left:100px; top: 50px">你的账户余额不足！</span>
	<span style="font-size: x-large;position:relative;left:-100px; top: 100px">是否前去充值？</span>
	<button type="button" id="yes" class="btn-primary" style="position:absolute; left:120px; bottom: 50px; padding: 5px; width: 40px">是</button>
	<button class="btn" id="no" style=" position:absolute; left:230px; bottom: 50px;">否</button>
</div>
<div class="outter">
<div class="out" id="out">您成功打赏</div>

<div style="position: absolute;top: 25%">
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
</div>
</body>
</html>
