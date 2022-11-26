<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02</title>
</head>
<body>
	<c:if test="${param.color == 1 }"> <!-- el에서는 get.para로 안 받고 그냥 param.(name)으로 받아도 괜찮다 -->
		<h1 style="color:red">RED</h1>
	</c:if>
	<c:if test="${param.color == 2 }">
		<h1 style="color:green">GREEN</h1>
	</c:if>
	<c:if test="${param.color == 3 }">
		<h1 style="color:blue">BLUE</h1>
	</c:if>
</body>
</html>