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
	z-index: 100px;
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
.div1{
	position: absolute;
	left:20%;
	top:20%;
	width: 200px;
	height: 100px;
	background-color: #ffa400;
	z-index:1;
}
.redpacket{
	position: absolute;
	background-image:url("img/packet.jpg");
	background-size:cover;
	width:45px;
	height:70px;
}
.out{
	position:absolute;
	left:30%;
	top:43%;
	background-color: #666666;
	width: 300px;
	text-align: center;
	font-size:large;
	display: none;
}

</style>
<script type="text/javascript">	
	$(document).ready(function(){
		$(".div1").click(function(){
			$.post("luckystart",{
				round:$(this).attr("id")
			},function(data,status){
				if(data==0){
					$(".out").html("红包雨正在进行！请稍后再试");
					$(".out").show();
					setTimeout(() => {
						$(".out").hide();
					}, 3000);
				} else if(data==1){
					$(".out").html("红包雨开启成功");
					$(".out").show();
					setTimeout(() => {
						$(".out").hide();
					}, 3000);
					doc();
					$(".layout").show();
				} else if(data==2){
					$(".out").html("红包雨已经结束");
					$(".out").show();
					setTimeout(() => {
						$(".out").hide();
					}, 3000);
				}
				doc();
			});
		});
		$(".layout").click(function(){
			$(".layout").hide();
			$("#fdiv").html("");
			clearInterval($("#6").val());
		});
	});
	
	function doc(){
		var i = 0;
		j = setInterval(() => {
			i++;
			if(i<20){
				$("#fdiv").append('<div id="div'+i+'" class="redpacket"></div>');
				do2(i);
			}
			else
				clearInterval(j);
		}, 900);
		$("#6").val(j);
	}
	function do2(i){
		var wid=$("#fdiv").width();
		var left = Math.ceil(Math.random()*(wid-50));
		$("#div"+i).css("left", "1"*left);
		$("#div"+i).css("top","0");
		$("#div"+i).css("z-index", "10");
		$("#div"+i).show();
		$("#div"+i).animate({top:'80%'},1900);
		setTimeout(() => {
			$("#div"+i).hide();
			$("#div"+i).css("top","0");
			do2(i);
		}, 2100);
	}
</script></head>
<body>
<div class="layout"></div>
<div style="position:absolute;left: 10%;right: 10%;top: 10%;height: 80%">
<div style="position:relative; width: 200px;height: 200px">
<ul class="nav nav-pills nav-stacked">
	<li><a href="account">个人账户</a></li>
	<li><a href="accounts">用户账户</a></li>
	<li><a href="wallets">钱包信息</a></li>
	<li><a href="program">节目列表</a></li>
	<li><a href="newprogram">录入节目</a></li>
	<li  class="active"><a href="luckymoneyrain">红包雨发放</a></li>
	<li><a href="grabluckymoney">抢红包发放</a></li>
	<li><a href="records">交易记录</a></li>
	<li><a href="luckyrecords">红包雨记录</a></li>
	<li><a href="grabluckyrecords">抢红包记录</a></li>
	<li><a href="rewardrecords">打赏记录</a></li>
</ul>
</div>
<div class="user1">
<div id="fdiv" style="z-index:100"></div>
<div class="out"></div>
<div style="position: absolute;left: 43%;top: 80%;">
<input type="hidden" id="6">
</div>
<div id="1" class="div1" style="position: absolute;left:20%;top:20%;">
<span style="position: absolute;font-size:x-large; left: 12%;top: 35%;">第一轮红包雨</span>
</div>
<div id="2" class="div1" style="position: absolute;left:50%;top:20%;">
<span style="position: absolute;font-size:x-large; left: 12%;top: 35%;">第二轮红包雨</span>
</div>
<div id="3" class="div1" style="position: absolute;left:20%;top:50%;">
<span style="position: absolute;font-size:x-large; left: 12%;top: 35%;">第三轮红包雨</span>
</div>
<div id="4" class="div1" style="position: absolute;left:50%;top:50%;">
<span style="position: absolute;font-size:x-large; left: 12%;top: 35%;">第四轮红包雨</span>
</div>
</div>
</div>
</body>
</html>