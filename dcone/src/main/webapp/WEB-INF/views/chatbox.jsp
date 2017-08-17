<%@page import="java.util.ArrayList"%>
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
setInterval('startrefresh2()',3000);
setInterval('startrefresh1()',3000);


	$(document).ready(function(){ 
		$("#div1").load("frame_b");
		$("#div2").load("frame_a");
		$("textarea").keydown(function(e){ 
		var curKey = e.which; 
		/* if(curKey == 13 && e.ctrlkey){ 
			$(this).val($(this).val() + "\n");
		}
		else  */if(curKey == 13 && !e.ctrlkey){
		$("#sub").click(); 
		return false; 
		} 
		}); 
		$("#sub").click(function(){
			$.post("setMessage",{
				text:$("#text").val()
			},function(data,status){
				$("#text").val('');
			});
		});
	}); 


</script>


</head>
<body>
<%
	String name= (String)session.getAttribute("username");
%>
<div style="position: absolute;left: 15%;top: 10%;reight:15%;">

<div id="div2" style="float:left;width: 300px;height: 500px;">
<%-- <table border="1" width="200" cellpadding="10" style="float:center;">
<%
	int counts=0;
	if(application.getAttribute("onlinecount")!=null)
		counts=((Integer)application.getAttribute("onlinecount")).intValue();
	%>
	<tr><td>在线人数：</td><td><%=counts %></td></tr>
		<%	
	if(application.getAttribute("onlineuser")!=null)
	{
	ArrayList<dc_user> users=(ArrayList<dc_user>)application.getAttribute("onlineuser");
	for(dc_user user:users)
	{	
%>
	<tr><td><%  out.print(user.getUsername()); %></td></tr>
<%
	}
	}
%>
</table>
 --%></div>
<div id="div1" style="float: left; width:610px; height: 400px;overflow: auto;">
<%-- <table>
<c:forEach items="${messages }" var="message">
<% 
	String itcode=(String)session.getAttribute("itcode");
	String username =(String)session.getAttribute("username");
%>
	<tr><td><div><table>
		<tr><td rowspan="2"><img width="40px" height="30px" alt="" src="photo?itcode=${message.itcode }"></td><td>${message.username }&nbsp;&nbsp;&nbsp;</td><td>${message.talktime }</td></tr>
		<tr><td></td><td>$message.message</td></tr>
		</table></div></td></tr>
</c:forEach>
</table> --%>
</div>
<div style="float: left; width:600px;">
<form action="setMessage" name="form1" onsubmit="return check()">
<textarea rows="5" cols="120" id="text" name="text"></textarea>
<input id="sub" type="button" value="发送">
</form>
</div>

</div>
</body>
</html>