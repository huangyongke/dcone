<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
function back(){
	var httprequest=initAjax();
	httprequest.open("post", "back", true);
	httprequest.onreadystatechange = function(){
		if(httprequest.readyState==4){
			if(httprequest.status == 200) {
				if(httprequest.responseText==1)
					{
						window.parent.location.href="frame.jsp"; 
					}
					
			}
		}
		
	};
	//httprequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
	var a
	httprequest.send(a);
}

</script>
</head>
<body>
<%
	String name= (String)session.getAttribute("username");
%>
<form action="file.html">
<table>
<tr><td rowspan="2"><img name="image" id="image" alt="" src="images"></td><td colspan="2" align="center"><div style="border: 1px;solid: #000;" id="username"><%=name %></div></td></tr>
<tr><td><input type="submit" value="上传头像" ></td><td><input type="button" value="注销" onclick="back()"></td></tr>
</table>
</form>
</body>
</html>
