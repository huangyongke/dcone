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
<script type="text/javascript">	
	$(document).ready(function(){
		$("input:button").click(function(){
			$.post("luckystart",{
				round:$(this).attr("id")
			},function(data,status){
				if(data==0){
					alert("红包雨正在进行！请稍后再试")
				} else if(data==1){
					window.location.href="lucky_on";
				} else if(data==2){
					alert("红包雨已经结束")
				}
			});
		});
	});
</script></head>
<body>
<div style="position:absolute;left: 10%;right: 10%;top: 10%">
<div style="position:relative; width: 200px;height: 200px">
<ul class="nav nav-pills nav-stacked">
	<li><a href="account">个人账户</a></li>
	<li><a href="accounts">用户账户</a></li>
	<li><a href="wallets">钱包信息</a></li>
	<li><a href="program">节目列表</a></li>
	<li><a href="">新建节目</a></li>
	<li  class="active"><a href="luckymoneyrain">红包雨发放</a></li>
	<li><a href="grabluckymoney">抢红包发放</a></li>
	<li><a href="records">交易记录</a></li>
	<li><a href="luckyrecords">红包雨记录</a></li>
	<li><a href="grabluckyrecords">抢红包记录</a></li>
	<li><a href="rewardrecords">打赏记录</a></li>
</ul>
</div>
<div style="position: absolute;left: 200px;top: 0px;right: 0px;">
<form action="" method="get">
<table>
<tr><td>第一轮红包雨</td><td><input type="button" value="开始" id="1"></td></tr>
<tr><td>第二轮红包雨</td><td><input type="button" value="开始" id="2"></td></tr>
<tr><td>第三轮红包雨</td><td><input type="button" value="开始" id="3"></td></tr>
</table>
</form>
</div>
</div>
</body>
</html>