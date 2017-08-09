<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
<table cellspacing="20">
<caption>红包发放记录</caption>
<tr><td>钱包</td><td align="center">金额</td><td align="center">时间</td></tr>
<c:forEach items="${records }" var="record">
<tr><td>${record.wid }</td><td>${record.lucky_money }</td><td>${record.trade_time }</td></tr>
</c:forEach>
</table>
</div>
</body>
</html>