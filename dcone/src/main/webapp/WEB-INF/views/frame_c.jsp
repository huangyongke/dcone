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
function check(){
	var text =document.form1.text.value
	if(text=="")
		return false
}
$(document).ready(function(){ 
	$("textarea").keydown(function(e){ 
	var curKey = e.which; 
	/* if(curKey == 13 && e.ctrlkey){ 
		$(this).val($(this).val() + "\n");
	}
	else  */if(curKey == 13 && !e.ctrlkey){
	$("input").click(); 
	return false; 
	} 
	}); 
}); 

</script>
</head>
<body>
<form action="setMessage" name="form1" onsubmit="return check()">
<textarea rows="5" cols="120" name="text"></textarea>
<input type="submit" value="发送">
</form>
</body>
</html>