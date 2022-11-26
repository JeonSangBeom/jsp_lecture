<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과02</title>
</head>
<body>
	<!-- jstl = 나중에 반복문을 편하게 쓸 수 있다 -->
	<h1>결과</h1>
	<%
	request.setCharacterEncoding("UTF-8");	
	String user_name = request.getParameter("user_name"); // getParameter 변수(하나일때) 가져오기	
	out.println("<p>당신의 이름은 : " + user_name + "</p>");
	
	String gender = request.getParameter("gender");
	out.println("<p>당신의 성별은 : " + gender + "</p>");
	
	String season [] = request.getParameterValues("season"); // getParameterValues 배열로 넘어온다
	out.println("당신이 좋아하는 계절은 ");	
	for(int i=0;i<season.length;i++){
		out.println(season[i]+",");
	}
	out.println("입니다. ");
	%>
</body>
</html>

