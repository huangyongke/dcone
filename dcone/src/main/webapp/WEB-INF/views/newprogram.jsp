<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="css/bootstrap.min.css">  
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script type="text/JavaScript" src="js/My97DatePicker/WdatePicker.js"></script> 
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
.out{
	position:absolute;
	left:30%;
	top:20%;
	background-color: #666666;
	width: 300px;
	text-align: center;
	font-size:large;
	display: none;
}

</style>
<script type="text/javascript">
	$(document).ready(function(){
		$("#sub").click(function(){
			$("#info").html('');
			if($("#program").val()!="" && $("#start").val()!="" && $("#stop").val()!="" && $("#actor").val()!=""&& $("#department").val()!=""){
				if($("#start").val()<$("#stop").val()){
					$.post("insertprogram",{
						program:$("#program").val(),
						start:$("#start").val(),
						stop:$("#stop").val(),
						actor:$("#actor").val(),
						department:$("#department").val()
					},function(data,status){
						if(data==1){
							$(".out").html("录入节目成功");
							$(".out").show();
							setTimeout(() => {
								$(".out").hide();
							}, 3000);
						} else if(data==0){
							$(".out").html("录入节目失败！时间冲突");
							$(".out").show();
							setTimeout(() => {
								$(".out").hide();
							}, 3000);
						}
						$("#program").val('');
						$("#start").val('');
						$("#stop").val('');
						$("#actor").val('');
						$("#department").val('');
					});
				} else{
					$("#info").html('结束时间必须大于开始时间');
				}
			}else {
				$("#info").html('输入不能为空');
			}
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
	<li class="active"><a href="newprogram">录入节目</a></li>
	<li><a href="luckymoneyrain">红包雨发放</a></li>
	<li><a href="grabluckymoney">抢红包发放</a></li>
	<li><a href="records">交易记录</a></li>
	<li><a href="luckyrecords">红包雨记录</a></li>
	<li><a href="grabluckyrecords">抢红包记录</a></li>
	<li><a href="rewardrecords">打赏记录</a></li>
</ul>
</div>
<div class="user1">
<div class="out">录入</div>
<div style="position: absolute;left: 30%;top: 25%;">
            <table style="width: 300px;height: 250px;">
            <tr><td colspan="2" align="center"><div id="info" style="color: red;"></div></td></tr>
            <tr><td>节目名称：</td><td><input type="text" name="program" id="program"></td></tr>
            <tr><td>表演者：</td><td><input type="text" name="actor" id="actor" ></td></tr>
            <tr><td>开始时间：</td><td><input type="text" name="start" id="start" class="Wdate" placeholder="输入开始时间" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td></tr>
            <tr><td>结束时间：</td><td><input type="text" name="stop" id="stop" class="Wdate" placeholder="输入结束时间" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td></tr>
            <tr><td>部门：</td><td><select id="department"><option value=""></option> 
      				<option>财务部</option> 
      				<option>会计部</option>
      				<option>人事部</option> 
      				<option>企划部</option>
      				<option>法律部</option> 
      				<option>开发部</option>
      				<option>总经理办公室</option> 
      				</select>
				</td></tr>
			<tr><td colspan="2" align="center"><input type="button" class="btn btn-info" id="sub" value="添加"></td></tr>
            </table>
</div>
</div>
</div>
</body>
</html>