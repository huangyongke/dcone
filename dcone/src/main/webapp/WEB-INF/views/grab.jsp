<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-3.2.1.min.js"></script>
<title>Insert title here</title>
<script>
$(document).ready(function(){
	$("#grab").click(function(){
		setTimeout( function(){
			$.post("grabmoney",{
				round:"1"
			},
			function(data,status){
					if(data==0){
						alert("红包已经抢完")
					} else if(data==2){
						alert("抢红包还未开始红包")
					}
					else
						alert("您成功抢的金额"+data+"元");
			});
		}, Math.random()*5000);
	});
});
</script>
</head>
<body>
<input id="grab" type="button" onclick="" value="抢红包">
</body>
</html>