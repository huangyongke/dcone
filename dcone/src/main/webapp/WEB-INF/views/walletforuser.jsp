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
#layout { 
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
.div2 {
	position: absolute;
	width:300px; 
	height:200px;
	left: 55%;
	top: 50%;
	text-align:center; 
	margin:-200px auto 0 -250px; 
	background:#fafafa; 
	z-index:999; 
	border:2px solid #eee; 
	border-radius:5px; 
	box-shadow:0 0 40px #333; 
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
.out{
	position:absolute;
	left:40%;
	top:46%;
	background-color: #666666;
	width: 300px;
	text-align: center;
	font-size:large;
	display: none;
}

</style>
<script type="text/javascript">
function checkmoney(){
	var money = $("#amount").val();
	if(money=="")
		{
		$("#info2").html("输入不能为空");
		return false
		}
	else
		return true;
}

	$(document).ready(function(){
		$(".closed").click(function(){
			$("#amount").val("");
			$("#div2").hide()
			$("#layout").hide()
		});
		$("#addmoney").click(function(){
			$("#div2").show();
			$("#layout").show();
		})
		$("#submit").click(function(){
			if(checkmoney()){
				$.post("balance_adding",{
					amount:$("#amount").val()
				},function(data,status){
					if(data!=0){
						alert("您成功充值"+data+"元");
					}
					$("#amount").val("");
					$("#div2").hide()
					$("#layout").hide()
				});
			}
		});
		$("#activate").click(function(){
			$("#myModal").modal('hide');
			$.post("activate",{},function(data){
				if(data==1){
					$("#out").html("你成功激活钱包");
					$("#out").show();
					$("#addmoney").removeAttr("disabled");
					setTimeout( function(){
						$("#out").hide();
					}, 3000);
				}
			}); 
		});
	});
</script>
</head>
<body>
<div id="layout"></div>
<div id="out" class="out"></div>
<div id="div2" class="div2">
	<div class="closed">X</div>
	<div id="info2" style="color: red;"></div>
	<form>
		<div class="form-group" >
    	<label for="amount" style="position:absolute;top: 30px;left: 80px;">您的充值金额为</label></td>
    	<div class="col-sm-10" style="top: 20px;">
      		<input type="text" class="form-control" style="position:absolute; left: 20px;top: 20px;" id="amount" placeholder="请输入充值金额">
    	</div>
    	</div>
    	<div class="form-group">
    	<div class="col-sm-offset-2 col-sm-10" style="top: 90px;left:-40px;">
      		<button id="submit" type="button" class="btn btn-info">充值</button>
    	</div>
  	</div>
	</form>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">提示</h4>
            </div>
            <div class="modal-body">你的钱包账户没有激活！是否激活</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="activate" type="button" class="btn btn-primary">激活</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<div style="position:absolute;left: 10%;right: 10%;top: 10%">
<div style="position:relative; width: 200px;height: 200px">
<ul class="nav nav-pills nav-stacked">
	<li><a href="accountforuser">个人账户</a></li>
	<li class="active"><a href="walletforuser">钱包信息</a></li>
	<li><a href="recordsforuser">交易记录</a></li>

</ul>
<script type="text/javascript">
$(function () { 
	 if(${has==0}){ 
		$('#myModal').modal('show'); 
		$('#addmoney').attr("disabled","disabled");
	} 
	
});

</script>
</div>
<div style="position: absolute;left: 200px;top: 0px;right: 0px;width: 800px;height: 100%">
	<div >
		<label class="col-sm-2 control-label" style="width: 100px;">您的账户余额为：</label>
    	<div class="col-sm-10">
      	<p class="form-control-static">${amount }</p>
    	</div>
  		<button id="addmoney" type="button" class="btn btn-primary">充值</button>
	</div>
</div>
</div>
</body>
</html>