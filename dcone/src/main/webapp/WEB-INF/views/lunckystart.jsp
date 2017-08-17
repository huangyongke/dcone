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
				if(status==200){
					if(data==0){
						alert("红包雨开启成功")
					} else if(data==1){
						window.location.href="lucky_on";
					} else if(data==2){
						alert("红包雨已经结束")
					}
				}
			});
		});
	});
</script>
</head>
<body>
<form action="" method="get">
<table>
<tr><td>第一轮红包雨</td><td><input type="button" value="开始" id="1" onmousedown="round1()"></td></tr>
<tr><td>第二轮红包雨</td><td><input type="button" value="开始" id="2" onmousedown="round2()"></td></tr>
<tr><td>第三轮红包雨</td><td><input type="button" value="开始" id="3" onmousedown="round3()"></td></tr>
</table>
</form>
</body>
</html>