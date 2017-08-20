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
	<script type="text/JavaScript" src="js/My97DatePicker/WdatePicker.js"></script> 
<title>Insert title here</title>
</head>
<body>
<div style="position:absolute;left: 10%;right: 10%;top:10%;">
<div style="position:relative; width: 200px;height: 200px">
<ul class="nav nav-pills nav-stacked">
	<li><a href="accountforuser">个人账户</a></li>
	<li><a href="walletforuser">钱包信息</a></li>
	<li  class="active"><a href="recordsforuser">交易记录</a></li>
</ul>
</div>
<div style="position: absolute;left: 200px;top: 0px;right: 0px;">
<form action="" class="bs-example bs-example-form" role="form">

<table class="table table-striped">
<tr><td colspan="3" align="center"><input id="starttime" name="starttime" type="text" class="Wdate" placeholder="输入开始时间" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})">
&nbsp;&nbsp;&nbsp;&nbsp;<input id="stoptime" name="stoptime" type="text" class="Wdate" placeholder="输入截止时间" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"> </td>
	<td><select class="form-control" name="memo"> <option>所有</option> 
      <option>充值</option> 
      <option>打赏</option>
      <option>红包雨</option> 
      <option>抢红包</option> 
    </select></td>
    <td><button id="button2" class="btn btn-default" type="submit">查询</button></td>
</tr>
<tr><td align="center">员工号</td><td align="center">姓名</td><td align="center">交易金额(元)</td><td>交易时间</td><td>备注</td></tr>
<c:forEach items="${trades }" var="trade">
<tr><td align="center">${trade.itcode }</td><td align="center">${trade.username }</td><td align="center">${trade.amount }</td><td>${trade.tradetime }</td><td>${trade.memo }</td></tr>
</c:forEach>
</table>
</form>
</div>
</div>
</body>
</html>