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
function send(){
	var httprequest=initAjax();
	httprequest.open("post", "activate", true);
	httprequest.onreadystatechange = function(){
		if(httprequest.readyState==4)
			{
			if(httprequest.status == 200) {
				if(httprequest.responseText==1)
					{
					alert("激活成功");
					window.location.href="walletinfo";
					}
				else if(httprequest.responseText==0){
					document.getElementById("info").innerHTML="激活失败";
				}
				
			}
			}
		
	};
	httprequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
	httprequest.send(null);
}

</script>
</head>
<body>
<form action="">
<table>
<tr><td><div id="info" style="color: red;"></div></td></tr>
<tr><td>您的账户未激活！请<input type="button" value="激活" onclick="send()"></td></tr>
</table>

</form>
</body>
</html>