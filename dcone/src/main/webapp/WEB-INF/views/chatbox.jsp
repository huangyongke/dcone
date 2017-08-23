<%@page import="java.util.ArrayList"%>
<%@page import="com.dcone.dtss.model.*" %>
<%@page import="org.springframework.jdbc.core.JdbcTemplate"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="js/jquery-3.2.1.min.js"></script>
<title>Insert title here</title>
<style>
textarea
{
width:100%;
height:100%;
font-size: 15px;
font-weight:bold;
padding-top:15px; 
padding-left:10px; 
padding-right:30px; 
background-image: url("img/inputbox.jpg");
background-size: cover;
}
.div1{
background-image: url("img/outputbox.jpg");
background-size: cover;
font-size: 15px;
font-weight:bold;
padding-left:10px; 
padding-right:30px; 
}

.div2{
background-image: url("img/userlistbackground.jpg");
background-size: cover;
font-size: 15px;
}
.div3{
background-image: url("img/inputbox.jpg");
background-size: cover;
}
</style>


<script type="text/javascript">
function check(){
	var text =document.form1.text.value
	if(text=="")
		return false
}

function startrefresh1(){
	$.ajax({
		type : "get",
		async : false,  //同步请求
		url : "frame_b",
		data : null,
		timeout:1000,
		success:function(dates){
			$("#div1").html(dates);//要刷新的div
		},
		error: function() {
           // alert("失败，请稍后再试！");
        }
	});
}
function startrefresh2(){
	$.ajax({
		type : "get",
		async : false,  //同步请求
		url : "frame_a",
		data : null,
		timeout:1000,
		success:function(dates){
			//alert(dates);
			$("#div2").html(dates);//要刷新的div
		},
		error: function() {
           // alert("失败，请稍后再试！");
        }
	});
}
setInterval('startrefresh2()',1000);
setInterval('startrefresh1()',1000);


	$(document).ready(function(){ 
		//$("#div3").width($("#div1")[0].offsetWidth)
		$("#div1").load("frame_b");
		$("#div2").load("frame_a");
		$('#div1').scrollTop($('#div1')[0].scrollHeight);
		$("textarea").keydown(function(e){ 
		var curKey = e.which; 
		if (e.ctrlKey && e.which ==13){ 
			$(this).val($(this).val() + "\n");
		}
		else  if(curKey == 13 && !e.ctrlKey){
			$("#sub").click(); 
		return false; 
		} 
		}); 
		$("#sub").click(function(){
			if($("#text").val()!=''){
				$.post("setMessage",{
					text:$("#text").val()
				},function(data,status){
					$('#div1').scrollTop($('#div1')[0].scrollHeight);
					$("#text").val('');
				});
			}
		});
	}); 
</script>


</head>
<body>

<%
	dc_user user  = (dc_user)session.getAttribute("user");
	String name= user.getUsername();
%>
<div style="position: absolute;left: 15%;top: 10%;reight:15%;">

<div style="float:left;width: 300px;height: 500px;" class="div2">
	<div id="div2" style="position:absolute;left: 40px;top: 30px;height: 400px;"></div>
</div>
<div id="div1" style="float: left; width:610px; height: 400px;overflow: auto;" class="div1">

</div>
<div id="div3" class="div3" style="float: left; heigth:300px;width: 610px; background-color: white;">
<textarea id="text" rows="5" name="text" style="position:relative; height:60px; width: 100%"></textarea>
<input id="sub" type="button" style="position:relative;left: 100%;" value="发送">
</div>
</div>
</body>
</html>