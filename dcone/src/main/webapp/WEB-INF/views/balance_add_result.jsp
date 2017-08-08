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
这个页面显示充值结果!
<hr>
<c:choose>
	<c:when test="${result=='充值成功'}"><div><c:out value="${result},您成功充值${amount},您的账户余额为${money}"></c:out><a href="balance_get">历史记录</a></div></c:when>
	<c:otherwise>${result}</c:otherwise>
</c:choose>
</body>
</html>