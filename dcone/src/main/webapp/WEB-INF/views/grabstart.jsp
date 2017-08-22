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
	background-color: #bddd22;
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
			$.post("grabstart",{
				round:$(this).attr("id")
			},function(data,status){
				if(data==0){
					$(".out").html("抢红包正在进行！请稍后再试");
					$(".out").show();
					setTimeout(() => {
						$(".out").hide();
					}, 3000);
				} else if(data==1){
					$(".out").html("抢红包开启成功");
					$(".out").show();
					setTimeout(() => {
						$(".out").hide();
					}, 3000);
				} else if(data==2){
					$(".out").html("抢红包已经结束");
					$(".out").show();
					setTimeout(() => {
						$(".out").hide();
					}, 3000);
				}
			});
		});
	});
</script>
</head>
<body>
<div style="position:absolute;left: 10%;right: 10%;top: 10%;height: 80%;">
<div style="position:relative; width: 200px;height: 200px">
<ul class="nav nav-pills nav-stacked">
	<li><a href="account">个人账户</a></li>
	<li><a href="accounts">用户账户</a></li>
	<li><a href="wallets">钱包信息</a></li>
	<li><a href="program">节目列表</a></li>
	<li><a href="newprogram">录入节目</a></li>
	<li><a href="luckymoneyrain">红包雨发放</a></li>
	<li class="active"><a href="grabluckymoney">抢红包发放</a></li>
	<li><a href="records">交易记录</a></li>
	<li><a href="luckyrecords">红包雨记录</a></li>
	<li><a href="grabluckyrecords">抢红包记录</a></li>
	<li><a href="rewardrecords">打赏记录</a></li>
</ul>
</div>
<div class="user1">
<div class="out"></div>
<div id="1" class="div1" style="position: absolute;left:20%;top:20%;">
<span style="position: absolute;font-size:x-large; left: 12%;top: 35%;">第一轮抢红包</span>
</div>
<div id="2" class="div1" style="position: absolute;left:50%;top:20%;">
<span style="position: absolute;font-size:x-large; left: 12%;top: 35%;">第二轮抢红包</span>
</div>
<div id="3" class="div1" style="position: absolute;left:20%;top:50%;">
<span style="position: absolute;font-size:x-large; left: 12%;top: 35%;">第三轮抢红包</span>
</div>
<div id="4" class="div1" style="position: absolute;left:50%;top:50%;">
<span style="position: absolute;font-size:x-large; left: 12%;top: 35%;">第四轮抢红包</span>
</div>
</div>
</div>
</body>
</html>