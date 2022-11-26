<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 이클립스 에러로 x표시가 떠 있는 경우가 있는데 그럴때에는 잘라내고 다시 붙여 넣어 보면 괜찮아 지는 경우가 있다 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>import</title>
</head>
<body>
	<%
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd"); // 단순 날짜 형식
		SimpleDateFormat now = new SimpleDateFormat("hh : mm : ss");
	%>
	<!--format:  -->
	<p>오늘은 <strong><%= today.format(calendar.getTime()) %></strong></p> 
	<p>지금 시간은  <strong><%= now.format(calendar.getTime()) %></strong></p>
</body>
</html>